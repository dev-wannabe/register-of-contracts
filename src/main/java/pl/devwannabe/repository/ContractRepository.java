package pl.devwannabe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.devwannabe.entity.Contract;

import java.util.List;

//@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>, JpaRepository<Contract, Long> {

        public List <Contract> findByName(String name);

        public List <Contract> findByActive(Boolean active);

}
