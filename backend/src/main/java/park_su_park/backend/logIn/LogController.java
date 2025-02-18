package park_su_park.backend.logIn;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestDto.RequestLogInDto;
import park_su_park.backend.dto.responseDto.ResponseLogInDto;
import park_su_park.backend.dto.responseDto.ResponseUserDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class LogController {

    private final LoginService loginService;
    private final SessionManager sessionManager;
    private final static String LOGIN_USER = "loginUser";

    @PostMapping("/login")
    public ResponseEntity<ResponseLogInDto> login(@Valid @RequestBody RequestLogInDto dto,
        HttpServletRequest request,
        HttpServletResponse response) {
        User loginUser = loginService.login(dto.getEmail(), dto.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_USER, loginUser);

        return ResponseEntity.ok(new ResponseLogInDto(loginUser.getId(), loginUser.getUsername()));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 성공");
    }

    @GetMapping
    public ResponseEntity<Object> getUserInfo(
        @SessionAttribute(name = LOGIN_USER, required = false) User findUser,
        HttpServletRequest request) {
        //세션에 유저가 없을 시 로그인 화면으로 redirect
        if (findUser == null) {
            return ResponseEntity.status(302).location(URI.create("/")).build();
        }
        ResponseUserDto responseUserDto = ResponseUserDto.of(findUser);
        return ResponseEntity.ok(responseUserDto);
    }

    private void expiredCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
