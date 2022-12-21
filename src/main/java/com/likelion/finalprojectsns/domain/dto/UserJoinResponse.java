package com.likelion.finalprojectsns.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserJoinResponse {
    private Integer userId;
    private String userName;
}
