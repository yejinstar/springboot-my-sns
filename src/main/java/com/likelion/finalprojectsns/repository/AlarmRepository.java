package com.likelion.finalprojectsns.repository;

import com.likelion.finalprojectsns.domain.entity.AlarmEntity;
import com.likelion.finalprojectsns.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlarmRepository extends JpaRepository<AlarmEntity, Integer> {
    Page<AlarmEntity> findAByUser(UserEntity user, Pageable pageable);
}
