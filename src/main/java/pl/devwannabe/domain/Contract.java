package pl.devwannabe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.devwannabe.validation.UniqueNumber;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contracts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contract {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Size(min = 1, max = 50, message = "Number of the contract have to contain from 1 to 50 characters.")
    @Column(nullable = false, unique = true, name = "number_of_contract")
    @UniqueNumber(message="Such number already exists!")
    private String number;

    @Size(min = 1, max = 50, message = "Name of the contract have to contain from 1 to 50 characters.")
    @Column(nullable = false, name = "name_of_system")
    private String name;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(nullable = false, columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal impact;

    @Column(nullable = false)
    private int daysLeft;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    //TODO
    public boolean isActive(LocalDate startDate, LocalDate endDate) {
        return true;
    }

}