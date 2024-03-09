package org.jog.springsecuritygit.service.auth;

import lombok.RequiredArgsConstructor;
import org.jog.springsecuritygit.dto.RegisteredUser;
import org.jog.springsecuritygit.dto.UserDTO;
import org.jog.springsecuritygit.dto.auth.AuthenticationRequest;
import org.jog.springsecuritygit.dto.auth.AuthenticationResponse;
import org.jog.springsecuritygit.exception.ObjectNotFoundException;
import org.jog.springsecuritygit.persistence.entity.security.User;
import org.jog.springsecuritygit.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    public boolean validateToken(String jwt) {
        try {
            jwtService.extractSubject(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        );

        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findByUsername(request.getUsername()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));

        AuthenticationResponse authRsp = new AuthenticationResponse();
        authRsp.setJwt(jwt);

        return authRsp;
    }

    public User findLoggedInUser() {
        UsernamePasswordAuthenticationToken auth =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext();

        String username = (String) auth.getPrincipal();

        return userService.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found User: " + username));
    }

    public RegisteredUser registerOneCustomer(UserDTO newUser) {

        User user = userService.registeredOneUser(newUser);

        RegisteredUser userRegistered = new RegisteredUser();

        userRegistered.setId(user.getId());
        userRegistered.setName(user.getName());
        userRegistered.setUsername(user.getUsername());
        userRegistered.setRole(user.getRole().getName());
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userRegistered.setJwt(jwt);

        return userRegistered;
    }

}
