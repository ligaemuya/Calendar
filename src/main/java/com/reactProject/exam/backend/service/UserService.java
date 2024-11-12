package com.reactProject.exam.backend.service;

import com.reactProject.exam.backend.Entity.UserEntity;
import com.reactProject.exam.backend.dto.CheckUserDto;
import com.reactProject.exam.backend.dto.LoginDto;
import com.reactProject.exam.backend.dto.SessionDto;
import com.reactProject.exam.backend.dto.SignUpDto;
import com.reactProject.exam.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 회원가입
     *
     * @param signUpDto
     * @throws Exception
     */
    public void signUp(SignUpDto signUpDto) throws Exception {
        if (userRepository.findByUserId(signUpDto.getUserId()) != null) {
            throw new Exception("이미 존재하는 사용자");
        }

        UserEntity userEntity = UserEntity.builder()
                .userId(signUpDto.getUserId())
                .username(signUpDto.getUsername())
                .password(signUpDto.getPassword())
                .build();

        userRepository.save(userEntity);
    }

    /**
     * 로그인
     * 
     * @param loginDto
     * @return SessionDto
     * @throws Exception
     */
    public SessionDto login(LoginDto loginDto) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(loginDto.getUserId());
        if(userEntity != null && userEntity.getPassword().equals(loginDto.getPassword())) {
            return new SessionDto(userEntity.getUserUuid(), userEntity.getUserId(), userEntity.getUsername(), true, "로그인성공");
        } else {
            return new SessionDto(null, null, null, false, "ID와 비밀번호를 확인하세요");
        }
    }

    /**
     * 중복검증
     * @param userId
     * @return boolean
     */
    public CheckUserDto isUserIdTaken(String userId) {
        boolean isTaken = userRepository.findByUserId(userId) != null;
        String message = isTaken ? "이미 존재하는 사용자" : "회원가입 가능";
        return new CheckUserDto(isTaken, message);
    }
}
