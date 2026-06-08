package com.softtek.inventory_api.auth;

public record LoginRequest(
        String username,
        String password
) {
}