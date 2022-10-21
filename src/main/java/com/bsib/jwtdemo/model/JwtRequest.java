package com.bsib.jwtdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class JwtRequest {
    private String username;
    private String password;
}
