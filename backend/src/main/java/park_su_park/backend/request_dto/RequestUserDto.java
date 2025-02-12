package park_su_park.backend.request_dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUserDto {
    private String username;
    private String password;
    private String email;
}
