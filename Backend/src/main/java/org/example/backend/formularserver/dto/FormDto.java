package org.example.backend.formularserver.dto;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FormDto(
        Long id,
        String title,
        String description,
        JsonNode definition,
        boolean published,
        LocalDate creationDate,
        LocalDateTime publishedAt
) {
}