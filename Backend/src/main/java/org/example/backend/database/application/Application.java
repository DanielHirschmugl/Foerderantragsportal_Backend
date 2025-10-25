package org.example.backend.database.application;

import lombok.ToString;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.database.applicationfield.Application_Field;
import org.example.backend.database.clerk.Clerk;
import org.example.backend.database.form.Form;
import org.example.backend.database.message.Message;
import org.example.backend.database.person.Person;

import java.util.List;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long application_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_svnr", nullable = false)
    private Person applicant;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clerk_svnr")
    private Clerk responsibleClerk;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Message> messages;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private List<Application_Field> fields;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.OPEN;
}

