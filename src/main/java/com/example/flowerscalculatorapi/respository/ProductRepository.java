package com.example.flowerscalculatorapi.respository;

import com.example.flowerscalculatorapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
