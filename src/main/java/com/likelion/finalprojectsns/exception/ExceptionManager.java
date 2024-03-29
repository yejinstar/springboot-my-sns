package com.likelion.finalprojectsns.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.finalprojectsns.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionManager {
    public static void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatus().value());
        response.setContentType("application/json;charset=UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();

        response.getWriter().write(objectMapper.writeValueAsString(
                new ResponseEntity(Response.error(
                        ErrorInfo.builder()
                                .errorCode(errorCode)
                                .errorMsg(errorCode.getMessage())
                                .build()
                ), errorCode.getStatus())
        ));

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e));
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> AppExceptionHandler(AppException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(
                Response.error(
                        ErrorInfo.builder()
                                .errorCode(e.getErrorCode())
                                .errorMsg(e.getMessage())
                                .build()
                )
        );
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> sqlExceptionHandler(SQLException e){
        ErrorInfo errorInfo = new ErrorInfo(ErrorCode.DATABASE_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(errorInfo));
    }
}
