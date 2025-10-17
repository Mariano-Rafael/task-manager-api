package io.task_manager.task_manager_api.service;

import io.task_manager.task_manager_api.domain.User;
import io.task_manager.task_manager_api.dto.UserCreateDTO;
import io.task_manager.task_manager_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(UserCreateDTO dto) {
        userRepository.findByEmail(dto.email()).ifPresent(user -> {
            throw new IllegalStateException("E-mail já cadastrado.");
        });

        String hashedPassword = hashPassword(dto.password());

        var newUser = new User(
                null,
                dto.name(),
                dto.email(),
                hashedPassword
        );
        return userRepository.save(newUser);
    }

    private String hashPassword(String password) {
        return "hashed_" + password;
    }
}


