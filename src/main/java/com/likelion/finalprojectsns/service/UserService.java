package com.likelion.finalprojectsns.service;

import com.likelion.finalprojectsns.domain.UserRole;
import com.likelion.finalprojectsns.domain.dto.UserJoinRequest;
import com.likelion.finalprojectsns.domain.dto.UserJoinResponse;
import com.likelion.finalprojectsns.domain.entity.UserEntity;
import com.likelion.finalprojectsns.exception.AppException;
import com.likelion.finalprojectsns.exception.ErrorCode;
import com.likelion.finalprojectsns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserJoinResponse join(UserJoinRequest dto) {
        userRepository.findByUserName(dto.getUserName())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.DUPLICATED_USER_NAME, String.format("userName:%s 는 이미 있습니다.", dto.getUserName()));
                });
        UserEntity savedUser = userRepository.save(
                UserEntity.builder()
                        .userName(dto.getUserName())
                        .password(encoder.encode(dto.getPassword()))
                        .role(UserRole.USER)
                        .registeredAt(new Timestamp(System.currentTimeMillis()))
                        .build()
        );
        return UserJoinResponse.builder()
                .userId(savedUser.getId())
                .userName(savedUser.getUserName())
                .build();
    }
}
