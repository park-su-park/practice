package park_su_park.backend.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestDto.RequestUserDto;
import park_su_park.backend.dto.responseData.UserData;
import park_su_park.backend.exception.NotExistException;
import park_su_park.backend.exception.UsedException;
import park_su_park.backend.message.USERMESSAGE;
import park_su_park.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserData save(RequestUserDto requestUserDto) {
        validateUser(requestUserDto);
        User user = User.of(requestUserDto);
        User savedUser = userRepository.save(user);
        return UserData.of(savedUser);
    }


    private void validateUser(RequestUserDto requestUserDto) {
        //중복 email,username 확인
        Optional<User> foundUserByEmail = userRepository.findByEmail(requestUserDto.getEmail());
        Optional<User> foundUserByUsername = userRepository.findByUsername(
            requestUserDto.getUsername());

        if (foundUserByEmail.isPresent() && foundUserByUsername.isPresent()) {
            throw new UsedException(USERMESSAGE.USED_USERNAME_AND_EMAIL);
        }
        //
        else if (foundUserByUsername.isPresent()) {
            throw new UsedException(USERMESSAGE.USED_USERNAME);
        } else if (foundUserByEmail.isPresent()) {
            throw new UsedException(USERMESSAGE.USED_Email);
        }
    }

    public UserData findOne(Long id) {
        User foundUser = userRepository.findById(id)
            .orElseThrow(() -> new NotExistException(USERMESSAGE.NOT_EXIST));
        return UserData.of(foundUser);
    }


    @Transactional
    public UserData update(Long id, RequestUserDto requestUserDto) {
        User foundUser = userRepository.findById(id)
            .orElseThrow(() -> new NotExistException(USERMESSAGE.NOT_EXIST));
        updateUserByDto(requestUserDto, foundUser);
        return UserData.of(foundUser);
    }

    private void updateUserByDto(RequestUserDto requestUserDto, User user) {
        user.setUsername(requestUserDto.getUsername());
        user.setPassword(requestUserDto.getPassword());
        user.setEmail(requestUserDto.getEmail());
    }


    @Transactional
    public void delete(Long id) {
        User foundUser = userRepository.findById(id)
            .orElseThrow(() -> new NotExistException(USERMESSAGE.NOT_EXIST));
        userRepository.delete(foundUser);
    }


}
