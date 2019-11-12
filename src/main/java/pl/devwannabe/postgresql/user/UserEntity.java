package pl.devwannabe.postgresql.user;

import lombok.*;
import pl.devwannabe.domain.User;
import pl.devwannabe.postgresql.role.RoleEntity;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String username;
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    public static UserEntity convertFrom(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .passwordConfirm(user.getPasswordConfirm())
                .roles(user.getRoles())
                .build();
    }

    public User convertTo() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .passwordConfirm(passwordConfirm)
                .roles(roles)
                .build();
    }
}
