package org.example.backend.formularserver;

import org.example.backend.formularserver.dto.FormDto;
import org.example.backend.formularserver.dto.UpsertFormPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/formulare")
public class FormularServerController {

    private final FormularServerService formularServerService;

    public FormularServerController(FormularServerService formularServerService) {
        this.formularServerService = formularServerService;
    }

    @GetMapping
    public List<FormDto> findAll() {
        return formularServerService.findAll();
    }

    @GetMapping("/{id}")
    public FormDto findById(@PathVariable Long id) {
        return formularServerService.findById(id);
    }

    @PostMapping
    public ResponseEntity<FormDto> create(@RequestBody UpsertFormPayload payload) {
        FormDto dto = formularServerService.create(payload);
        return ResponseEntity
                .created(URI.create("/api/formulare/" + dto.id()))
                .body(dto);
    }

    @PutMapping("/{id}")
    public FormDto update(@PathVariable Long id, @RequestBody UpsertFormPayload payload) {
        return formularServerService.update(id, payload);
    }

    @PostMapping("/{id}/publish")
    public FormDto publish(@PathVariable Long id) {
        return formularServerService.publish(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        formularServerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}