package ro.secret.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.secret.entity.Employer;

import java.util.List;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
    Iterable<Employer> findAll();
    @Query(value = "SELECT employer.id FROM employer WHERE  employer.id != :id AND employer.id NOT IN (SELECT pair.from_id FROM pair)", nativeQuery = true)
    List<Integer> eligibleEmployerIds( @Param("id") Integer id);
}
