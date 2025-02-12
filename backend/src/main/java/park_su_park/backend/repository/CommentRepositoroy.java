package park_su_park.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import park_su_park.backend.domain.Comment;

@Repository
public interface CommentRepositoroy extends JpaRepository<Comment, Long> {

}
