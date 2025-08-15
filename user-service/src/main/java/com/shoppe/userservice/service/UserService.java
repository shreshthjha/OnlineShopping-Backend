package com.shoppe.userservice.service;

import com.shoppe.userservice.entity.User;
import com.shoppe.userservice.exceptions.UserNotFoundException;
import com.shoppe.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        updatedUser.setId(id);
        return userRepository.save(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new UserNotFoundException("Invalid email or password");
    }

    public void changePassword(Long id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
