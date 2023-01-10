package com.likelion.finalprojectsns.controller;

import com.likelion.finalprojectsns.domain.Response;
import com.likelion.finalprojectsns.domain.dto.*;
import com.likelion.finalprojectsns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /* Join */
    @PostMapping("/join")
    public ResponseEntity<Response<UserJoinResponse>> join(@RequestBody UserJoinRequest dto){
        UserJoinResponse userJoinResponse = userService.join(dto);
        return ResponseEntity.ok().body(Response.success(userJoinResponse));
    }

    /* Login */
    @PostMapping("/login")
    public ResponseEntity<Response<UserLoginResponse>> login(@RequestBody UserLoginRequest dto) {
        UserLoginResponse userLoginResponse = userService.login(dto);
        return ResponseEntity.ok().body(Response.success(userLoginResponse));
    }

}
