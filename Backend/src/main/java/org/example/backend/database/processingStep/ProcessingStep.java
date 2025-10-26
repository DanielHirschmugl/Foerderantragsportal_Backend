package org.example.backend.database.processingStep;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.database.application.Application;

import java.time.LocalDate;

@Entity
@Table(name = "processing_steps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessingStep {
    @Id
    @GeneratedValue
    private Long processingStep_id;

    @JoinColumn(name = "title")
    private String title;

    @JoinColumn(name = "description")
    private String description;

    @JoinColumn(name = "start_date")
    private LocalDate startDate;

    @JoinColumn(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

}
