package org.example.backend.database.applicant;

import org.example.backend.database.application.Application;
import org.example.backend.database.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;


@Entity
@Table(name = "applicants")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Applicant extends Person {

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Application> applications;
}

