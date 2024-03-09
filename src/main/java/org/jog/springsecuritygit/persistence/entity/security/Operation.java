package org.jog.springsecuritygit.persistence.entity.security;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String httpMethod;

    private String path;

    private boolean permitAll;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}
