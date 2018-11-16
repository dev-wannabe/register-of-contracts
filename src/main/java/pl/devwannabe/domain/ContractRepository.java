package pl.devwannabe.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>, JpaRepository<Contract, Long> {

    Page<Contract> findByActive(Boolean active, Pageable pageable);

    Contract findByNumber(String contractNumber);
}
