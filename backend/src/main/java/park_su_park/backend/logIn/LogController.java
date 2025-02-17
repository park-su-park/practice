package park_su_park.backend.logIn;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/login")
    public ResponseEntity<ResponseLogInDto> login(@Valid @RequestBody RequestLogInDto dto,
        HttpServletResponse response) {
        User loginUser = loginService.login(dto.getEmail(), dto.getPassword());

        sessionManager.createSession(loginUser, response);

        return ResponseEntity.ok(new ResponseLogInDto(loginUser.getId(), loginUser.getUsername()));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        sessionManager.expire(request);
        return ResponseEntity.ok("로그아웃 성공");
    }

    @GetMapping
    public ResponseEntity<ResponseUserDto> getUserInfo(HttpServletRequest request) {
        User findUser = (User) sessionManager.getSession(request);
        ResponseUserDto responseUserDto = ResponseUserDto.of(findUser);
        return ResponseEntity.ok(responseUserDto);
    }

    private void expiredCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
