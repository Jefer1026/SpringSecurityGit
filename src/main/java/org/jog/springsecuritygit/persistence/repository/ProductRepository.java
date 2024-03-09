package org.jog.springsecuritygit.persistence.repository;

import org.jog.springsecuritygit.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
