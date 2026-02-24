package com.example.mlbb.service;

import com.example.mlbb.dto.RegisterRequest;
import com.example.mlbb.entity.User;
import com.example.mlbb.enums.SystemRole;
import com.example.mlbb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public String getUserByMlbbId(Long id){
        return String.valueOf(userRepository.findById(id));
    }

    @Transactional
    public User registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }
        String baseUsername = request.getEmail().split("@")[0];
        String finalUsername = baseUsername;

        // Тут можно добавить проверку на уникальность username в БД,
        // если он занят — добавляем случайные цифры

        User user = User.builder()
                .email(request.getEmail())
                .username(finalUsername)
                .password(passwordEncoder.encode(request.getPassword()))
                .mlbbId(request.getMlbbId())
                .role(SystemRole.USER)
                .build();
        return userRepository.save(user);
    }
}
