package org.example.backend.api.dto;

import org.example.backend.database.application.ApplicationStatus;

import java.time.LocalDate;

public record ApplicationOverviewDto(
        Long id,
        String applicantFirstName,
        String applicantLastName,
        ApplicationStatus status,
        String lastProcessingStepDescription,
        LocalDate lastProcessingStepEndDate
) {
}