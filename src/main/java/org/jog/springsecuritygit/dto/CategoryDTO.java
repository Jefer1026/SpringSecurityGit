package org.jog.springsecuritygit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class CategoryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3518901311927346903L;

    @NotBlank
    private String name;
}
