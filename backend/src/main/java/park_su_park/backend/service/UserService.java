package park_su_park.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.User;
import park_su_park.backend.request_dto.RequestUserDto;
import park_su_park.backend.repository.UserRepository;
import park_su_park.backend.response_dto.ResponseUserDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public ResponseUserDto save(RequestUserDto requestUserDto) {
        User user = User.createUser(requestUserDto);
        userRepository.save(user);
        ResponseUserDto responseUserDto = ResponseUserDto.createResponseUserDto(user.getId(), user.getCreate_time(), requestUserDto);
        return responseUserDto;
    }

}
