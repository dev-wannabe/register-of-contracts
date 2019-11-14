package pl.devwannabe.postgresql.role;

import lombok.NonNull;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;
import pl.devwannabe.domain.role.RoleRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class SqlRoleRepository implements RoleRepository {

    @NonNull
    private final RoleJpaRepository roleJpaRepository;

    public SqlRoleRepository(@NonNull RoleJpaRepository roleJpaRepository) {
        Validate.notNull(roleJpaRepository);
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public Set<RoleEntity> findAll() {
        return roleJpaRepository.findAll().stream().collect(Collectors.toSet());
    }
}
