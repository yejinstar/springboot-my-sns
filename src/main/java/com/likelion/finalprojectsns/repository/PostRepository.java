package com.likelion.finalprojectsns.repository;

import com.likelion.finalprojectsns.domain.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
}
