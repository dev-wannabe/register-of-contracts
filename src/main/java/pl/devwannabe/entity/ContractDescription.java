package pl.devwannabe.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "descriptions")
public class ContractDescription {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false,
            columnDefinition = "text default 'No description available, yet'")
    private String description;

    @Override
    public String toString() {
        return "ContractDescription{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    //    @JsonManagedReference
//    @OneToOne(mappedBy = "description",
//            cascade =  CascadeType.ALL,
//            fetch = FetchType.EAGER)
//    private Contract contract;


}
