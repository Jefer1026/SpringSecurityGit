package org.jog.springsecuritygit.persistence.entity.security;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Module {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String basePath;
}
