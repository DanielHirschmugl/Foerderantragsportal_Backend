package org.example.backend.formCollection;

import org.example.backend.formCollection.dto.FormOverviewDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/formulare/collection")
public class FormCollectionController {
    private final FormCollectionService formCollectionService;

    public FormCollectionController(FormCollectionService formCollectionService){
        this.formCollectionService = formCollectionService;
    }

    @GetMapping
    public List<FormOverviewDTO> getAll(){
        // DUMMY DATEN!
        return List.of(
                new FormOverviewDTO(1L, "Mietbeihilfe", LocalDate.now(), "Antrag auf finanzielle Unterstützung zur Miete."),
                new FormOverviewDTO(2L, "Wohnbeihilfe", LocalDate.now(), "Formular für Wohnbeihilfe bei niedrigen Einkommen."),
                new FormOverviewDTO(3L, "Heizkostenzuschuss", LocalDate.now(), "Unterstützung für Heiz- und Energiekosten."),
                new FormOverviewDTO(4L, "Studienbeihilfe", LocalDate.now(), "Beantragung der Studienbeihilfe für Studierende."),
                new FormOverviewDTO(5L, "Familienbeihilfe", LocalDate.now(), "Antrag zur finanziellen Unterstützung für Familien."),
                new FormOverviewDTO(6L, "Kinderbetreuungszuschuss", LocalDate.now(), "Zuschuss zu Kinderbetreuungskosten."),
                new FormOverviewDTO(7L, "Pflegegeld", LocalDate.now(), "Antrag auf Pflegegeld für pflegebedürftige Personen."),
                new FormOverviewDTO(8L, "Sozialhilfe Neu", LocalDate.now(), "Formular zur sozialen Mindestsicherung.")
        );
    }

}
