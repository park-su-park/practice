package park_su_park.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import park_su_park.backend.repository.CommentRepositoroy;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepositoroy commentRepositoroy;


}
