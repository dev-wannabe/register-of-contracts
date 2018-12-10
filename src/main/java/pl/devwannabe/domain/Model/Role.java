package pl.devwannabe.domain.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Role {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}