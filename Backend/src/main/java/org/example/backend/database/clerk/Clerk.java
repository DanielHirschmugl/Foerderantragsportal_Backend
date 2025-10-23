package org.example.backend.database.clerk;

import org.example.backend.database.form.Form;
import org.example.backend.database.process.Process;
import org.example.backend.database.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Entity
@Table(name = "clerks")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Clerk extends Person {

    @Column(name = "company_email", nullable = false, unique = true)
    private String companyEmail;

    @OneToMany(mappedBy = "clerk", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Form> forms;

    @OneToMany(mappedBy = "clerk", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Process> processes;
}
