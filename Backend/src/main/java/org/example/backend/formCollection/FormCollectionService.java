package org.example.backend.formCollection;

import org.example.backend.database.form.Form;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormCollectionService {

    private final FormCollectionRepository formCollectionRepository;

    public FormCollectionService(FormCollectionRepository formCollectionRepository){
        this.formCollectionRepository = formCollectionRepository;
    }

    public List<Form> getAll(){
        return formCollectionRepository.findAll();
    }

}
