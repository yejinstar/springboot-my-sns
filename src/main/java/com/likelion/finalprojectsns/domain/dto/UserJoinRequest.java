package com.likelion.finalprojectsns.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserJoinRequest {
    private String userName;
    private String password;
}
