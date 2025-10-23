package org.example.backend.database.process;

import org.example.backend.database.application.Application;
import org.example.backend.database.clerk.Clerk;
import org.example.backend.database.message.Message;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Entity
@Table(
        name = "processes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"application_id", "clerk_svnr"})
)
@Data
@NoArgsConstructor @AllArgsConstructor
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long process_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clerk_svnr", nullable = false)
    private Clerk clerk;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Message> messages;
}

