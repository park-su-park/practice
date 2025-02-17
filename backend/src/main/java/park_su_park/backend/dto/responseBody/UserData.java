package park_su_park.backend.dto.responseBody;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import park_su_park.backend.domain.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserData implements ApiResponseData {

    private final Long userId;
    private final String username;
    private final String email;
    private final LocalDateTime createdTime;

    //==생성 메서드==//
    public static UserData create(User user) {
        return new UserData(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedTime());
    }
}
