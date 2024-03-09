package org.jog.springsecuritygit.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class ShowPermission implements Serializable {


    @Serial
    private static final long serialVersionUID = -6985535475592480306L;


    private Long id;

    private String httpMethod;

    private String module;

    private String operation;

    private String role;
}
