package org.example.backend.database.person;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.backend.database.application.Application;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "persons")
@Data
@NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    private Long svnr;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String address;

    @OneToMany(mappedBy = "applicant", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Application> createdApplications;

}

