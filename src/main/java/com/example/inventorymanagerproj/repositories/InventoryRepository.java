package com.example.inventorymanagerproj.repositories;

import com.example.inventorymanagerproj.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByQuantityLessThan(Integer quantity);
    Optional<Inventory> findByProductId(Long productId);
} 