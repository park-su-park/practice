package park_su_park.backend.logIn;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestDto.RequestLogInDto;
import park_su_park.backend.dto.responseData.ApiResponseBody;
import park_su_park.backend.dto.responseDto.ResponseLogInDto;
import park_su_park.backend.dto.responseData.UserData;
import park_su_park.backend.exception.NotExistException;
import park_su_park.backend.logIn.LogInterface;
import park_su_park.backend.logIn.LoginService;
import park_su_park.backend.message.USERMESSAGE;
import park_su_park.backend.repository.UserRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
@Slf4j
public class LogController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseBody> login(@Valid @RequestBody RequestLogInDto dto,
        HttpServletRequest request,
        HttpServletResponse response) {
        log.info("login-controller");
        UserData userData = loginService.login(dto.getEmail(), dto.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute(LogInterface.LOGIN_USER, userData.getId());

        return ResponseEntity.ok(new ApiResponseBody(LogInterface.LOGIN_SUCCESS, userData));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponseBody> logout(HttpServletRequest request,
        HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok(new ApiResponseBody(LogInterface.LOGOUT_SUCCESS, null));
    }
}
