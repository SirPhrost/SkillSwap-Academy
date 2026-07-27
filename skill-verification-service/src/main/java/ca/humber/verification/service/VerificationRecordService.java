package ca.humber.verification.service;

import ca.humber.verification.model.VerificationRecord;
import ca.humber.verification.model.VerificationStatus;
import ca.humber.verification.repository.VerificationRecordRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class VerificationRecordService {
    private final VerificationRecordRepository repository;

    public VerificationRecordService(VerificationRecordRepository repository) { this.repository = repository; }

    public List<VerificationRecord> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public VerificationRecord findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Transactional
    public VerificationRecord create(VerificationRecord record) {
        record.setId(null);
        record.setCreatedAt(null);
        return repository.save(record);
    }

    @Transactional
    public VerificationRecord update(Long id, VerificationRecord submitted) {
        VerificationRecord existing = findById(id);
        existing.setLearnerName(submitted.getLearnerName());
        existing.setLearnerEmail(submitted.getLearnerEmail());
        existing.setSkillName(submitted.getSkillName());
        existing.setProvider(submitted.getProvider());
        existing.setStatus(submitted.getStatus());
        existing.setAssessmentScore(submitted.getAssessmentScore());
        existing.setNotes(submitted.getNotes());
        return repository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        VerificationRecord existing = findById(id);
        repository.delete(existing);
    }

    public List<VerificationRecord> search(String skillName, VerificationStatus status, Integer minScore) {
        Specification<VerificationRecord> spec = (root, query, cb) -> cb.conjunction();
        if (skillName != null && !skillName.isBlank()) {
            String pattern = "%" + skillName.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("skillName")), pattern));
        }
        if (status != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }
        if (minScore != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("assessmentScore"), minScore));
        }
        return repository.findAll(spec, Sort.by(Sort.Direction.DESC, "createdAt"));
    }
}
