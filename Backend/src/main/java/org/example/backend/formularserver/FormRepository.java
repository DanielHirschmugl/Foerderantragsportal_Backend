package org.example.backend.formularserver;

import org.example.backend.database.form.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findAllByOrderByCreationDateDesc();
}
