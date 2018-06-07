package pl.devwannabe.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.devwannabe.entity.Contract;

import java.util.List;


@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>, JpaRepository<Contract, Long> {

        public List <Contract> findByName(String name);

        public Page <Contract> findByActive(Boolean active, Pageable pageable);

}
