package curtainRod.repository;

import curtainRod.entity.DictSizeSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DictSizeSystemRepository extends JpaRepository<DictSizeSystem, Integer> {


    Optional<DictSizeSystem> findById(Integer id);
}
