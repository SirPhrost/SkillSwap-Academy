package ca.humber.skillswap.integration;

import java.time.LocalDateTime;

public record VerificationRecordDto(
        Long id,
        String learnerName,
        String learnerEmail,
        String skillName,
        String provider,
        String status,
        Integer assessmentScore,
        String notes,
        LocalDateTime createdAt
) { }
