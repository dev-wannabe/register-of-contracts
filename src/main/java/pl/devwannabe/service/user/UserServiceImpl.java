package pl.devwannabe.service.user;

import lombok.NonNull;
import org.apache.commons.lang3.Validate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.devwannabe.domain.User.User;
import pl.devwannabe.domain.User.UserRepository;
import pl.devwannabe.domain.User.UserService;
import pl.devwannabe.postgresql.role.RoleRepository;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @NonNull
    private UserRepository userRepository;
    @NonNull
    private RoleRepository roleRepository;
    @NonNull
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(@NonNull UserRepository userRepository, @NonNull RoleRepository roleRepository,
                           @NonNull BCryptPasswordEncoder bCryptPasswordEncoder) {
        Validate.notNull(userRepository);
        Validate.notNull(roleRepository);
        Validate.notNull(bCryptPasswordEncoder);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}