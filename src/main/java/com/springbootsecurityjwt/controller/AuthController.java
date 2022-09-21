package com.springbootsecurityjwt.controller;

import com.springbootsecurityjwt.entity.User;
import com.springbootsecurityjwt.payload.MessageResponse;
import com.springbootsecurityjwt.payload.RegisterRequest;
import com.springbootsecurityjwt.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest signUpRequest) {

        if (userRepository.existsByUsuario(signUpRequest.getUsuario())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("El usuario ya existe"));
        }

        User user = new User(signUpRequest.getUsuario(), encoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("usuario registrado satisfactoriamente"));
    }


}
