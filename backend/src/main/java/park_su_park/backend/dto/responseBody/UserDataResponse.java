package park_su_park.backend.dto.responseBody;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import park_su_park.backend.domain.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDataResponse {

    private final String username;
    private final String email;
    private final LocalDateTime createdTime;

    //==생성 메서드==//
    public static UserDataResponse createUserDataResponse(User user) {
        return new UserDataResponse(user.getUsername(), user.getEmail(), user.getCreatedTime());
    }
}
