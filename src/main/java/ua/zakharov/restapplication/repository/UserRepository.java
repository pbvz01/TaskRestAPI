package ua.zakharov.restapplication.repository;

import org.springframework.data.repository.CrudRepository;
import ua.zakharov.restapplication.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(Long id);
}
