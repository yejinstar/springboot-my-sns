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

    /* Comment 전체 조회*/
    public CommentPageInfoResponse get(Integer postId, Pageable pageable, String userName) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.POST_NOT_FOUND, String.format("postId:%d 이 없습니다.", postId));
                });

        UserEntity user = userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("userName:%s 이 없습니다.", userName));
                });

        Page<CommentEntity> comments = commentRepository.findAll(pageable);
        Page<CommentGetResponse> commentGetResponses = comments.map(
                comment -> CommentGetResponse.builder()
                        .id(comment.getId())
                        .comment(comment.getComment())
                        .userName(comment.getUser().getUserName())
                        .postId(comment.getPost().getId())
                        .createdAt(comment.getCreatedAt())
                        .build()
        );

        CommentPageInfoResponse commentPageInfoResponse = CommentPageInfoResponse.builder()
                .content(commentGetResponses.getContent())
                .pageable(commentGetResponses.getPageable())
                .last(commentGetResponses.hasNext())
                .totalPages(commentGetResponses.getTotalPages())
                .totalElements(commentGetResponses.getTotalElements())
                .size(commentGetResponses.getSize())
                .number(commentGetResponses.getNumber())
                .sort(commentGetResponses.getSort())
                .numberOfElements(commentGetResponses.getNumberOfElements())
                .first(commentGetResponses.isFirst())
                .empty(commentGetResponses.isEmpty())
                .build();
        return commentPageInfoResponse;
    }

    /* Comment 작성 */
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
    /* Comment 수정 */
    public CommentWriteResponse edit(Integer postId, Integer id, CommentWriteRequest dto, String userName) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.POST_NOT_FOUND, String.format("postId:%d 이 없습니다.", postId));
                });

        UserEntity user = userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("userName:%s 이 없습니다.", userName));
                });

        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() ->{
                    throw new AppException(ErrorCode.COMMENT_NOT_FOUND, String.format("commentId:%d 이 없습니다.", id));
                });

        if (!(comment.getUser().getId() == user.getId())) {
            throw new AppException(ErrorCode.INVALID_PERMISSION,
                    String.format("commentId:%d 의 작성자 userName:%s 의 아이디 %d 가 일치하지 않습니다", id, userName,user.getId() ));
        }

        comment.editComment(dto);
        commentRepository.save(comment);
        return CommentWriteResponse.builder()
                .comment(comment.getComment())
                .id(comment.getId())
                .userName(comment.getUser().getUserName())
                .postId(comment.getPost().getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
    /* Comment 삭제 */
    public CommentDeleteResponse delete(Integer postId, Integer id, String userName) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.POST_NOT_FOUND, String.format("postId:%d 이 없습니다.", postId));
                });

        UserEntity user = userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("userName:%s 이 없습니다.", userName));
                });

        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() ->{
                    throw new AppException(ErrorCode.COMMENT_NOT_FOUND, String.format("commentId:%d 이 없습니다.", id));
                });

        if (!(comment.getUser().getId() == user.getId())) {
            throw new AppException(ErrorCode.INVALID_PERMISSION,
                    String.format("commentId:%d 의 작성자 userName:%s 의 아이디 %d 가 일치하지 않습니다", id, userName,user.getId() ));
        }

        commentRepository.delete(comment);
        return CommentDeleteResponse.builder()
                .message("댓글 삭제 완료.")
                .id(comment.getId())
                .build();
    }

}
