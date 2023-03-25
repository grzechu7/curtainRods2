package curtainRod.repository;

import curtainRod.entity.Customer;
import curtainRod.entity.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PricesRepository extends JpaRepository<Prices,Integer> {
    Prices findBylengthCurtainBase(Integer lengthCurtainBase);
    Optional<Prices> findById(Integer id);

    Prices findTopByOrderByIdDesc();
}
