package com.likelion.finalprojectsns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.finalprojectsns.domain.dto.UserJoinRequest;
import com.likelion.finalprojectsns.domain.dto.UserJoinResponse;
import com.likelion.finalprojectsns.exception.AppException;
import com.likelion.finalprojectsns.exception.ErrorCode;
import com.likelion.finalprojectsns.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    UserService userService;

    @Test
    @WithMockUser
    @DisplayName("join success")
    void join_success() throws Exception {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("userName")
                .password("1234")
                .build();
        UserJoinResponse userJoinResponse = UserJoinResponse.builder()
                .userId(1)
                .userName("userName")
                .build();

        when(userService.join(any())).thenReturn(userJoinResponse);

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .content(objectMapper.writeValueAsBytes(userJoinRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @WithMockUser
    @DisplayName("join fail - userName 중복")
    void join_fail() throws Exception {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("userName")
                .password("1234")
                .build();

        when(userService.join(any()))
                .thenThrow(new AppException(ErrorCode.DUPLICATED_USER_NAME, ErrorCode.DUPLICATED_USER_NAME.getMessage()));

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .content(objectMapper.writeValueAsBytes(userJoinRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(ErrorCode.DUPLICATED_USER_NAME.getStatus().value()))
                .andDo(print());
    }

    @Test
    @DisplayName("login success")
    void login_success() throws Exception {

    }

    @Test
    @DisplayName("login fail1 - userName 없음")
    void login_fail1() throws Exception {

    }

    @Test
    @DisplayName("login fail2 - password 틀림")
    void login_fail2() throws Exception {

    }
}