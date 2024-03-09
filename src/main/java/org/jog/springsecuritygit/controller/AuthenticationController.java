package org.jog.springsecuritygit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jog.springsecuritygit.dto.auth.AuthenticationRequest;
import org.jog.springsecuritygit.dto.auth.AuthenticationResponse;
import org.jog.springsecuritygit.persistence.entity.security.User;
import org.jog.springsecuritygit.service.auth.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt) {
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile() {
        User user = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/Authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest authenticationRequest
    ) {

        AuthenticationResponse rsp = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(rsp);
    }
}
