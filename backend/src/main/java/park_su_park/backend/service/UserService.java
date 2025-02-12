package park_su_park.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import park_su_park.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

}
