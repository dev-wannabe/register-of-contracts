package pl.devwannabe.domain.Model;

import lombok.*;
import pl.devwannabe.domain.Role;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class RoleEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;

    public static RoleEntity convertFrom(Role role) {
        return RoleEntity.builder()
                .id(role.getId())
                .role(role.getRole())
                .users(role.getUsers())
                .build();
    }

    public Role convertTo() {
        return Role.builder()
                .id(id)
                .role(role)
                .users(users)
                .build();
    }


}