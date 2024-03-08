package com.grupo1.ganaderiagrupo1.security;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;

}
