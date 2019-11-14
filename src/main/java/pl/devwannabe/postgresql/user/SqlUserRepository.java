package pl.devwannabe.postgresql.user;

import lombok.NonNull;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;
import pl.devwannabe.domain.user.User;

@Repository
public class SqlUserRepository implements pl.devwannabe.domain.user.UserRepository {

    @NonNull
    private final UserJpaRepository userJpaRepository;

    public SqlUserRepository(@NonNull UserJpaRepository userJpaRepository) {
        Validate.notNull(userJpaRepository);
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public void save(@NonNull User user) {
        Validate.notNull(user);
        userJpaRepository.save(UserEntity.convertFrom(user));
    }

    @Override
    public User findByUsername(@NonNull String username) {
        Validate.notNull(username);
        if (userJpaRepository.findByUsername(username) != null)
            return userJpaRepository.findByUsername(username).convertTo();
        return null;
    }
}
