package pl.devwannabe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.devwannabe.entity.Contract;
import pl.devwannabe.entity.ContractDescription;


@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>, JpaRepository<Contract, Long> {

        public Contract getOneByDescription(Long id);

        public Page <Contract> findByActive(Boolean active, Pageable pageable);


}
