package pl.devwannabe.domain;

import org.springframework.stereotype.Component;
import pl.devwannabe.services.ContractService;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;
import java.time.Period;

@Component
public class ContractEntityListener {

    @PreUpdate
    @PrePersist
    void preUpdate(Contract contract) {
        ContractService.printBlue(contract);
        contract.setDaysLeft(calculateDaysLeft(contract));
        contract.setActive(isActive(contract));
    }

    @PostLoad
    void preLoad(Contract contract) {
        contract.setDaysLeft(calculateDaysLeft(contract));
        contract.setActive(isActive(contract));
    }

    private int calculateDaysLeft(Contract contract) {
        int days;
        if (contract.getStartDate().isBefore(contract.getEndDate())) {
            days = Period.between(LocalDate.now(), contract.getEndDate()).getDays();
            return days;
        }
        return 0;
    }

    private boolean isActive(Contract contract) {
        return LocalDate.now().isBefore(contract.getEndDate());
    }

}