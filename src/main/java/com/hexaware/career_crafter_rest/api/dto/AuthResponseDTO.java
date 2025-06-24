package com.hexaware.career_crafter_rest.api.dto;

import com.hexaware.career_crafter_rest.api.entity.Users;

public class AuthResponseDTO {
    private String token;
    private Users user;

    public AuthResponseDTO(String token, Users user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public Users getUser() {
        return user;
    }
}

