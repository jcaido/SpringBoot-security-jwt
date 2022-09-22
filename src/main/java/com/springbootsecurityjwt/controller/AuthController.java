package com.springbootsecurityjwt.controller;

import com.springbootsecurityjwt.entity.User;
import com.springbootsecurityjwt.security.jwt.JwtTokenUtil;
import com.springbootsecurityjwt.security.payload.JwtResponse;
import com.springbootsecurityjwt.security.payload.LoginRequest;
import com.springbootsecurityjwt.security.payload.MessageResponse;
import com.springbootsecurityjwt.security.payload.RegisterRequest;
import com.springbootsecurityjwt.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authManager, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication =authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsuario(), loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
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
