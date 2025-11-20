package org.example.backend.api.dto;

import org.example.backend.database.application.ApplicationStatus;

import java.util.List;

public record ApplicationDetailsDto(
        Long id,
        ApplicationStatus status,
        String applicantFirstName,
        String applicantLastName,
        String applicantEmail,
        String applicantAddress,
        String formTitle,
        String formDescription,
        List<ApplicationFieldDto> fields
) {}
