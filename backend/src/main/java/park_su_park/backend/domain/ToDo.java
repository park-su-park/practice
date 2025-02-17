package park_su_park.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import park_su_park.backend.dto.requestBody.CreateToDoRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor
public class ToDo {
    @Id @GeneratedValue
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
    private LocalDateTime updateTime;

    //==생성자==//
    private ToDo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //==생성 메서드==//
    public static ToDo create(CreateToDoRequest createToDoRequest) {
        return new ToDo(createToDoRequest.getTitle(), createToDoRequest.getContent());
    }

    //==연관 관계 매핑 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getToDos().add(this);
    }

    //==비즈니스 로직==//
    public void update(CreateToDoRequest updateToDoRequest) {
        this.title = updateToDoRequest.getTitle();
        this.content = updateToDoRequest.getContent();
    }
}
