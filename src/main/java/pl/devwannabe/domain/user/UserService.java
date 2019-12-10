package pl.devwannabe.domain.user;

import lombok.NonNull;

public interface UserService {

    void save(@NonNull User user);

    User findByUsername(@NonNull String username);

    String generateUserGreeting(@NonNull char asciiArtChar, @NonNull String input);

}