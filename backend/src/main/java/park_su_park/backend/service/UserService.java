package park_su_park.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestBody.CreateUserRequest;
import park_su_park.backend.dto.responseBody.UserData;
import park_su_park.backend.exception.DuplicateResourceException;
import park_su_park.backend.exception.ResourceNotFoundException;
import park_su_park.backend.repository.UserRepository;
import park_su_park.backend.web.security.encoder.PasswordEncoder;
import java.text.MessageFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserData join(CreateUserRequest createUserRequest) {
        User user = createNewUserWithEncodedPassword(createUserRequest);
        User savedUser = userRepository.save(user);
        return UserData.create(savedUser);
    }

    @Transactional(readOnly = true)
    public User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageFormat.format("해당 id 를 가진 사용자를 찾지 못했습니다: {0}", id)
                ));
    }

    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageFormat.format("해당 사용자명 을 가진 사용자를 찾지 못했습니다: {0}", username)
                ));
    }

    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageFormat.format("해당 email 을 가진 사용자를 찾지 못했습니다: {0}", email)
                ));
    }

    @Transactional(readOnly = true)
    public void validateUniqueUser(CreateUserRequest createUserRequest) {
        String username = createUserRequest.getUsername();
        if(userRepository.existsUserByUsername(username)){
            throw new DuplicateResourceException(
                    MessageFormat.format("이미 사용중인 사용자명 입니다: {0}", username)
            );
        }
        String email = createUserRequest.getEmail();
        if (userRepository.existsUserByEmail(email)) {
            throw new DuplicateResourceException(
                    MessageFormat.format("이미 사용중인 email 입니다: {0}", email)
            );
        }
    }

    private User createNewUserWithEncodedPassword(CreateUserRequest createUserRequest) {
        User user = User.create(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getRawPassword()));
        return user;
    }
}
