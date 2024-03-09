package org.jog.springsecuritygit.persistence.entity.security;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class GrantedPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


}
