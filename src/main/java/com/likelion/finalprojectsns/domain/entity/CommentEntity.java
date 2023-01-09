package com.likelion.finalprojectsns.domain.entity;

import com.likelion.finalprojectsns.domain.dto.CommentWriteRequest;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at is null")
public class CommentEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private LocalDateTime deletedAt;
    public void editComment(CommentWriteRequest commentWriteRequest) {
        this.comment = commentWriteRequest.getComment();
    }

    public void deleteComment() {
        this.deletedAt = LocalDateTime.now();
    }

    public void deleteCommnetByPostDelete() {
        this.deletedAt = LocalDateTime.now();
    }
}
