package ca.humber.skillswap.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "workshops")
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required.")
    @Size(min = 5, max = 120, message = "Title must be between 5 and 120 characters.")
    @Column(nullable = false, length = 120)
    private String title;

    @NotNull(message = "Choose a category.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private SkillCategory category;

    @NotNull(message = "Choose a skill level.")
    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level", nullable = false, length = 30)
    private SkillLevel level;

    @Column(nullable = false, length = 100)
    private String instructorName;

    @Column(nullable = false, length = 150)
    private String instructorEmail;

    @NotNull(message = "Duration is required.")
    @Min(value = 1, message = "Duration must be at least 1 hour.")
    @Max(value = 40, message = "Duration cannot be more than 40 hours.")
    @Column(nullable = false)
    private Integer durationHours;

    @NotNull(message = "Capacity is required.")
    @Min(value = 1, message = "Capacity must be at least 1 learner.")
    @Max(value = 100, message = "Capacity cannot exceed 100 learners.")
    @Column(nullable = false)
    private Integer capacity;

    @NotBlank(message = "Description is required.")
    @Size(min = 20, max = 1000, message = "Description must be between 20 and 1000 characters.")
    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public SkillCategory getCategory() { return category; }
    public void setCategory(SkillCategory category) { this.category = category; }
    public SkillLevel getLevel() { return level; }
    public void setLevel(SkillLevel level) { this.level = level; }
    public String getInstructorName() { return instructorName; }
    public void setInstructorName(String instructorName) { this.instructorName = instructorName; }
    public String getInstructorEmail() { return instructorEmail; }
    public void setInstructorEmail(String instructorEmail) { this.instructorEmail = instructorEmail; }
    public Integer getDurationHours() { return durationHours; }
    public void setDurationHours(Integer durationHours) { this.durationHours = durationHours; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public String getCreatedDateDisplay() {
        return createdAt == null ? "" : createdAt.format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
    }
}
