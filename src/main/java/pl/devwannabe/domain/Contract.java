package pl.devwannabe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.devwannabe.validation.Unique;
import pl.devwannabe.validation.ValidateGroupFirst;
import pl.devwannabe.validation.ValidateGroupSecond;

import javax.persistence.*;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({ContractEntityListener.class})
@GroupSequence({Contract.class, ValidateGroupFirst.class, ValidateGroupSecond.class})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contract {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Unique(groups = ValidateGroupFirst.class, message = "This annotation here is for idContractInputSupplier class ")
    private Long id;

    @Column(nullable = false, unique = true, name = "contract_number")
    @Size(min = 1, max = 50, message = "Number of the contract have to contain from 1 to 50 characters.")
    @NotNull
    @Unique(groups = ValidateGroupSecond.class, message="Such number already exists!")
    private String number;

    @Column(nullable = false, name = "system_name")
    @Size(min = 1, max = 50, message = "Name of the contract have to contain from 1 to 50 characters.")
    @NotNull
    private String name;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "The start date can not be empty")
    private LocalDate startDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "The end date can not be empty")
    private LocalDate endDate;

    @Column(nullable = false, columnDefinition = "Decimal(10,2) default '0.00'")
    @NotNull(message = "The impact can not be empty")
    private BigDecimal impact;

    @Column(nullable = false)
    private Integer daysLeft;

    @Column(nullable = false)
    private Boolean active;

    @Column(columnDefinition = "TEXT")
    private String description;

}