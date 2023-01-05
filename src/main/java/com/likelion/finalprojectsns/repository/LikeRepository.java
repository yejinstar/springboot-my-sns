package com.likelion.finalprojectsns.repository;

import com.likelion.finalprojectsns.domain.entity.LikeEntity;
import com.likelion.finalprojectsns.domain.entity.PostEntity;
import com.likelion.finalprojectsns.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {

//    Long countByPostAndDeleted_atIsNull(PostEntity post);
    Long countByPost(PostEntity post);

    Optional<LikeEntity> findByUserAndPost(UserEntity user, PostEntity post);
}
