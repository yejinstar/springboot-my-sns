package com.likelion.finalprojectsns.configuration;

import com.likelion.finalprojectsns.exception.ErrorCode;
import com.likelion.finalprojectsns.exception.ExceptionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null) {
            log.error("토큰이 존재하지 않습니다.");
            ErrorCode errorCode = ErrorCode.TOKEN_NOT_FOUND;

            ExceptionManager.setErrorResponse(response, errorCode);
        }
    }
}
