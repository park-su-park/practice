package park_su_park.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park_su_park.backend.dto.requestDto.CreateUser;
import park_su_park.backend.dto.requestDto.RequestUserDto;
import park_su_park.backend.dto.requestDto.UpdateUser;
import park_su_park.backend.dto.responseData.ApiResponseBody;
import park_su_park.backend.dto.responseData.UserData;
import park_su_park.backend.exception.ExpriedSessionException;
import park_su_park.backend.logIn.LogInterface;
import park_su_park.backend.message.USERMESSAGE;
import park_su_park.backend.repository.UserRepository;
import park_su_park.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    //C
    @PostMapping("/sign-up")
    @Validated(CreateUser.class)
    public ResponseEntity<ApiResponseBody> createUser(
        @Valid @RequestBody RequestUserDto requestUserDto) {
        UserData userData = userService.save(requestUserDto);
        return ResponseEntity.ok(new ApiResponseBody(USERMESSAGE.CREATE_SUCCESS,userData));
    }

    //R
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseBody> readOneUserById(@PathVariable Long userId) {
        UserData userData = userService.findOne(userId);
        return ResponseEntity.ok(new ApiResponseBody(USERMESSAGE.READ_SUCCESS, userData));
    }


    //U
    //@SessionAttribute(name = LogInterface.LOGIN_USER, required = false) Long sessionUserId
    @PatchMapping("/update")
    @Validated(UpdateUser.class)
    public ResponseEntity<ApiResponseBody> updateUser(HttpServletRequest request,
        @Valid @RequestBody RequestUserDto requestUserDto) {
        Long userId = validSession(request);
        UserData userData = userService.update(userId, requestUserDto);
        return ResponseEntity.ok(new ApiResponseBody(USERMESSAGE.UPDATE_SUCCESS, userData));
    }

    private static Long validSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(LogInterface.LOGIN_USER);
        log.info("{}", userId);
        if (userId == null) {
            throw new ExpriedSessionException();
        }
        return userId;
    }


    //D
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseBody> deleteUser(HttpServletRequest request) {
        Long userId = validSession(request);
        userService.delete(userId);
        return ResponseEntity.ok(new ApiResponseBody(USERMESSAGE.DELETE_SUCCESS, null));
    }
}
