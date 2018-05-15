package pl.devwannabe.registerofcontracts.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Contract {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, name = "NUMBER_OF_CONTRACT") //unique = true,
    private String number;

    @Column(nullable = false, name = "NAME_OF_SYSTEM")
    private String name;

    @Column(nullable = false, name = "START_DATE")
    private LocalDate startDate;

    @Column(nullable = false, name = "END_DATE")
    private LocalDate endDate;

    @Column(nullable = false, columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal impact;

    @Column(nullable = false)
    private String scale;

    @Column(nullable = false)
    private Boolean active;

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
                ", <b>active:</b> " + yesOrNo(active) +
                '}';

    }



    public String yesOrNo(Boolean active) {
        String answer = "";
        if(active == true) {
            answer = "Yes";
        }else if(active == false) {
            answer = "No";
        }else{
            answer = "No information";
        }
        return answer;
    }

    //TODO
    public boolean isActive(LocalDate startDate, LocalDate endDate){
        return true;
    }

}
