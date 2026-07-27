package ca.humber.skillswap.integration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VerificationRequest {

    @NotBlank(message = "Learner name is required.")
    @Size(max = 100, message = "Learner name cannot exceed 100 characters.")
    private String learnerName;

    @NotBlank(message = "Learner email is required.")
    @Email(message = "Enter a valid learner email.")
    private String learnerEmail;

    @NotBlank(message = "Skill name is required.")
    @Size(max = 120, message = "Skill name cannot exceed 120 characters.")
    private String skillName;

    @NotBlank(message = "Provider is required.")
    @Size(max = 120, message = "Provider cannot exceed 120 characters.")
    private String provider;

    @NotBlank(message = "Status is required.")
    private String status = "PENDING";

    @NotNull(message = "Assessment score is required.")
    @Min(value = 0, message = "Score cannot be below 0.")
    @Max(value = 100, message = "Score cannot exceed 100.")
    private Integer assessmentScore;

    @Size(max = 500, message = "Notes cannot exceed 500 characters.")
    private String notes;

    public String getLearnerName() { return learnerName; }
    public void setLearnerName(String learnerName) { this.learnerName = learnerName; }
    public String getLearnerEmail() { return learnerEmail; }
    public void setLearnerEmail(String learnerEmail) { this.learnerEmail = learnerEmail; }
    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getAssessmentScore() { return assessmentScore; }
    public void setAssessmentScore(Integer assessmentScore) { this.assessmentScore = assessmentScore; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
