package com.likelion.finalprojectsns.repository;

import com.likelion.finalprojectsns.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
