package org.example.backend.database.application;

import org.example.backend.database.applicant.Applicant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.database.form.Form;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long application_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_svnr", nullable = false)
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;
}

