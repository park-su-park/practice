package park_su_park.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import park_su_park.backend.request_dto.RequestUserDto;
import park_su_park.backend.response_dto.ResponseDto;
import park_su_park.backend.response_dto.ResponseUserDto;
import park_su_park.backend.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto createUser(@RequestBody RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = userService.save(requestUserDto);

        return new ResponseDto("유저등록 성공", responseUserDto);
    }
}
