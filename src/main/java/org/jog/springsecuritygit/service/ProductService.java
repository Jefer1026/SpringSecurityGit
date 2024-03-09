package org.jog.springsecuritygit.service;

import org.jog.springsecuritygit.dto.ProductDTO;
import org.jog.springsecuritygit.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findOneById(Long productId);

    Product createOne(ProductDTO productDTO);

    Product updateOneById(Long productId, ProductDTO productDTO);

    Product disableOneById(Long productId);
}
