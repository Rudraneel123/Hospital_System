package com.hospital.patientservice.controller;

import com.hospital.patientservice.dto.ApiResponse;
import com.hospital.patientservice.model.Patient;
import com.hospital.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Patient>>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(new ApiResponse<>(200, "Patients fetched successfully!", patients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> getPatientById(@PathVariable Integer id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Patient fetched successfully!", patient));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Patient>> addPatient(@Valid @RequestBody Patient patient) {
        Patient saved = patientService.addPatient(patient);
        return ResponseEntity.ok(new ApiResponse<>(201, "Patient created successfully!", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> updatePatient(@PathVariable Integer id,
                                                               @Valid @RequestBody Patient patient) {
        Patient updated = patientService.updatePatient(id, patient);
        return ResponseEntity.ok(new ApiResponse<>(200, "Patient updated successfully!", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePatient(@PathVariable Integer id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Patient deleted successfully!", null));
    }
}