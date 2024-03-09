package org.jog.springsecuritygit.service;

import org.jog.springsecuritygit.dto.CategoryDTO;
import org.jog.springsecuritygit.persistence.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findOneById(Long categoryId);

    Category createOne(CategoryDTO categoryDTO);

    Category updateOneById(Long categoryId, CategoryDTO categoryDTO);

    Category disableOneById(Long categoryId);
}
