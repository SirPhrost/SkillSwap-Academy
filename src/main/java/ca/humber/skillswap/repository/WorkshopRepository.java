package ca.humber.skillswap.repository;

import ca.humber.skillswap.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkshopRepository extends JpaRepository<Workshop, Long>, JpaSpecificationExecutor<Workshop> {
    long countByInstructorEmailIgnoreCase(String instructorEmail);
}
