package com.paksi.tokopediaassignment.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paksi.tokopediaassignment.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    public List<Inventory> findAllByIdIn(List<Long> ids);
}
