package com.reactProject.exam.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDto {

    private String userId;  // 사용자 입력 ID

    private String username;  // 사용자 이름

    private String password;  // 비밀번호
}
