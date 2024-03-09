package org.jog.springsecuritygit.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7351423564618273862L;

    @Size(min = 4)
    private String name;

    @Size(min = 4)
    private String username;

    @Size(min = 8)
    private String password;

    @Size(min = 8)
    private String repeatPassword;
}
