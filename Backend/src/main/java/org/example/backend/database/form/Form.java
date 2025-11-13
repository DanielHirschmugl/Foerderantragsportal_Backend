package org.example.backend.database.form;

import org.example.backend.database.application.Application;
import org.example.backend.database.clerk.Clerk;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.database.formfield.Form_Field;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "forms")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long form_id;

    private String title;
    private String description;
    private LocalDate creation_date;

    @OneToMany(mappedBy = "form", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Application> applicationsMadeOf;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<Form_Field> fields;

    @OneToMany(mappedBy = "form",  cascade = CascadeType.ALL)
    private List<Form_Field> rememberFields;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clerk_svnr", nullable = false)
    private Clerk responsibleClerk;

}

