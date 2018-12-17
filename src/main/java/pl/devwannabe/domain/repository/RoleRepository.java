package pl.devwannabe.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.devwannabe.domain.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
