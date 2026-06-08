package com.softtek.inventory_api.controller;

import com.softtek.inventory_api.dto.AssetRequestDTO;
import com.softtek.inventory_api.dto.AssetResponseDTO;
import com.softtek.inventory_api.service.AssetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    public ResponseEntity<AssetResponseDTO> save(@RequestBody @Valid AssetRequestDTO dto) {
        AssetResponseDTO response = assetService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<AssetResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt")
            String sortBy) {

        return ResponseEntity.ok(assetService.findAll(
                        page,
                        size,
                        sortBy
                )
        );
    }
}
