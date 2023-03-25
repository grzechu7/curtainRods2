package curtainRod.repository;

import curtainRod.entity.Customer;
import curtainRod.entity.Longcurtain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CustomerFirstRepository,JpaRepository<Customer,Integer> {

    // @Query("SELECT new com.javatechie.jpa.dto.OrderResponse(c.name , p.productName) FROM Customer c JOIN c.products p")
    //  public List<OrderResponse> getJoinInformation();
    /*
    @Override
    //@Query("select distinct p from Project p left join fetch p.steps") - zapis przy left join wraz z uikalnymi wynikami
    @Query("select distinct  p from Customer p join fetch p.steps") // domyslnie inner join zapytanie HQL Hibernate SQL wszystkie kroki zwiazane z grupami też były pobrane jedno zapytanie zamiast dwóch (najperw zapytanie do grupy później do pjedynczych tasków)
    List<Customer> findAll();

     */

}

