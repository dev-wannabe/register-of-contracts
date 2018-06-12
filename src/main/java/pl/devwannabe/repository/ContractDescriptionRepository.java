package pl.devwannabe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.devwannabe.entity.ContractDescription;

@Repository
public interface ContractDescriptionRepository extends CrudRepository<ContractDescription, Long>, JpaRepository<ContractDescription, Long>  {








}
