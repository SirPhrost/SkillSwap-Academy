package ca.humber.skillswap.service;

import ca.humber.skillswap.model.SkillCategory;
import ca.humber.skillswap.model.SkillLevel;
import ca.humber.skillswap.model.Workshop;
import ca.humber.skillswap.repository.WorkshopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class WorkshopService {

    private static final Set<String> SORTABLE_FIELDS = Set.of("title", "category", "level", "durationHours", "capacity", "createdAt");

    private final WorkshopRepository workshopRepository;

    public WorkshopService(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    public Page<Workshop> search(String keyword,
                                 SkillCategory category,
                                 SkillLevel level,
                                 int page,
                                 int size,
                                 String sortBy,
                                 String direction) {
        String safeSort = SORTABLE_FIELDS.contains(sortBy) ? sortBy : "createdAt";
        Sort.Direction safeDirection = "asc".equalsIgnoreCase(direction) ? Sort.Direction.ASC : Sort.Direction.DESC;
        int safePage = Math.max(page, 0);
        int safeSize = Math.min(Math.max(size, 1), 24);

        Specification<Workshop> specification = (root, query, cb) -> cb.conjunction();

        if (keyword != null && !keyword.isBlank()) {
            String pattern = "%" + keyword.trim().toLowerCase() + "%";
            specification = specification.and((root, query, cb) -> cb.or(
                    cb.like(cb.lower(root.get("title")), pattern),
                    cb.like(cb.lower(root.get("description")), pattern),
                    cb.like(cb.lower(root.get("instructorName")), pattern)
            ));
        }
        if (category != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("category"), category));
        }
        if (level != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("level"), level));
        }

        return workshopRepository.findAll(
                specification,
                PageRequest.of(safePage, safeSize, Sort.by(safeDirection, safeSort))
        );
    }

    public Workshop findById(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new WorkshopNotFoundException(id));
    }

    @Transactional
    public Workshop save(Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    @Transactional
    public void delete(Long id) {
        if (!workshopRepository.existsById(id)) {
            throw new WorkshopNotFoundException(id);
        }
        workshopRepository.deleteById(id);
    }

    public long count() {
        return workshopRepository.count();
    }

    public long countByInstructor(String email) {
        return workshopRepository.countByInstructorEmailIgnoreCase(email);
    }
}
