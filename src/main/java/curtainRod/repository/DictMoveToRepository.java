package curtainRod.repository;


import curtainRod.entity.DictMoveTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictMoveToRepository extends JpaRepository<DictMoveTo,Integer> {
}
