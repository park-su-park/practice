package park_su_park.backend.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import park_su_park.backend.dto.requestBody.CreateUserRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<ToDo> toDos = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    private String username;

    private String password;

    private String email;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdTime;

    //==생성자==//
    private User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //==생성 메서드==//
    public static User create(CreateUserRequest createUserRequest) {
        return new User(createUserRequest.getUsername(), createUserRequest.getRawPassword(), createUserRequest.getEmail());
    }
}
