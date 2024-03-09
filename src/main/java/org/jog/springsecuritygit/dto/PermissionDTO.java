package org.jog.springsecuritygit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class PermissionDTO implements Serializable {


    @Serial
    private static final long serialVersionUID = 1814379678559702681L;


    @NotBlank
    private String operation;

    @NotBlank
    private String role;
}
