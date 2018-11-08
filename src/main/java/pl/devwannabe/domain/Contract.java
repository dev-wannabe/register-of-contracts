package pl.devwannabe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
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

    @Column(nullable = false, name = "number_of_contract") //unique = true,
    private String number;

    @Column(nullable = false, name = "name_of_system")
    private String name;

    @Column(nullable = false, name = "start_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @Column(nullable = false, name = "end_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    @Column(nullable = false, columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal impact;

    @Column(nullable = false)
    private String scale;

    @Column(nullable = false)
    private Boolean active = true;
@Valid()
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    //TODO
    public boolean isActive(LocalDate startDate, LocalDate endDate) {
        return true;
    }

}