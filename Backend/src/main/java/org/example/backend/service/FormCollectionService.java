package org.example.backend.controller;

import org.springframework.stereotype.Service;

@Service
class FormCollectionSerivce {

    private final FormCollectionRepository formCollectionRepository;

    public FormCollectionSerivce(FormCollectionRepository formCollectionRepository){
        this.formCollectionRepository = formCollectionRepository;
    }

}
