package park_su_park.backend.dto.responseData;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import lombok.Getter;
import lombok.Setter;
import park_su_park.backend.domain.User;

@Getter
@Setter
public class UserData implements ResponseData{

    private Long id;
    private String username;
    private String email;
    private LocalDateTime createTime;

    public static UserData of(User user) {
        UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setUsername(user.getUsername());
        userData.setEmail(user.getEmail());
        userData.setCreateTime(user.getCreateTime());
        return userData;
    }
}