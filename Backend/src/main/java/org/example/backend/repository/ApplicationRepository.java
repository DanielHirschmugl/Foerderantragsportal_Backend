package org.example.backend.repository;

import org.example.backend.database.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // Applications assigned to a specific clerk
    List<Application> findByResponsibleClerk_Svnr(Long svnr);

    // later: filter methods (by status, applicant, date range, etc.)

}
