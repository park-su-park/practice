package park_su_park.backend.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import park_su_park.backend.domain.Comment;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public Comment findOne(Long commentId) {
        return em.find(Comment.class, commentId);
    }

    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c",Comment.class).getResultList();
    }

    public void remove(Comment comment) {
        em.remove(comment);
    }
}
