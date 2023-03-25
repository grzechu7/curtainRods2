package curtainRod.repository;

import curtainRod.entity.PricingSee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingSeeRepository extends JpaRepository <PricingSee, Integer> {
}
