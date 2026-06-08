package com.softtek.inventory_api.config;

import com.softtek.inventory_api.entity.Category;
import com.softtek.inventory_api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;


    @Override
    public void run(String... args) throws Exception {
        if(categoryRepository.count() == 0){

            categoryRepository.save(
                    Category.builder()
                            .name("Laptop")
                            .prefixCode("LAP")
                            .build()
            );

            categoryRepository.save(
                    Category.builder()
                            .name("Monitor")
                            .prefixCode("MON")
                            .build()
            );

            categoryRepository.save(
                    Category.builder()
                            .name("Celular")
                            .prefixCode("CEL")
                            .build()
            );
        }
    }


}