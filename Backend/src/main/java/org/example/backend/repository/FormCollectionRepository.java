package org.example.backend.repository;

import org.example.backend.database.form.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormCollectionRepository extends JpaRepository<Form, Long> {
}
