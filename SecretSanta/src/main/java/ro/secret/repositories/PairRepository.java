package ro.secret.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.secret.entity.Pair;

@Repository
public interface PairRepository extends CrudRepository<Pair, Integer> {
}
