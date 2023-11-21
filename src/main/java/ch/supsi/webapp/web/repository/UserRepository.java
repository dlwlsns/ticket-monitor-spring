package ch.supsi.webapp.web.repository;

import ch.supsi.webapp.web.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    public Optional<User> findUserById(String id);
    @Query(value = "SELECT * FROM user", nativeQuery = true)
    public List<User> findUsers();
}
