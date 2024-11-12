package com.reactProject.exam.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SessionDto {

    private String userUuid;

    private String userId;

    private String username;

    private boolean success;

    private String message;

    public SessionDto(String userUuid, String userId, String username, boolean success, String message) {
        this.userUuid = userUuid;
        this.userId = userId;
        this.username = username;
        this.success = success;
        this.message = message;
    }

}
