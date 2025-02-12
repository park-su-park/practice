package park_su_park.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.UserDataResponse;
import park_su_park.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<UserDataResponse> searchUserByUsername(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email) {

        if (username != null && email != null) {
            throw new IllegalArgumentException("둘 중 하나만 입력해야 합니다. username 또는 email 을 선택해주세요.");
        }

        if (username != null) {
            User user = userService.findUserByUsername(username);
            return ResponseEntity.ok(UserDataResponse.createUserDataResponse(user));
        } else if (email != null) {
            User user = userService.findUserByEmail(email);
            return ResponseEntity.ok(UserDataResponse.createUserDataResponse(user));
        } else {
            throw new IllegalArgumentException("username 또는 email 을 선택해서 검색 해주세요.");
        }
    }
}
