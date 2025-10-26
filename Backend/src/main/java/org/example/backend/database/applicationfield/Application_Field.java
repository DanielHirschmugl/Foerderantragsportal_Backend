package org.example.backend.database.applicationfield;

import lombok.AllArgsConstructor;
import org.example.backend.database.application.Application;
import org.example.backend.database.formfield.Form_Field;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.database.processingStep.ProcessingStep;

import java.util.List;

@Entity
@Table(name = "application_fields")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application_Field {

    @Id
    @GeneratedValue
    private Long applicationfield_id;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @ManyToOne
    @JoinColumn(name = "form_field_id")
    private Form_Field form_field;

}

