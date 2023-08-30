package com.paksi.tokopediaassignment.inventory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paksi.tokopediaassignment.inventory.model.Inventory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Inventory create(Inventory inventory) {
        return this.inventoryRepository.save(inventory);
    }

    public Inventory findById(Inventory inventory) {
        return this.inventoryRepository.findById(inventory.getId()).orElseThrow(InventoryNotFoundException::new);
    }

    public List<Inventory> findAllIn(List<Long> inventoryIds) {
        return this.inventoryRepository.findAllByIdIn(inventoryIds);
    }
}
