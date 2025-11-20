package org.example.backend.database.form;

import org.example.backend.database.application.Application;
import org.example.backend.database.clerk.Clerk;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.database.formfield.Form_Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String definition;

    private boolean published;

    private LocalDateTime publishedAt;

    @OneToMany(mappedBy = "form", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Application> applicationsMadeOf;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<Form_Field> fields;

    @OneToMany(mappedBy = "form",  cascade = CascadeType.ALL)
    private List<Form_Field> rememberFields;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clerk_svnr")
    private Clerk responsibleClerk;

    @PrePersist
    public void prePersist() {
        if (creationDate == null) {
            creationDate = LocalDate.now();
        }
    }
}



