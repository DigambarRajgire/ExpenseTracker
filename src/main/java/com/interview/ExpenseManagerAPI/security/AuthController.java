package com.interview.ExpenseManagerAPI.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){

        return ResponseEntity.ok(authService.login(jwtRequest));
    }



    @PostMapping("/auth")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody JwtRequest signUpRequest){

        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }


}
