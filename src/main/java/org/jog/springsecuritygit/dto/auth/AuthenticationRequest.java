package org.jog.springsecuritygit.dto.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class AuthenticationRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -8888830890515526668L;

    private String username;

    private String password;
}
