package curtainRod.repository;

import curtainRod.entity.Customer;
import curtainRod.entity.Longcurtain;

import java.util.List;

public interface LongCurtainFirstRepository {

    Longcurtain getCurtainById(Integer id);
    List<Longcurtain> findAll();


    //szuka klienta o konkretnym Id
    List<Longcurtain> findByCustomer_id(Integer customer_id);



    void deleteById(Integer id);
    Longcurtain save(Integer id);

/*
    Page<Longcurtain> findAll(Pageable page);



    boolean existsById(Integer id);
   // boolean existsByDoneIsFalseAndAndGroup_Id(Integer groupId);
   // List<Longcurtain> findAllByGroup_Id (Integer groupId);// na podstawie grupy pobieramy wszystkie taski zwiazane z ta grupą

 */



    //List<Task> findByDone(@Param("state") boolean done); // mozemy wpisywać w html true lub false i tak szukac search
  //  List<Longcurtain> findByDone(boolean done);

}
