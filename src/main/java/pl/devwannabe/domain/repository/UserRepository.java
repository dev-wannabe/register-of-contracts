package pl.devwannabe.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.devwannabe.domain.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
