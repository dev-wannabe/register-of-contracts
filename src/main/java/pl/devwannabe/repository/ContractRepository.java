package pl.devwannabe.registerofcontracts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.devwannabe.registerofcontracts.entity.Contract;

import java.util.List;

@Repository
public interface ContractRepository extends CrudRepository <Contract, Long> {




        public List<Contract> findByName(String name);

        public List <Contract> findByActive(Boolean active);

}
