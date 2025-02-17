package park_su_park.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import park_su_park.backend.dto.requestBody.CreateCommentRequest;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_do_id")
    private ToDo toDo;

    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime updateTime;

    //==생성자==//
    private Comment(String content) {
        this.content = content;
    }

    //==생성 메서드==//
    public static Comment create(CreateCommentRequest createCommentRequest) {
        return new Comment(createCommentRequest.getContent());
    }

    //==연관 관계 매핑 메서드==//
    public void setUser(User user){
        this.user = user;
        user.getComments().add(this);
    }

    public void setToDo(ToDo toDo) {
        this.toDo = toDo;
        toDo.getComments().add(this);
    }

    //==비즈니스 로직==//
    public void update(CreateCommentRequest updateCommentRequest) {
        this.content = updateCommentRequest.getContent();
    }
}
