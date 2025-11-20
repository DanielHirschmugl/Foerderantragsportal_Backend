package org.example.backend.formularserver.dto;

import com.fasterxml.jackson.databind.JsonNode;

public record UpsertFormPayload(
        String title,
        String description,
        JsonNode definition
) {
}

