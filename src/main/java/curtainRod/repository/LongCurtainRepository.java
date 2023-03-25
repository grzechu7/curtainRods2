package curtainRod.repository;

import curtainRod.entity.Customer;
import curtainRod.entity.Longcurtain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LongCurtainRepository extends LongCurtainFirstRepository, JpaRepository<Longcurtain, Integer> {

    Longcurtain findByCustomerId(Integer customerId); //zwraca tylko jeden rekord dla danego customerId
    Integer findByCustomerIdAndLengthCurtainRod(Integer customerId, Integer getLengthCurtainRod);

    //Longcurtain findByWaveAndFirstByCustomerId(Integer wave, Integer customerId); //zwraca rekord dla wybranego przez użytkownika rodzaju falowania

}
