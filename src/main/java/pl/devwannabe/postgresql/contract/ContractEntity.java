package pl.devwannabe.postgresql.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.devwannabe.domain.contract.Contract;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({ContractEntityListener.class})
public class ContractEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true, name = "contract_number")
    private String number;

    @Column(nullable = false, name = "system_name")
    private String name;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal income;

    @Column(nullable = false)
    private Integer daysLeft;

    @Column(nullable = false)
    private Boolean active;

    @Column(columnDefinition = "TEXT")
    private String description;


    public static ContractEntity convertFrom(Contract contract) {
        return ContractEntity.builder()
                .id(contract.getId())
                .number(contract.getNumber())
                .name(contract.getName())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .income(contract.getIncome())
                .daysLeft(contract.getDaysLeft())
                .active(contract.isActive())
                .description(contract.getDescription())
                .build();
    }

    public Contract convertTo() {
        return Contract.builder()
                .id(id)
                .number(number)
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .income(income)
                .daysLeft(daysLeft)
                .active(active)
                .description(description)
                .build();
    }

}