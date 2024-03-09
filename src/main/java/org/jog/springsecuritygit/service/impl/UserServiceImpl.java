package org.jog.springsecuritygit.service.impl;

import lombok.RequiredArgsConstructor;
import org.jog.springsecuritygit.dto.UserDTO;
import org.jog.springsecuritygit.exception.InvalidPasswordException;
import org.jog.springsecuritygit.exception.ObjectNotFoundException;
import org.jog.springsecuritygit.persistence.entity.security.Role;
import org.jog.springsecuritygit.persistence.entity.security.User;
import org.jog.springsecuritygit.persistence.repository.security.UserRepository;
import org.jog.springsecuritygit.service.RoleService;
import org.jog.springsecuritygit.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public User registeredOneUser(UserDTO newUser) {

        validationPassword(newUser);

        User user = new User();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));

        user.setName(newUser.getName());
        user.setUsername(newUser.getUsername());

        Role role = roleService.findByDefaultRole()
                .orElseThrow(()->new ObjectNotFoundException("Role not found"));

        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    private void validationPassword(UserDTO userDTO) {

        if (!StringUtils.hasText(userDTO.getPassword()) || !StringUtils.hasText(userDTO.getRepeatPassword())) {
            throw new InvalidPasswordException("Password is required");
        }
        if (!userDTO.getPassword().equals(userDTO.getRepeatPassword())) {
            throw new InvalidPasswordException("Passwords don't match");
        }
    }
}
