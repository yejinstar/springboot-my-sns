package com.likelion.finalprojectsns.domain.entity;

import com.likelion.finalprojectsns.domain.dto.CommentWriteRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public void editComment(CommentWriteRequest commentWriteRequest) {
        this.comment = commentWriteRequest.getComment();
    }
}
