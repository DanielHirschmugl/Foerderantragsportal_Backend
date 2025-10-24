package org.example.backend.database.applicationfield;

import org.example.backend.database.application.Application;
import org.example.backend.database.formfield.Form_Field;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "application_fields")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Application_Field extends Form_Field {

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;
}

