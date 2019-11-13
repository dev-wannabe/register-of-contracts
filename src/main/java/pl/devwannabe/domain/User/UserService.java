package pl.devwannabe.domain.User;

import lombok.NonNull;

public interface UserService {

    void save(@NonNull User user);

    User findByUsername(@NonNull String username);

}