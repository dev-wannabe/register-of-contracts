package pl.devwannabe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contract {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, name = "NUMBER_OF_CONTRACT") //unique = true,
    private String number;

    @Column(nullable = false, name = "NAME_OF_SYSTEM")
    private String name;

    @Column(nullable = false, name = "START_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @Column(nullable = false, name = "END_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    @Column(nullable = false, columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal impact;

    @Column(nullable = false)
    private String scale;

    @Column(nullable = false)
    private Boolean active = true;

    public Contract withId(final Long id) {
        this.id = id;
        return this;
    }

    public Contract withNumber(final String number) {
        this.number = number;
        return this;
    }

    public Contract withName(final String name) {
        this.name = name;
        return this;
    }

    public Contract withStartDate(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public Contract withEndDate(final LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public Contract withImpact(final BigDecimal impact) {
        this.impact = impact;
        return this;
    }


    public Contract withScale(final String scale) {
        this.scale = scale;
        return this;
    }

    public Contract withActive(final Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String toString() {
        return "ContractEntity{" +
                "<b>id</b>: " + id +
                ", <b>number of contract:</b> '   " + number + '\'' +
                ", <b>system:</b> '" + name + '\'' +
                ", <b>start date:</b> " + startDate +
                ", <b>end date:</b> " + endDate +
                ", <b>impact:</b> " + impact + " zł"   +
                ", <b>scale:</b> " + scale +
                ", <b>active:</b> " + active +
                '}';

    }



    //TODO
    public boolean isActive(LocalDate startDate, LocalDate endDate) {
        return true;
    }

}
