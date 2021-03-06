package pl.devwannabe.domain.user;

import lombok.NonNull;

public interface UserRepository {

    void save(@NonNull User user);

    User findByUsername(@NonNull String username);

}
