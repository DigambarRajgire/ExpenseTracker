package com.interview.ExpenseManagerAPI.security;

import com.interview.ExpenseManagerAPI.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private  final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public JwtResponse login(JwtRequest jwtRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        User user = (User) authenticate.getPrincipal();

        String token = authUtil.generateToken(user);

        return new JwtResponse(user.getId() , token);
    }

    public SignUpResponse signUp(JwtRequest signUpRequest)  {
        User user = userRepository.findByUsername(signUpRequest.getUsername()).orElse(null);

        if(user != null) throw  new IllegalArgumentException("user already present");

        User new_user = userRepository.save(User.builder()
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build());

        SignUpResponse signUpResponse = SignUpResponse.builder()
                                        .id(new_user.getId())
                                        .username(new_user.getUsername())
                                        .build();

        return  signUpResponse;

    }
}
