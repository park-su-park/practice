package park_su_park.backend.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import park_su_park.backend.dto.requestDto.RequestUserDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<ToDo> toDoes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    private String username;

    private String password;

    private String email;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;

    public static User createUser(RequestUserDto requestUserDto) {
        User user = new User();
        user.setUsername(requestUserDto.getUsername());
        user.setPassword(requestUserDto.getPassword());
        user.setEmail(requestUserDto.getEmail());
        return user;
    }
}
