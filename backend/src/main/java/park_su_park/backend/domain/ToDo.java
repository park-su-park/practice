package park_su_park.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import park_su_park.backend.dto.requestDto.RequestToDoDto;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)

public class ToDo {
    @Id
    @GeneratedValue
    @Column(name = "to_do_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")

    private User user;


    @OneToMany(mappedBy = "toDo")
    private List<Comment> comments = new ArrayList<>();

    private String title;

    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime updatedTime;


    //==생성 메소드==//
    public static ToDo create(User user, RequestToDoDto requestToDoDto) {
        ToDo toDo = new ToDo();
        toDo.setTitle(requestToDoDto.getTitle());
        toDo.setContent(requestToDoDto.getContent());
        toDo.setUser(user);
        return toDo;
    }

    //==연관관계 설정 메소드==//
    public void setUser(User user) {
        this.user = user;
        user.getToDoes().add(this);
    }
}
