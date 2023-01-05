package com.likelion.finalprojectsns.controller;

import com.likelion.finalprojectsns.domain.Response;
import com.likelion.finalprojectsns.domain.dto.*;
import com.likelion.finalprojectsns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/alarms")
    public ResponseEntity<Response<AlarmPageInfoResponse>> getAlarm(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                                Authentication authentication) {
        String userName = authentication.getName();
        AlarmPageInfoResponse alarmPageInfoResponse = userService.getAlarm(pageable, userName);
        return ResponseEntity.ok().body(Response.success(alarmPageInfoResponse));
    }
}
