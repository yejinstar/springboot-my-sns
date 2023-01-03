package com.likelion.finalprojectsns.service;

import com.likelion.finalprojectsns.domain.dto.*;
import com.likelion.finalprojectsns.domain.entity.CommentEntity;
import com.likelion.finalprojectsns.domain.entity.PostEntity;
import com.likelion.finalprojectsns.domain.entity.UserEntity;
import com.likelion.finalprojectsns.exception.AppException;
import com.likelion.finalprojectsns.exception.ErrorCode;
import com.likelion.finalprojectsns.repository.CommentRepository;
import com.likelion.finalprojectsns.repository.PostRepository;
import com.likelion.finalprojectsns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentWriteResponse writing(Integer postId,CommentWriteRequest dto, String userName) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.POST_NOT_FOUND, String.format("postId:%d 이 없습니다.", postId));
                });

        UserEntity user = userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("userName:%s 이 없습니다.", userName));
                });

        CommentEntity comment = commentRepository.save(
                CommentEntity.builder()
                        .comment(dto.getComment())
                        .post(post)
                        .user(user)
                        .build()
        );

        return CommentWriteResponse.builder()
                .comment(comment.getComment())
                .id(comment.getId())
                .userName(comment.getUser().getUserName())
                .postId(comment.getPost().getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
