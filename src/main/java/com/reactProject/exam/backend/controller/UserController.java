package com.reactProject.exam.backend.controller;

import com.reactProject.exam.backend.dto.CheckUserDto;
import com.reactProject.exam.backend.dto.LoginDto;
import com.reactProject.exam.backend.dto.SessionDto;
import com.reactProject.exam.backend.dto.SignUpDto;
import com.reactProject.exam.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/methods")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpDto signUpDto) throws Exception {
       userService.signUp(signUpDto);
       return "회원가입 성공";
    }

    @PostMapping("/login")
    public SessionDto login(@RequestBody LoginDto loginDto) throws Exception {
        return userService.login(loginDto);
    }

    @GetMapping("/checkUserId")
    public CheckUserDto checkUserId(@RequestParam("userId") String userId) {
        return userService.isUserIdTaken(userId);
    }
    
}
