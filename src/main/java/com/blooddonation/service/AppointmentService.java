package com.blooddonation.service;

import com.blooddonation.model.Appointment;
import com.blooddonation.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAppointmentsByDonorId(Long donorId) {
        return appointmentRepository.findByDonorId(donorId);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
