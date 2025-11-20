// src/main/java/org/example/backend/service/ApplicationOverviewService.java
package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.repository.ApplicationRepository;
import org.example.backend.api.dto.ApplicationDetailsDto;
import org.example.backend.api.dto.ApplicationFieldDto;
import org.example.backend.api.dto.ApplicationOverviewDto;
import org.example.backend.database.application.Application;
import org.example.backend.database.applicationfield.Application_Field;
import org.example.backend.database.form.Form;
import org.example.backend.database.formfield.Form_Field;
import org.example.backend.database.person.Person;
import org.example.backend.database.processingStep.ProcessingStep;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationOverviewService {

    private final ApplicationRepository applicationRepository;

    // --- existing method (overview list) ---
    public List<ApplicationOverviewDto> getAllApplicationsOverviewForClerk(Long clerkSvnr) {
        List<Application> applications =
                applicationRepository.findByResponsibleClerk_Svnr(clerkSvnr);

        return applications.stream()
                .map(this::mapToOverviewDto)
                .toList();
    }

    private ApplicationOverviewDto mapToOverviewDto(Application application) {
        Person applicant = application.getApplicant();

        String firstName = applicant != null ? applicant.getFirstName() : null;
        String lastName  = applicant != null ? applicant.getLastName() : null;

        ProcessingStep lastStep = null;
        if (application.getProcessingSteps() != null && !application.getProcessingSteps().isEmpty()) {
            lastStep = application.getProcessingSteps().stream()
                    .max(Comparator.comparing(
                            step -> {
                                LocalDate end = step.getEndDate();
                                LocalDate start = step.getStartDate();
                                return end != null ? end : (start != null ? start : LocalDate.MIN);
                            }
                    ))
                    .orElse(null);
        }

        String lastStepDescription = lastStep != null ? lastStep.getDescription() : null;
        LocalDate lastStepEndDate  = lastStep != null ? lastStep.getEndDate() : null;

        return new ApplicationOverviewDto(
                application.getId(),
                firstName,
                lastName,
                application.getStatus(),
                lastStepDescription,
                lastStepEndDate
        );
    }

    // --- NEW: details for a single application ---
    public ApplicationDetailsDto getApplicationDetails(Long applicationId) {
        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));

        Person applicant = app.getApplicant();
        Form form = app.getForm();

        List<ApplicationFieldDto> fieldDtos = app.getFields().stream()
                .map(this::mapField)
                .toList();

        return new ApplicationDetailsDto(
                app.getId(),
                app.getStatus(),
                applicant != null ? applicant.getFirstName() : null,
                applicant != null ? applicant.getLastName() : null,
                applicant != null ? applicant.getEmail() : null,
                applicant != null ? applicant.getAddress() : null,
                form != null ? form.getTitle() : null,
                form != null ? form.getDescription() : null,
                fieldDtos
        );
    }

    private ApplicationFieldDto mapField(Application_Field af) {

        Form_Field ff = af.getForm_field();

        String label = ff != null ? ff.getLabel() : null;

        // build some technical name from the id
        String technicalName = ff != null ? ("field_" + ff.getField_id()) : null;

        // schema has no type / required flag yet
        String fieldType = null;
        boolean required = false;

        // actual entered value stored in Application_Field.value
        String value = af.getValue();

        return new ApplicationFieldDto(
                technicalName,
                label,
                fieldType,
                required,
                value
        );
    }
}