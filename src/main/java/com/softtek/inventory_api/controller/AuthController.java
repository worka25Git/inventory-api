package com.softtek.inventory_api.controller;

import com.softtek.inventory_api.auth.LoginRequest;
import com.softtek.inventory_api.auth.LoginResponse;
import com.softtek.inventory_api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        if ("admin".equals(request.username())
                && "admin123".equals(request.password())) {

            String token =
                    jwtService.generateToken(
                            "admin",
                            "ADMIN"
                    );

            return ResponseEntity.ok(
                    new LoginResponse(token)
            );
        }

        if ("user".equals(request.username())
                && "user123".equals(request.password())) {

            String token =
                    jwtService.generateToken(
                            "user",
                            "USER"
                    );

            return ResponseEntity.ok(
                    new LoginResponse(token)
            );
        }

        return ResponseEntity.badRequest().build();
    }

}