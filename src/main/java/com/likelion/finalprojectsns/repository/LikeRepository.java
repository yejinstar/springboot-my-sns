package com.likelion.finalprojectsns.repository;

import com.likelion.finalprojectsns.domain.entity.LikeEntity;
import com.likelion.finalprojectsns.domain.entity.PostEntity;
import com.likelion.finalprojectsns.domain.entity.UserEntity;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
//    Long countByPostAndDeleted_atIsNull(PostEntity post);

    @Query(nativeQuery = true, value = "select count(*) from `finalproj-db`.like_entity t where t.post_id = :post and t.deleted_at is null")
    Long countByPost(PostEntity post);

    Optional<LikeEntity> findByUserAndPost(UserEntity user, PostEntity post);

    List<LikeEntity> findAllByPost(PostEntity post);
}
