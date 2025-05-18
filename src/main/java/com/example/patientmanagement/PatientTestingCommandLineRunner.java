package com.example.patientmanagement;

import com.example.patientmanagement.entities.Patient;
import com.example.patientmanagement.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class PatientTestingCommandLineRunner implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("========== TESTING PATIENT REPOSITORY CRUD OPERATIONS ==========");
        
        // Create - Add multiple patients
        System.out.println("Creating patients...");
        Patient patient1 = new Patient(null, "Mohamed", new Date(), true, 75);
        Patient patient2 = new Patient(null, "Ahmed", new Date(), false, 50);
        Patient patient3 = new Patient(null, "Sara", new Date(), true, 65);
        Patient patient4 = new Patient(null, "Fatima", new Date(), false, 80);
        
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);
        patientRepository.save(patient4);
        
        // Read - Retrieve all patients
        System.out.println("\nRetrieving all patients:");
        List<Patient> allPatients = patientRepository.findAll();
        allPatients.forEach(p -> System.out.println(p));
        
        // Read - Retrieve one patient by id
        System.out.println("\nRetrieving patient by ID 1:");
        Optional<Patient> patientById = patientRepository.findById(1L);
        if (patientById.isPresent()) {
            System.out.println(patientById.get());
        } else {
            System.out.println("Patient with ID 1 not found");
        }
        
        // Read - Search patients by name keyword
        System.out.println("\nSearching patients with 'a' in their name:");
        List<Patient> patientsByName = patientRepository.findByNomContains("a");
        patientsByName.forEach(p -> System.out.println(p));
        
        // Update - Update a patient's details
        System.out.println("\nUpdating patient with ID 2:");
        Optional<Patient> patientToUpdate = patientRepository.findById(2L);
        if (patientToUpdate.isPresent()) {
            Patient patient = patientToUpdate.get();
            System.out.println("Before update: " + patient);
            patient.setMalade(true);
            patient.setScore(95);
            patientRepository.save(patient);
            System.out.println("After update: " + patientRepository.findById(2L).get());
        }
        
        // Delete - Delete a patient
        System.out.println("\nDeleting patient with ID 3:");
        patientRepository.deleteById(3L);
        
        // Verify deletion
        System.out.println("\nFinal list of patients after deletion:");
        patientRepository.findAll().forEach(p -> System.out.println(p));
        
        System.out.println("========== CRUD OPERATIONS COMPLETED ==========");
    }
}
