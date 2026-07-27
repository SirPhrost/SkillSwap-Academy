package ca.humber.verification.repository;

import ca.humber.verification.model.VerificationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VerificationRecordRepository extends JpaRepository<VerificationRecord, Long>, JpaSpecificationExecutor<VerificationRecord> { }
