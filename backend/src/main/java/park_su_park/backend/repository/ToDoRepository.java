package park_su_park.backend.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import park_su_park.backend.domain.ToDo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ToDoRepository {
    private final EntityManager em;

    public void save(ToDo toDo) {
        em.persist(toDo);
    }

    public ToDo findOne(Long toDoId) {
        return em.find(ToDo.class, toDoId);
    }

    public List<ToDo> findAll() {
        return em.createQuery("select t from ToDo t",ToDo.class).getResultList();
    }

    public void remove(ToDo toDo) {
        em.remove(toDo);
    }

}
