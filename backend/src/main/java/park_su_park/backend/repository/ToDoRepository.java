package park_su_park.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import park_su_park.backend.domain.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
