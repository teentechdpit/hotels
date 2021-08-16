package com.teentech.hotels.service;

import com.teentech.hotels.model.Registration;
import com.teentech.hotels.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;


    public Optional<Registration> getRegistrationById(String uuid) {
        return registrationRepository.findById(uuid);
    }

    public void add(Registration registration) {
        registrationRepository.save(registration);
    }

    public void delete(Registration registration) {
        registrationRepository.delete(registration);
    }
}
