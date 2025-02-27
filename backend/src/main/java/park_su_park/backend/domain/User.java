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
import park_su_park.backend.logIn.PasswordEncoder;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ToDo> toDoes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    private String username;

    private String encodedPassword;

    private String email;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;
}
