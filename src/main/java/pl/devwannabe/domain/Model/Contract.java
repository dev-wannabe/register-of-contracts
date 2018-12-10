package pl.devwannabe.domain.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.devwannabe.domain.ContractEntityListener;
import pl.devwannabe.validation.annotations.EndDate;
import pl.devwannabe.validation.annotations.StartDate;
import pl.devwannabe.validation.annotations.Unique;
import pl.devwannabe.validation.sequences.ValidateGroupFirst;
import pl.devwannabe.validation.sequences.ValidateGroupSecond;

import javax.persistence.*;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
@NoArgsConstructor
@EntityListeners({ContractEntityListener.class})
@GroupSequence({Contract.class, ValidateGroupFirst.class, ValidateGroupSecond.class})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contract {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Unique(groups = ValidateGroupFirst.class, message = "Annotation is here for idContractInputSupplier class ")
    private Long id;

    @Column(nullable = false, unique = true, name = "contract_number")
    @Size(min = 1, max = 50, message = "''Contract Number'' have to contain from 1 to 50 characters")
    @NotNull
    @Unique(groups = ValidateGroupSecond.class, message="Such ''Contract Number'' already exists.")
    private String number;

    @Column(nullable = false, name = "system_name")
    @Size(min = 1, max = 50, message = "''System Name'' have to contain from 1 to 50 characters")
    @NotNull
    private String name;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @StartDate(groups = ValidateGroupFirst.class, message = "This annotation is here for InputStartDateContractSupplier class")
    @NotNull(message = "''Start Date'' can not be empty")
    private LocalDate startDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @EndDate(groups = ValidateGroupSecond.class, message = " ''Start Date'' must be less than ''End Date'' ")
    @NotNull(message = "''End Date'' can not be empty")
    private LocalDate endDate;

    @Column(nullable = false, columnDefinition = "Decimal(10,2) default '0.00'")
    @NotNull(message = "''Impact'' can not be empty")
    private BigDecimal impact;

    @Column(nullable = false)
    private Integer daysLeft;

    @Column(nullable = false)
    private Boolean active;

    @Column(columnDefinition = "TEXT")
    private String description;

}