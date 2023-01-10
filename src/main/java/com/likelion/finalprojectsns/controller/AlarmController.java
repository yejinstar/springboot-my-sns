package com.likelion.finalprojectsns.controller;

import com.likelion.finalprojectsns.domain.Response;
import com.likelion.finalprojectsns.domain.dto.AlarmPageInfoResponse;
import com.likelion.finalprojectsns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AlarmController {
    private final UserService userService;
    @GetMapping("/alarms")
    public ResponseEntity<Response<AlarmPageInfoResponse>> getAlarm(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                                    Authentication authentication) {
        String userName = authentication.getName();
        AlarmPageInfoResponse alarmPageInfoResponse = userService.getAlarm(pageable, userName);
        return ResponseEntity.ok().body(Response.success(alarmPageInfoResponse));
    }
}
