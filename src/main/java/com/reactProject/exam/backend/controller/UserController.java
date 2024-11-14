package com.reactProject.exam.backend.controller;

import com.reactProject.exam.backend.dto.CheckUserDto;
import com.reactProject.exam.backend.dto.LoginDto;
import com.reactProject.exam.backend.dto.SessionDto;
import com.reactProject.exam.backend.dto.SignUpDto;
import com.reactProject.exam.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/methods")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "회원가입", description = "이름, ID, 패스워드를 작성하여 회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/signup")
    public String signUp(@Parameter(description = "회원가입 정보")  @RequestBody SignUpDto signUpDto) throws Exception {
       userService.signUp(signUpDto);
       return "회원가입 성공";
    }

    @Operation(summary = "로그인", description = "ID, 패스워드를 입력하여 로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/login")
    public SessionDto login(@Parameter(description = "계정 정보") @RequestBody LoginDto loginDto) throws Exception {
        return userService.login(loginDto);
    }

    @Operation(summary = "ID중복확인", description = "회원가입시 ID를 입력후 중복확인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/checkUserId")
    public CheckUserDto checkUserId(@Parameter(description = "가입ID") @RequestParam("userId") String userId) {
        return userService.isUserIdTaken(userId);
    }
    
}
