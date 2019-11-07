package pl.devwannabe.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.devwannabe.domain.Model.ContractEntity;


@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {

    Page<ContractEntity> findByActive(Boolean active, Pageable pageable);

    ContractEntity findByNumber(String contractNumber);
}