package org.example.backend.controller;

import org.example.backend.database.form.Form;
import org.example.backend.service.FormCollectionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/formulare")
public class FormCollectionController {
    private final FormCollectionService formCollectionService;

    public FormCollectionController(FormCollectionService formCollectionService){
        this.formCollectionService = formCollectionService;
    }

    @GetMapping
    public List<Form> getAll(){
        return formCollectionService.getAll();
    }

}
