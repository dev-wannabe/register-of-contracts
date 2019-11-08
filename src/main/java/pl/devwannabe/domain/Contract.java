package pl.devwannabe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.devwannabe.validation.annotations.EndDate;
import pl.devwannabe.validation.annotations.StartDate;
import pl.devwannabe.validation.annotations.Unique;
import pl.devwannabe.validation.sequences.ValidateGroupFirst;
import pl.devwannabe.validation.sequences.ValidateGroupSecond;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

//@Value


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@GroupSequence({Contract.class, ValidateGroupFirst.class, ValidateGroupSecond.class})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contract {

    @Unique(groups = ValidateGroupFirst.class, message = "Annotation is here for idContractInputSupplier class ")
    private Long id;

    @Size(min = 1, max = 50, message = "''System Name'' have to contain from 1 to 50 characters")
    @NotNull
    @Unique(groups = ValidateGroupSecond.class, message="Such ''Contract Number'' already exists.")
    private String number;

    @Size(min = 1, max = 50, message = "''System Name'' have to contain from 1 to 50 characters")
    @NotNull
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @StartDate(groups = ValidateGroupFirst.class, message = "This annotation is here for InputStartDateContractSupplier class")
    @NotNull(message = "''Start Date'' can not be empty")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @EndDate(groups = ValidateGroupSecond.class, message = " ''Start Date'' must be less than ''End Date'' ")
    @NotNull(message = "''End Date'' can not be empty")
    private LocalDate endDate;

    @NotNull(message = "''Income'' can not be empty")
    private BigDecimal income;

    private int daysLeft;
    private boolean active;
    private String description;

}