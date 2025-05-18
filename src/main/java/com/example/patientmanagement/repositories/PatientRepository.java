package com.example.patientmanagement.repositories;

import com.example.patientmanagement.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    // Custom query method to search patients by name containing a keyword
    List<Patient> findByNomContains(String keyword);
}
