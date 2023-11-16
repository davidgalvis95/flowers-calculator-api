package com.example.flowerscalculatorapi.respository;

import com.example.flowerscalculatorapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
