package pl.devwannabe.service.security;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.devwannabe.domain.User;
import pl.devwannabe.postgresql.role.RoleRepository;
import pl.devwannabe.postgresql.user.UserEntity;
import pl.devwannabe.postgresql.user.UserRepository;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        val userEntity = UserEntity.convertFrom(user);
        userRepository.save(userEntity);
    }

    @Override
    public User findByUsername(String username) {
        if(userRepository.findByUsername(username) != null)
            return userRepository.findByUsername(username).convertTo();
        else
            return null;
    }
}