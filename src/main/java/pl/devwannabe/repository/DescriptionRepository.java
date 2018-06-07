package pl.devwannabe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.devwannabe.entity.Contract;
import pl.devwannabe.entity.Description;

@Repository
public interface DescriptionRepository extends CrudRepository<Description, Long>, JpaRepository<Description, Long>  {

    public Page<Description> findByDescription(Pageable pageable);

}
