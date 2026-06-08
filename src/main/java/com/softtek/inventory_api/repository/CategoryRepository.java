package com.softtek.inventory_api.repository;

import com.softtek.inventory_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
