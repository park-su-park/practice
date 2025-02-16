package park_su_park.backend.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.User;
import park_su_park.backend.exception.NotExistUserException;
import park_su_park.backend.exception.ValidateMemberException;
import park_su_park.backend.requestDto.RequestUserDto;
import park_su_park.backend.repository.UserRepository;
import park_su_park.backend.responseDto.ResponseUserDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public ResponseUserDto save(RequestUserDto requestUserDto) {
        User user = User.createUser(requestUserDto);
        validateUser(requestUserDto);
        User savedUser = userRepository.save(user);
        return ResponseUserDto.of(savedUser);
    }

//    public ResponseUserDto findUser(String username, String email) {
//
//    }

    private void validateUser(RequestUserDto requestUserDto){
        //중복 email,username 확인
        Optional<User> byEmail = userRepository.findByEmail(requestUserDto.getEmail());
        Optional<User> byUsername = userRepository.findByUsername(requestUserDto.getUsername());
        if(byEmail.isPresent()&&byUsername.isPresent()){
            throw new ValidateMemberException("이미 사용중인 email, username입니다.");
        }
        //
        else if (byUsername.isPresent()) {
            throw new ValidateMemberException("이미 사용중인 username입니다.");
        }else if(byEmail.isPresent()) {
            throw new ValidateMemberException("이미 사용중인 email입니다.");
        }
    }

    @Transactional
    public void updateUser(Long id, RequestUserDto requestUserDto) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotExistUserException("존재하지 않는 사용자 입니다"));
        updateUserByDto(requestUserDto, user);
    }



    @Transactional
    public void deleteUser(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(() -> new NotExistUserException("존재하지 않는 사용자입니다"));
        userRepository.delete(findUser);
    }

    private static void updateUserByDto(RequestUserDto requestUserDto, User user) {
        user.setUsername(requestUserDto.getUsername());
        user.setPassword(requestUserDto.getPassword());
        user.setEmail(requestUserDto.getEmail());
    }
}
