package pl.devwannabe.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.devwannabe.domain.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
