package com.likelion.finalprojectsns.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorInfo {
    private ErrorCode errorCode;
    private String errorMsg;
}
