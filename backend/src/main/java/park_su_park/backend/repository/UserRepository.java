package park_su_park.backend.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import park_su_park.backend.domain.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    private void save(User user) {
        em.persist(user);
    }

    private User findOne(Long userId) {
        return em.find(User.class,userId);
    }

    private List<User> findAll(){
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    private void remove(User user) {
        em.remove(user);
    }
}
