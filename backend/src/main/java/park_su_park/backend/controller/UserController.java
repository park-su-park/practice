package park_su_park.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.responseBody.UserDataResponse;
import park_su_park.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<UserDataResponse> searchUser(
            @RequestParam String q,
            @RequestParam String udm) {

        if(udm.equals("username")){
            User user = userService.findUserByUsername(q);
            return ResponseEntity.ok(UserDataResponse.createUserDataResponse(user));
        } else if (udm.equals("email")) {
            User user = userService.findUserByEmail(q);
            return ResponseEntity.ok(UserDataResponse.createUserDataResponse(user));
        } else {
            throw new IllegalArgumentException("유효한 검색 타입을 지정해서 검색해주세요.");
        }
    }
}
