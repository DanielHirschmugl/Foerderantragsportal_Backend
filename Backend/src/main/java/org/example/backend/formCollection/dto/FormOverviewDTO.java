package org.example.backend.formCollection.dto;

import java.time.LocalDate;

public record FormOverviewDTO(
        Long id,
        String title,
        LocalDate date,
        String description
) {}
