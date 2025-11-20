package org.example.backend.formularserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.database.form.Form;
import org.example.backend.formularserver.dto.FormDto;
import org.example.backend.formularserver.dto.UpsertFormPayload;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FormularServerService {
    private final FormRepository formRepository;
    private final ObjectMapper objectMapper;

    public FormularServerService(FormRepository formRepository, ObjectMapper objectMapper) {
        this.formRepository = formRepository;
        this.objectMapper = objectMapper;
    }

    public List<FormDto> findAll() {
        return formRepository.findAllByOrderByCreationDateDesc()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public FormDto findById(Long id) {
        return toDto(getFormOrThrow(id));
    }

    public FormDto create(UpsertFormPayload payload) {
        Form form = new Form();
        applyPayload(form, payload);
        form.setPublished(false);
        form.setPublishedAt(null);
        return toDto(formRepository.save(form));
    }

    public FormDto update(Long id, UpsertFormPayload payload) {
        Form form = getFormOrThrow(id);
        applyPayload(form, payload);
        return toDto(formRepository.save(form));
    }

    public FormDto publish(Long id) {
        Form form = getFormOrThrow(id);
        if (!form.isPublished()) {
            form.setPublished(true);
            form.setPublishedAt(LocalDateTime.now());
            form = formRepository.save(form);
        }
        return toDto(form);
    }

    public void delete(Long id) {
        if (!formRepository.existsById(id)) {
            throw notFound(id);
        }
        formRepository.deleteById(id);
    }

    private Form getFormOrThrow(Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> notFound(id));
    }

    private void applyPayload(Form form, UpsertFormPayload payload) {
        if (payload.title() == null || payload.title().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Der Titel darf nicht leer sein.");
        }
        if (payload.definition() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Eine Tripetto-Definition wird benötigt.");
        }
        form.setTitle(payload.title());
        form.setDescription(payload.description());
        form.setDefinition(writeDefinition(payload.definition()));
    }

    private String writeDefinition(JsonNode definition) {
        try {
            return objectMapper.writeValueAsString(definition);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Definition konnte nicht gespeichert werden.", e);
        }
    }

    private JsonNode readDefinition(String definition) {
        if (definition == null || definition.isBlank()) {
            return objectMapper.createObjectNode();
        }
        try {
            return objectMapper.readTree(definition);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Gespeicherte Definition ist ungültig.", e);
        }
    }

    private FormDto toDto(Form form) {
        return new FormDto(
                form.getForm_id(),
                form.getTitle(),
                form.getDescription(),
                readDefinition(form.getDefinition()),
                form.isPublished(),
                form.getCreationDate(),
                form.getPublishedAt()
        );
    }

    private ResponseStatusException notFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Formular " + id + " wurde nicht gefunden.");
    }
}



