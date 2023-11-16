package com.example.flowerscalculatorapi.respository;

import com.example.flowerscalculatorapi.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    List<Inventory> findInventoryByCompanyId(Integer companyId);
}
