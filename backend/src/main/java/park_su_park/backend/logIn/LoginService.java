package park_su_park.backend.logIn;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import park_su_park.backend.domain.User;
import park_su_park.backend.exception.LogInException;
import park_su_park.backend.exception.NotExistException;
import park_su_park.backend.message.USERMESSAGE;
import park_su_park.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(String email, String password) {
        User findUser = userRepository.findByEmail(email).orElseThrow(() -> new NotExistException(
            USERMESSAGE.NOT_EXIST));
        if (!findUser.getPassword().equals(password)) {
            throw new LogInException("비밀번호가 일치하지 않습니다.");
        }
        return findUser;
    }
}
