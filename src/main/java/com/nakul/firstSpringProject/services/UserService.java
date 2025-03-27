package com.nakul.firstSpringProject.services;

import com.nakul.firstSpringProject.entity.JournalEntry;
import com.nakul.firstSpringProject.entity.User;
import com.nakul.firstSpringProject.repositories.JournalEntryRepository;
import com.nakul.firstSpringProject.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public void deleteByUsername(String username) { userRepository.deleteByUsername(username); }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
