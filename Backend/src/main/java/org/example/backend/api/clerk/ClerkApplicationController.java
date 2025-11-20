package org.example.backend.api.clerk;

import lombok.RequiredArgsConstructor;
import org.example.backend.api.dto.ApplicationDetailsDto;
import org.example.backend.api.dto.ApplicationOverviewDto;
import org.example.backend.service.ApplicationOverviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clerks/applications")
@RequiredArgsConstructor
public class ClerkApplicationController {

    private final ApplicationOverviewService applicationOverviewService;

    // All application for a clerk (by clerk id)
    @GetMapping("/by-clerk/{svnr}")
    public List<ApplicationOverviewDto> getApplicationsForClerk(@PathVariable Long svnr) {
        return applicationOverviewService.getAllApplicationsOverviewForClerk(svnr);
    }

    // details of a single application (for Ansehen)
    @GetMapping("/{applicationId}")
    public ApplicationDetailsDto getApplicationDetails(@PathVariable Long applicationId) {
        return applicationOverviewService.getApplicationDetails(applicationId);
    }
}
