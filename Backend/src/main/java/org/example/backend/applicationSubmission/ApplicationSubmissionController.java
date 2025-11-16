package org.example.backend.applicationSubmission;

import lombok.RequiredArgsConstructor;
import org.example.backend.services.mailserver.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applicationSubmission")
@RequiredArgsConstructor
public class ApplicationSubmissionController {

    private final EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<Void> sendEmail() {

        // Beispiel-Text – kannst du später dynamisch aus einem DTO erzeugen
        String subject = "Bestätigung";
        String text = "Die Email wurde erfolgreich versendet.";
        String email = "daniel.hirschmugl@icloud.com";

        // E-Mail versenden
        emailService.sendEmail(email, subject, text);

        return ResponseEntity.ok().build();
    }
}
