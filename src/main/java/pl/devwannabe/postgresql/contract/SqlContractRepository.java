package pl.devwannabe.postgresql.contract;

import lombok.NonNull;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import pl.devwannabe.domain.contract.Contract;
import pl.devwannabe.domain.contract.ContractRepository;

@Repository
public class SqlContractRepository implements ContractRepository {

    @NonNull
    private final ContractJpaRepository contractJpaRepository;

    public SqlContractRepository(@NonNull ContractJpaRepository contractJpaRepository) {
        Validate.notNull(contractJpaRepository);
        this.contractJpaRepository = contractJpaRepository;
    }

    @Override
    public Page<Contract> getAll(@NonNull PageRequest pageRequest) {
        Validate.notNull(pageRequest);
        return contractJpaRepository.findAll(pageRequest).map(ContractEntity::convertTo);
    }

    @Override
    public Page<Contract> getActive(boolean active, @NonNull PageRequest pageRequest) {
        Validate.notNull(pageRequest);
        return contractJpaRepository.findByActive(active, pageRequest).map(ContractEntity::convertTo);
    }

    @Override
    public Contract getByContractNumber(@NonNull String contractNumber) {
        Validate.notNull(contractNumber);
        if (contractJpaRepository.findByNumber(contractNumber) != null)
            return contractJpaRepository.findByNumber(contractNumber).convertTo();
        return null;
    }

    @Override
    public void saveContract(@NonNull Contract contract) {
        Validate.notNull(contract);
        contractJpaRepository.save(ContractEntity.convertFrom(contract));

    }

    @Override
    public void deleteContract(@NonNull Long id) {
        Validate.notNull(id);
        contractJpaRepository.deleteById(id);
    }

    @Override
    public Contract getContract(@NonNull Long id) {
        Validate.notNull(id);
        return contractJpaRepository.getOne(id).convertTo();
    }
}
