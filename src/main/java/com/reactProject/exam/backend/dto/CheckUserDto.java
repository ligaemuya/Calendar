package com.reactProject.exam.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class CheckUserDto {
    private boolean success;
    private String message;

    public CheckUserDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
