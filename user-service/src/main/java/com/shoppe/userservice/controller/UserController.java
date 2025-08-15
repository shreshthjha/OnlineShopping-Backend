package com.shoppe.userservice.controller;

import com.shoppe.userservice.entity.User;
import com.shoppe.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        User saved = userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully with id: " + saved.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok("Login successful. Welcome " + user.getName());
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody String newPassword) {
        userService.changePassword(id, newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }
}
