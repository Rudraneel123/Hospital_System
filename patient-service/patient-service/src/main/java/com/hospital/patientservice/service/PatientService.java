package com.hospital.patientservice.service;

import com.hospital.patientservice.exception.ResourceNotFoundException;
import com.hospital.patientservice.model.Patient;
import com.hospital.patientservice.repository.PatientRepository;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Patient> getAllPatients() {
        logger.info("Fetching all patients");
        return patientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Patient getPatientById(Integer id) {
        logger.info("Fetching patient with id: {}", id);
        return patientRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Patient with id {} not found!", id);
                    return new ResourceNotFoundException("Patient", id);
                });
    }

    @Transactional
    public Patient addPatient(Patient patient) {
        logger.info("Adding new patient: {}", patient.getName());
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(Integer id, Patient updatedPatient) {
        logger.info("Updating patient with id: {}", id);
        Patient patient = getPatientById(id);
        patient.setName(updatedPatient.getName());
        patient.setAge(updatedPatient.getAge());
        patient.setGender(updatedPatient.getGender());
        patient.setPhone(updatedPatient.getPhone());
        patient.setAddress(updatedPatient.getAddress());
        return patientRepository.save(patient);
    }

    @Transactional
    public boolean deletePatient(Integer id) {
        logger.info("Deleting patient with id: {}", id);
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            logger.info("Patient deleted successfully!");
            return true;
        }
        logger.warn("Patient with id {} not found!", id);
        return false;
    }
}