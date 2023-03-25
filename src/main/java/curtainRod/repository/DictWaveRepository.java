package curtainRod.repository;

import curtainRod.entity.DictSizeSystem;
import curtainRod.entity.DictWave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DictWaveRepository extends JpaRepository<DictWave,Integer> {

    //List<DictWave> findById(DictSizeSystem dictSizeSystem);
    List<DictWave> findByDictSizeSystem(Optional<DictSizeSystem> dictSizeSystem);
    Optional<DictWave> findById(Integer Id);

}
