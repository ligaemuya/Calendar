package com.reactProject.exam.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    private String userId;  // 사용자 입력 ID

    private String password;  // 비밀번호

}
