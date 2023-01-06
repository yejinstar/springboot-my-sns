package com.likelion.finalprojectsns.domain.entity;

import com.likelion.finalprojectsns.domain.UserRole;
import com.likelion.finalprojectsns.domain.dto.PostPostingRequest;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at is null")
public class PostEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String body;
    private LocalDateTime deletedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public void editPost(PostPostingRequest postPostingRequest) {
        this.title = postPostingRequest.getTitle();
        this.body = postPostingRequest.getBody();
    }

    public void deletePost(){
        this.deletedAt = LocalDateTime.now();
    }
}
