package park_su_park.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
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

    private LocalDateTime createdTime;

    private LocalDateTime updateTime;
}
