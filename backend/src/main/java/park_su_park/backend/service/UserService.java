package park_su_park.backend.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestBody.CreateUserRequest;
import park_su_park.backend.exception.DuplicateResourceException;
import park_su_park.backend.exception.ResourceNotFoundException;
import park_su_park.backend.exception.UnauthorizedAccessException;
import park_su_park.backend.repository.UserRepository;

import java.text.MessageFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final static String UNAUTHORIZED_ACCESS_ACTION = "로그인 정보가 유효하지 않거나 만료 되었습니다.";
    private final UserRepository userRepository;

    public Long join(CreateUserRequest createUserRequest) {
        checkDuplicateUser(createUserRequest);

        User user = User.create(createUserRequest);
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageFormat.format("해당 id 를 가진 사용자를 찾지 못했습니다: {0}", id)
                ));
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageFormat.format("해당 username 을 가진 사용자를 찾지 못했습니다: {0}", username)
                ));
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageFormat.format("해당 email 을 가진 사용자를 찾지 못했습니다: {0}", email)
                ));
    }

    @Transactional(readOnly = true)
    public void checkDuplicateUser(CreateUserRequest createUserRequest) {
        userRepository.findUserByUsername(createUserRequest.getUsername())
                .ifPresent(user -> {
                    throw new DuplicateResourceException(
                            MessageFormat.format("다른 사용자가 사용중인 username 입니다: {0}", createUserRequest.getUsername())
                    );
                });

        userRepository.findUserByEmail(createUserRequest.getEmail())
                .ifPresent(user -> {
                    throw new DuplicateResourceException(
                            MessageFormat.format("다른 사용자가 사용중인 email 입니다: {0}", createUserRequest.getEmail())
                    );
                });
    }

    public Long getUserIdFromSession(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new UnauthorizedAccessException(UNAUTHORIZED_ACCESS_ACTION);
        }
        return userId;
    }
}
