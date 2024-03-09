package org.jog.springsecuritygit.service.impl;

import lombok.RequiredArgsConstructor;
import org.jog.springsecuritygit.dto.PermissionDTO;
import org.jog.springsecuritygit.dto.ShowPermission;
import org.jog.springsecuritygit.exception.ObjectNotFoundException;
import org.jog.springsecuritygit.persistence.entity.security.GrantedPermission;
import org.jog.springsecuritygit.persistence.entity.security.Operation;
import org.jog.springsecuritygit.persistence.entity.security.Role;
import org.jog.springsecuritygit.persistence.repository.security.OperationRepository;
import org.jog.springsecuritygit.persistence.repository.security.PermissionRepository;
import org.jog.springsecuritygit.persistence.repository.security.RoleRepository;
import org.jog.springsecuritygit.service.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final OperationRepository operationRepository;
    private final RoleRepository roleRepository;

    @Override
    public Page<ShowPermission> findAll(Pageable pageable) {
        return permissionRepository.findAll(pageable)
                .map(this::mapEntityToShowDTO);
    }

    @Override
    public Optional<ShowPermission> findById(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .map(this::mapEntityToShowDTO);
    }

    @Override
    public ShowPermission createOne(PermissionDTO permissionDTO) {

        GrantedPermission newPermission = new GrantedPermission();

        Operation operation = operationRepository.findByName(permissionDTO.getOperation())
                .orElseThrow(() -> new ObjectNotFoundException("Operation not found"));

        newPermission.setOperation(operation);

        Role role = roleRepository.findByName(permissionDTO.getRole())
                .orElseThrow(()-> new ObjectNotFoundException("role not found"));

        newPermission.setRole(role);


        permissionRepository.save(newPermission);

        return this.mapEntityToShowDTO(newPermission);
    }

    @Override
    public ShowPermission deleteOneById(Long permissionId) {

        GrantedPermission permission = permissionRepository.findById(permissionId)
                .orElseThrow(()-> new  ObjectNotFoundException("permission not found"));

        permissionRepository.delete(permission);


        return  this.mapEntityToShowDTO(permission);
    }

    private ShowPermission mapEntityToShowDTO(GrantedPermission grantedPermission) {

        ShowPermission showDTO = new ShowPermission();

        showDTO.setId(grantedPermission.getId());
        showDTO.setRole(grantedPermission.getRole().getName());
        showDTO.setOperation(grantedPermission.getOperation().getName());
        showDTO.setHttpMethod(grantedPermission.getOperation().getHttpMethod());
        showDTO.setModule(grantedPermission.getOperation().getModule().getName());

        return showDTO;


    }
}
