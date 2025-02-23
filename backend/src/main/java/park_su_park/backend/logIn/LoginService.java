package park_su_park.backend.logIn;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.responseData.UserData;
import park_su_park.backend.exception.LogInException;
import park_su_park.backend.exception.NotExistException;
import park_su_park.backend.message.USERMESSAGE;
import park_su_park.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserData login(String email, String password) {
        User foundUser = userRepository.findByEmail(email).orElseThrow(() -> new NotExistException(
            USERMESSAGE.NOT_EXIST_BY_EMAIL));
        if (!passwordEncoder.matches(password, foundUser.getEncodedPassword())) {
            throw new LogInException(LogInterface.NOT_MATCH);
        }
        return UserData.of(foundUser);
    }
}
