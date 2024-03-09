package org.jog.springsecuritygit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jog.springsecuritygit.dto.RegisteredUser;
import org.jog.springsecuritygit.dto.UserDTO;
import org.jog.springsecuritygit.persistence.entity.security.User;
import org.jog.springsecuritygit.service.auth.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<RegisteredUser> registerOne(@RequestBody @Valid UserDTO newUser){

        RegisteredUser registeredUser = authenticationService.registerOneCustomer(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(Arrays.asList());
    }
}
