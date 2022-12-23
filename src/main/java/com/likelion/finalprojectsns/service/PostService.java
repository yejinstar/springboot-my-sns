package com.likelion.finalprojectsns.service;

import com.likelion.finalprojectsns.domain.dto.PostPostingRequest;
import com.likelion.finalprojectsns.domain.dto.PostPostingResponse;
import com.likelion.finalprojectsns.domain.entity.PostEntity;
import com.likelion.finalprojectsns.domain.entity.UserEntity;
import com.likelion.finalprojectsns.exception.AppException;
import com.likelion.finalprojectsns.exception.ErrorCode;
import com.likelion.finalprojectsns.repository.PostRepository;
import com.likelion.finalprojectsns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostPostingResponse posting(PostPostingRequest dto, String userName) {
        UserEntity user = userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("userName:%s 이 없습니다.", userName));
                });

        PostEntity post = postRepository.save(
                PostEntity.builder()
                        .title(dto.getTitle())
                        .body(dto.getBody())
                        .user(user)
                        .build()
        );
        return PostPostingResponse.builder()
                .postId(post.getId())
                .message("포스트 등록 완료")
                .build();
    }

    public PostPostingResponse edit(Integer postId, PostPostingRequest dto, String userName) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.POST_NOT_FOUND, String.format("postId:%d 이 없습니다.", postId));
                });

        UserEntity user = userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("userName:%s 이 없습니다.", userName));
                });

        if (!(post.getUser().getId() == user.getId())) {
            throw new AppException(ErrorCode.INVALID_PERMISSION,
                    String.format("postId:%d 의 작성자와 userName:%s 의 아이디 %d 가 일치하지 않습니다", postId, userName,user.getId() ));
        }

        post.setBody(dto.getBody());
        post.setTitle(dto.getTitle());

        postRepository.save(post);
        return PostPostingResponse.builder()
                .postId(post.getId())
                .message("포스트 수정 완료")
                .build();
    }

    public PostPostingResponse delete(Integer postId, String userName) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.POST_NOT_FOUND, String.format("postId:%d 이 없습니다.", postId));
                });

        UserEntity user = userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("userName:%s 이 없습니다.", userName));
                });

        if (!(post.getUser().getId() == user.getId())) {
            throw new AppException(ErrorCode.INVALID_PERMISSION,
                    String.format("postId:%d 의 작성자 아이디 %d 와 userName:%s 의 아이디 %d 가 일치하지 않습니다", postId, post.getUser().getId(),userName,user.getId() ));
        }

        postRepository.delete(post);
        return PostPostingResponse.builder()
                .postId(post.getId())
                .message("포스트 삭제 완료")
                .build();
    }
}
