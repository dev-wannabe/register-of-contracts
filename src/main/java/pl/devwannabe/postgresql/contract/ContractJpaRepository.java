package pl.devwannabe.postgresql.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContractJpaRepository extends JpaRepository<ContractEntity, Long> {

    Page<ContractEntity> findByActive(boolean active, Pageable pageable);

    ContractEntity findByNumber(String contractNumber);

}