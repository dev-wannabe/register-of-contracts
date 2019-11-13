package pl.devwannabe.domain.contract;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ContractService {

    Page<Contract> getAllContracts(@NonNull PageRequest pageRequest);

    Page<Contract> getActiveContracts(boolean active, @NonNull PageRequest pageRequest);

    Contract getContractByContractNumber(@NonNull String ContractNumber);

    void saveContract(@NonNull Contract contract);

    void deleteContractById(@NonNull Long id);

    Contract getOneContract(@NonNull Long id);

}
