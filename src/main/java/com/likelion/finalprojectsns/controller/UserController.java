package com.likelion.finalprojectsns.controller;

import com.likelion.finalprojectsns.domain.Response;
import com.likelion.finalprojectsns.domain.dto.UserJoinRequest;
import com.likelion.finalprojectsns.domain.dto.UserJoinResponse;
import com.likelion.finalprojectsns.domain.dto.UserLoginRequest;
import com.likelion.finalprojectsns.domain.dto.UserLoginResponse;
import com.likelion.finalprojectsns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest dto){
        UserJoinResponse userJoinResponse = userService.join(dto);
        return Response.success(userJoinResponse);
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest dto) {
        UserLoginResponse userLoginResponse = userService.login(dto);
        return Response.success(userLoginResponse);
    }
}
