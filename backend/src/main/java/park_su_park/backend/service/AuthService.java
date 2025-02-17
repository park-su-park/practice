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

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private static final String AUTHENTICATION_FAILED_ACTION = "이메일 혹은 비밀번호가 올바르지 않습니다.";
    private final UserService userService;

    public UserData signIn(CreateUserRequest createUserRequest) {
        userService.checkExists(createUserRequest);

        return userService.join(createUserRequest);
    }

    public void login(LoginRequest loginRequest, HttpSession session) {
        try {
            UserData userData = userService.findUserByEmail(loginRequest.getEmail());

            User user = userService.findUser(userData.getUserId());

            if (!user.getPassword().equals(loginRequest.getRawPassword())) {
                throw new AuthenticationFailedException(AUTHENTICATION_FAILED_ACTION);
            }

            session.setAttribute("userId", user.getId());
        } catch (ResourceNotFoundException e) {
            throw new AuthenticationFailedException(AUTHENTICATION_FAILED_ACTION);
        }
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
