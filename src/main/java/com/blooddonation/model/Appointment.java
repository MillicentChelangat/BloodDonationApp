package com.blooddonation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "donor_id", referencedColumnName = "id")
    private Donor donor;// Foreign key to Donor entity

    @Column(nullable = false)
    private LocalDateTime appointmentDate; // Stores date and time

    @Column(nullable = false)
    private String status;// e.g., "Scheduled", "Completed", "Canceled"
}

