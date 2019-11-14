package pl.devwannabe.domain.user;

import lombok.*;
import pl.devwannabe.postgresql.role.RoleEntity;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private Set<RoleEntity> roles;

}
