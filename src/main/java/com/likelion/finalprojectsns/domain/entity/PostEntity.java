package com.likelion.finalprojectsns.domain.entity;

import com.likelion.finalprojectsns.domain.UserRole;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
