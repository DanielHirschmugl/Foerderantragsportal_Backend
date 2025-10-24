package org.example.backend.database.formfield;

import org.example.backend.database.form.Form;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "form_fields")
@Data
@NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Form_Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long field_id;

    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;
}

