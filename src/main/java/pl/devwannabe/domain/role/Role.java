package pl.devwannabe.domain.role;

import lombok.*;
import pl.devwannabe.postgresql.user.UserEntity;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    private Long id;
    private String role;
    private Set<UserEntity> users;

}
