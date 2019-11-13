package pl.devwannabe.domain.User;

import lombok.NonNull;

public interface UserRepository {

    void save(@NonNull User user);

    User findByUsername(@NonNull String username);

}
