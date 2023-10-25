package com.microservice.security.controller;

import com.microservice.security.config.jwt.JwtUtils;
import com.microservice.security.controller.dto.AuthRequest;
import com.microservice.security.controller.dto.UserDTO;
import com.microservice.security.entities.RoleEntity;
import com.microservice.security.entities.UserEntity;
import com.microservice.security.entities.enums.ERole;
import com.microservice.security.repository.UserRepository;
import com.microservice.security.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {

        UserEntity userEntity = UserEntity.builder()
                .email(userDTO.getEmail())
                .staffId(userDTO.getStaffId())
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles(Set.of(RoleEntity.builder()
                        .id(2L)
                        .name(ERole.valueOf(ERole.USER.name()))
                        .build()))
                .build();
        userService.save(userEntity);

        return ResponseEntity.ok("User Registered");

    }
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody String token) {
        return ResponseEntity.ok(jwtUtils.isTokenValid(token));
    }
    @GetMapping("/token")
    public ResponseEntity<?> generateToken(@Valid @RequestBody AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(jwtUtils.generateAccessToken(jwtUtils.generateAccessToken(authRequest.getUsername())));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Username or password");
        }


    }



}
