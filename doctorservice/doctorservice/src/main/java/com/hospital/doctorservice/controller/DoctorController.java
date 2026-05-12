package com.hospital.doctorservice.controller;

import com.hospital.doctorservice.dto.ApiResponse;
import com.hospital.doctorservice.model.Doctor;
import com.hospital.doctorservice.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Doctor>>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(new ApiResponse<>(200, "Doctors fetched successfully!", doctors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Doctor>> getDoctorById(@PathVariable Integer id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Doctor fetched successfully!", doctor));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Doctor>> addDoctor(@Valid @RequestBody Doctor doctor) {
        Doctor saved = doctorService.addDoctor(doctor);
        return ResponseEntity.ok(new ApiResponse<>(201, "Doctor created successfully!", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Doctor>> updateDoctor(@PathVariable Integer id,
                                                             @Valid @RequestBody Doctor doctor) {
        Doctor updated = doctorService.updateDoctor(id, doctor);
        return ResponseEntity.ok(new ApiResponse<>(200, "Doctor updated successfully!", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDoctor(@PathVariable Integer id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Doctor deleted successfully!", null));
    }
}