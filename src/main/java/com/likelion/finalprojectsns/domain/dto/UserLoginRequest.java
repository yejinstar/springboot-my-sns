package com.likelion.finalprojectsns.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginRequest {
    private String userName;
    private String password;
}
