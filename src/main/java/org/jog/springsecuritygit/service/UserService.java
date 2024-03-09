package org.jog.springsecuritygit.service;

import org.jog.springsecuritygit.dto.UserDTO;
import org.jog.springsecuritygit.persistence.entity.security.User;

import java.util.Optional;

public interface UserService {

    User registeredOneUser(UserDTO newUser);

    Optional<User> findByUsername(String username);
}
