package pl.devwannabe.service.user;

import lombok.NonNull;
import org.apache.commons.lang3.Validate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.devwannabe.domain.Role;
import pl.devwannabe.domain.User.User;
import pl.devwannabe.domain.User.UserRepository;
import pl.devwannabe.postgresql.role.RoleEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @NonNull
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(@NonNull UserRepository userRepository) {
        Validate.notNull(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        if (userRepository.findByUsername(username) != null) {
            user = userRepository.findByUsername(username);
        } else {
            user = null;

        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = user.getRoles().stream().map(RoleEntity::convertTo).collect(Collectors.toSet());
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}