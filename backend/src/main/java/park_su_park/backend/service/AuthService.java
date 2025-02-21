package park_su_park.backend.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestBody.CreateUserRequest;
import park_su_park.backend.dto.requestBody.LoginRequest;
import park_su_park.backend.dto.responseBody.UserData;
import park_su_park.backend.exception.AuthenticationFailedException;
import park_su_park.backend.exception.ResourceNotFoundException;
import park_su_park.backend.util.constant.AuthResponseMessage;
import park_su_park.backend.web.security.encoder.PasswordEncoder;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserData signUp(CreateUserRequest createUserRequest) {
        userService.validateUniqueUser(createUserRequest);

        return userService.join(createUserRequest);
    }

    public void login(LoginRequest loginRequest, HttpSession session) {
        try {
            Long userId = validateCredentials(loginRequest);

            session.setAttribute("userId", userId);
            session.setMaxInactiveInterval(1800);
        } catch (ResourceNotFoundException e) {
            throw new AuthenticationFailedException(AuthResponseMessage.AUTHENTICATION_FAILED_ACTION);
        }
    }

    public void logout(HttpSession session) {
        if (session == null) {
            throw new IllegalStateException(AuthResponseMessage.LOGOUT_BAD_REQUEST);
        }
        session.invalidate();
    }

    public Long validateCredentials(LoginRequest loginRequest) {
        User user = userService.findUserByEmail(loginRequest.getEmail());

        if (!passwordEncoder.match(loginRequest.getRawPassword(), user.getPassword())) {
            throw new AuthenticationFailedException(AuthResponseMessage.AUTHENTICATION_FAILED_ACTION);
        }
        return user.getId();
    }
}
