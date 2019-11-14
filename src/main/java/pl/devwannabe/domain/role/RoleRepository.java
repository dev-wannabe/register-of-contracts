package pl.devwannabe.domain.role;

import pl.devwannabe.postgresql.role.RoleEntity;

import java.util.Set;

public interface RoleRepository {

    Set<RoleEntity> findAll();

}
