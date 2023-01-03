package com.likelion.finalprojectsns.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDeleteResponse {
    private String message;
    private Integer id;
}
