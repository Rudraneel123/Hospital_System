package com.hospital.doctorservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @NotBlank(message = "Specialization cannot be empty!")
    private String specialization;

    @NotBlank(message = "Phone cannot be empty!")
    private String phone;

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Email should be valid!")
    private String email;
}