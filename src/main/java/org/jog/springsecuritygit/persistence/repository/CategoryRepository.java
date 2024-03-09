package org.jog.springsecuritygit.persistence.repository;

import org.jog.springsecuritygit.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
