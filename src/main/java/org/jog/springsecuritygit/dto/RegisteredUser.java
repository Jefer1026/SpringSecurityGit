package org.jog.springsecuritygit.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class RegisteredUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 5478084835515881520L;

    private Long id;
    private String name;
    private String username;
    private String jwt;
    private String role;
}
