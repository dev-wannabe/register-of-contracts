package pl.devwannabe.services.security;

import pl.devwannabe.domain.Model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

}