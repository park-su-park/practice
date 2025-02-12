package park_su_park.backend.dto;

import lombok.Getter;
import park_su_park.backend.domain.User;

import java.time.LocalDateTime;

@Getter
public class UserDataResponse {

    private final String username;
    private final String email;
    private final LocalDateTime createdTime;

    //==생성자==//
    private UserDataResponse(String username, String email, LocalDateTime createdTime) {
        this.username = username;
        this.email = email;
        this.createdTime = createdTime;
    }

    //==생성 메서드==//
    public static UserDataResponse createUserDataResponse(User user) {
        return new UserDataResponse(user.getUsername(), user.getEmail(), user.getCreatedTime());
    }
}
