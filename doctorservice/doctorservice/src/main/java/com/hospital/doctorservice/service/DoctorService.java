package com.hospital.doctorservice.service;

import com.hospital.doctorservice.exception.ResourceNotFoundException;
import com.hospital.doctorservice.model.Doctor;
import com.hospital.doctorservice.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Transactional(readOnly = true)
    public List<Doctor> getAllDoctors() {
        logger.info("Fetching all doctors");
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Doctor getDoctorById(Integer id) {
        logger.info("Fetching doctor with id: {}", id);
        return doctorRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Doctor with id {} not found!", id);
                    return new ResourceNotFoundException("Doctor", id);
                });
    }

    @Transactional
    public Doctor addDoctor(Doctor doctor) {
        logger.info("Adding new doctor: {}", doctor.getName());
        return doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor updateDoctor(Integer id, Doctor updatedDoctor) {
        logger.info("Updating doctor with id: {}", id);
        Doctor doctor = getDoctorById(id);
        doctor.setName(updatedDoctor.getName());
        doctor.setSpecialization(updatedDoctor.getSpecialization());
        doctor.setPhone(updatedDoctor.getPhone());
        doctor.setEmail(updatedDoctor.getEmail());
        return doctorRepository.save(doctor);
    }

    @Transactional
    public boolean deleteDoctor(Integer id) {
        logger.info("Deleting doctor with id: {}", id);
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            logger.info("Doctor deleted successfully!");
            return true;
        }
        logger.warn("Doctor with id {} not found!", id);
        return false;
    }
}