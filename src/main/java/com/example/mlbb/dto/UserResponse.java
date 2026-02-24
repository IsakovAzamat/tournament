package com.example.mlbb.dto;

import com.example.mlbb.enums.SystemRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String mlbbId;
    private SystemRole role;
}
