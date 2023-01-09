package com.likelion.finalprojectsns.repository;

import com.likelion.finalprojectsns.domain.entity.CommentEntity;
import com.likelion.finalprojectsns.domain.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    Page<CommentEntity> findAllByPost(PostEntity post, Pageable pageable);

    List<CommentEntity> findAllByPost(PostEntity post);
}
