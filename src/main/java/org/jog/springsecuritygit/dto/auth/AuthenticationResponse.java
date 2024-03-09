package org.jog.springsecuritygit.dto.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class AuthenticationResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -5832615554733182856L;

    private String jwt;
}
