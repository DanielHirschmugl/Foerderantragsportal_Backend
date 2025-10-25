package org.example.backend.database.message;

import org.example.backend.database.application.Application;
import org.example.backend.database.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long message_id;

    private String headline;
    private String body;

    @ManyToOne
    @JoinColumn(name = "sender_svnr")
    private Person sender;

    @ManyToOne
    @JoinColumn(name = "recipient_svnr")
    private Person recipient;

    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

}
