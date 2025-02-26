package park_su_park.backend.dto.requestBody;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {

    private final String email;

    private final String rawPassword;
}
