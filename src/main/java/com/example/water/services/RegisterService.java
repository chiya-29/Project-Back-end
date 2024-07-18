package com.example.water.services;

import com.example.water.model.Register;
import com.example.water.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    public List<Register> getAllUsers() {
        return registerRepository.findAll();
    }

    public Register getUserById(Long userId) {
        Optional<Register> userOptional = registerRepository.findById(userId);
        return userOptional.orElse(null);
    }

    public Register saveOrUpdateUser(Register user) {
        return registerRepository.save(user);
    }

    public void deleteUser(Long userId) {
        registerRepository.deleteById(userId);
    }

    public Register findByCustomerName(String customerName) {
        return registerRepository.findByCustomerName(customerName);
    }
}
