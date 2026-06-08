package com.softtek.inventory_api.repository;

import com.softtek.inventory_api.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AssetRepository  extends JpaRepository<Asset, UUID>,
        JpaSpecificationExecutor<Asset> {

    boolean existsBySerialNumber(String serialNumber);

    long countByCategoryId(Long categoryId);
}
