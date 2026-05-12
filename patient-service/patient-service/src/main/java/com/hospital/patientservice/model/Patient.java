package com.hospital.patientservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @NotNull(message = "Age cannot be empty!")
    @Min(value = 1, message = "Age cannot be less than 1!")
    @Max(value = 150, message = "Age cannot be more than 150!")
    private Integer age;

    @NotBlank(message = "Gender cannot be empty!")
    private String gender;

    @NotBlank(message = "Phone cannot be empty!")
    private String phone;

    private String address;
}