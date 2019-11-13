package pl.devwannabe.domain.contract;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ContractRepository {

    Page<Contract> getAll(@NonNull PageRequest pageRequest);

    Page<Contract> getActive(boolean active, @NonNull PageRequest pageRequest);

    Contract getByContractNumber(@NonNull String ContractNumber);

    void saveContract(@NonNull Contract contract);

    void deleteContract(@NonNull Long id);

    Contract getContract(@NonNull Long id);
}
