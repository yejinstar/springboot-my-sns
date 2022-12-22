package com.likelion.finalprojectsns.service;

import com.likelion.finalprojectsns.domain.UserRole;
import com.likelion.finalprojectsns.domain.dto.UserJoinRequest;
import com.likelion.finalprojectsns.domain.dto.UserJoinResponse;
import com.likelion.finalprojectsns.domain.dto.UserLoginRequest;
import com.likelion.finalprojectsns.domain.dto.UserLoginResponse;
import com.likelion.finalprojectsns.domain.entity.UserEntity;
import com.likelion.finalprojectsns.exception.AppException;
import com.likelion.finalprojectsns.exception.ErrorCode;
import com.likelion.finalprojectsns.repository.UserRepository;
import com.likelion.finalprojectsns.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}") // Spring Annotation
    private String secretKey;

    private long expireTimeMs = 10000 * 60 * 60 ; // 1시간

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

    public UserLoginResponse login(UserLoginRequest dto) {

        UserEntity user = userRepository.findByUserName(dto.getUserName())
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("userName:%s 는 가입된 적이 없습니다.", dto.getUserName()));
                });

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "password가 잘못 되었습니다.");
        }

        return UserLoginResponse.builder()
                .token(JwtTokenUtil.createToken(user.getUserName(),secretKey,expireTimeMs))
                .build();
    }
}
