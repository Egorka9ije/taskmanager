package com.example.taskmanager.service;

import com.example.taskmanager.domain.Role;
import com.example.taskmanager.domain.RoleName;
import com.example.taskmanager.domain.User;
import com.example.taskmanager.dto.request.RegisterRequest;
import com.example.taskmanager.dto.response.UserResponse;
import com.example.taskmanager.exception.UserNotFoundException;
import com.example.taskmanager.exception.UsernameAlreadyExistsException;
import com.example.taskmanager.mapper.UserMapper;
import com.example.taskmanager.repository.RoleRepository;
import com.example.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse register(RegisterRequest request){
        if (userRepository.existsByUsername(request.username()) || userRepository.existsByEmail(request.email())){
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        User user = userMapper.toEntity(request);
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new IllegalStateException("Default role ROLE_USER not found"));
        user.setRoles(Set.of(userRole));
        user.setPassword(passwordEncoder.encode(request.password()));
        return userMapper.toResponse(userRepository.save(user));

    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username " + username));
        return userMapper.toResponse(user);
    }

}
