package com.example.atb.Service.Impl;


import com.example.atb.Entities.Auth.AuthenticationRequest;
import com.example.atb.Entities.Auth.AuthenticationResponse;
import com.example.atb.Entities.Auth.RegisterRequest;
import com.example.atb.Entities.Privilege;
import com.example.atb.Entities.Role;
import com.example.atb.Entities.User;
import com.example.atb.Repository.UserRepository;
import com.example.atb.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {


        var user = User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .status(true)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .privilege(Privilege.NONE)
                .build();
        utilisateurRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword())
        );
        var user = utilisateurRepository.findByEmail(authenticationRequest.getEmail()).get();
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }
}