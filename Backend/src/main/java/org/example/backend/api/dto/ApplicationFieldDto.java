package org.example.backend.api.dto;

public record ApplicationFieldDto(
        String technicalName,
        String label,
        String fieldType,
        boolean required,
        String value
) {}
