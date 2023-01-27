package com.likelion.finalprojectsns.domain.entity;

import com.likelion.finalprojectsns.domain.UserRole;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_user")
@Where(clause = "deleted_at is null")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "user_type")
    @Enumerated(value = EnumType.STRING)
    private UserRole userType;

    @CreatedDate
    @Column(name = "registered_at", updatable = false)
    private String registeredAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    private String modifiedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
