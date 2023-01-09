package com.likelion.finalprojectsns.service;

import com.likelion.finalprojectsns.domain.AlarmType;
import com.likelion.finalprojectsns.domain.entity.AlarmEntity;
import com.likelion.finalprojectsns.domain.entity.LikeEntity;
import com.likelion.finalprojectsns.domain.entity.PostEntity;
import com.likelion.finalprojectsns.domain.entity.UserEntity;
import com.likelion.finalprojectsns.exception.AppException;
import com.likelion.finalprojectsns.exception.ErrorCode;
import com.likelion.finalprojectsns.repository.AlarmRepository;
import com.likelion.finalprojectsns.repository.LikeRepository;
import com.likelion.finalprojectsns.repository.PostRepository;
import com.likelion.finalprojectsns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final AlarmRepository alarmRepository;


    public String like(String userName, Integer postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.POST_NOT_FOUND, String.format("postId:%d 이 없습니다.", postId));
                });

        UserEntity user = userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("userName:%s 이 없습니다.", userName));
                });

        boolean flag = true;
        String returnStr = "좋아요를 눌렀습니다.";

        Optional<LikeEntity> like = likeRepository.findByUserAndPost(user, post);
        if (like.isPresent()){
            flag = false;
            LikeEntity savedLike = like.get();
            if (savedLike.getDeleted_at() == null) {
                returnStr = "좋아요를 취소했습니다.";
                savedLike.cancelLike();
            } else {
                savedLike.reLike();
            }
            likeRepository.save(savedLike);
        }

        if(flag){
            likeRepository.save(
                    LikeEntity.builder()
                            .user(user)
                            .post(post)
                            .build()
            );

            alarmRepository.save(
                    AlarmEntity.builder()
                            .alarmType(AlarmType.NEW_LIKE_ON_POST)
                            .fromUserId(user.getId())
                            .targetId(post.getId())
                            .text(AlarmType.NEW_LIKE_ON_POST.getMessage())
                            .user(post.getUser())
                            .build()
            );
        }
        return returnStr;
    }


    public Long likeCount(/*String userName,*/ Integer postId) {
        /*userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("userName:%s 이 없습니다.", userName));
                });*/

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.POST_NOT_FOUND, String.format("postId:%d 이 없습니다.", postId));
                });

        Long likeCount = likeRepository.countByPost(post);
        if (likeCount == null) {
            likeCount = 0L;
        }
        return likeCount;
    }
}
