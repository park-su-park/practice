package park_su_park.backend.dto.requestBody;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import park_su_park.backend.util.validation.EmailCustom;
import park_su_park.backend.util.validation.Password;
import park_su_park.backend.util.validation.Username;

@Getter
@RequiredArgsConstructor
public class CreateUserRequest {

    @Username
    private final String username;

    @Password
    private final String rawPassword;

    @EmailCustom
    private final String email;
}
