package com.example.water.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.water.model.Register;
import com.example.water.services.RegisterService;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping
    public ResponseEntity<List<Register>> getAllUsers() {
        List<Register> users = registerService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Register> getUserById(@PathVariable Long userId) {
        Register user = registerService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Register loginUser) {
        Register user = registerService.findByCustomerName(loginUser.getCustomerName());

        if (user == null || !user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid customer name or password.");
        }

        // You can customize the response as needed, like returning user details or JWT token
        return ResponseEntity.ok().body("Login successful");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Register newUser) {
        // Add logic to handle registration, validation, etc.
        Register savedUser = registerService.saveOrUpdateUser(newUser);
        return ResponseEntity.ok().body(savedUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Register> updateUser(@PathVariable Long userId, @RequestBody Register updatedUser) {
        Register existingUser = registerService.getUserById(userId);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        
        existingUser.setCustomerName(updatedUser.getCustomerName());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setCustomerLocation(updatedUser.getCustomerLocation());
        existingUser.setCustomerPhone(updatedUser.getCustomerPhone());
        existingUser.setCustomerEmail(updatedUser.getCustomerEmail());
        
        Register updated = registerService.saveOrUpdateUser(existingUser);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        registerService.deleteUser(userId);
        return ResponseEntity.ok().body("User deleted successfully");
    }
}
