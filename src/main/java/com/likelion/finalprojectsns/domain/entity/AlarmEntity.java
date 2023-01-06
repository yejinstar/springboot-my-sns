package com.likelion.finalprojectsns.domain.entity;

import com.likelion.finalprojectsns.domain.AlarmType;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at is null")
public class AlarmEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType; // like인지 comment인지

    private LocalDate deleted_at;

    private Integer fromUserId;
    private Integer targetId; // post.id
    private String text;

    public void deleteAlarm() {
        this.deleted_at = LocalDate.now();
    }
}
