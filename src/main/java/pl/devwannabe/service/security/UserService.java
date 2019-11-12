package pl.devwannabe.service.security;

import pl.devwannabe.domain.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

}