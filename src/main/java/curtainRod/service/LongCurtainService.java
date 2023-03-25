package curtainRod.service;

import curtainRod.entity.Customer;
import curtainRod.entity.DictMoveTo;
import curtainRod.entity.Longcurtain;
import curtainRod.model.CustomerWriteModel;
import curtainRod.repository.DictMoveToRepository;
import curtainRod.repository.LongCurtainFirstRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class LongCurtainService {


private final LongCurtainFirstRepository repository;
private final DictMoveToRepository dictMoveToRepository;

//private CustomerWriteModel customerWriteModel ;




    public LongCurtainService(LongCurtainFirstRepository repository, DictMoveToRepository dictMoveToRepository) {
        this.repository = repository;
        this.dictMoveToRepository = dictMoveToRepository;

    }


    public Longcurtain getCurtainById(Integer id ){
        return repository.getCurtainById(id);
    }

    public void deleteCurtainById(Integer longCurtainId) {
        repository.deleteById(longCurtainId);
    }

    public void saveCurtain(Integer longCurtainId) {
        repository.save(longCurtainId);
    }

    public List<Longcurtain> getCurtainByCustomer_id(Integer customer_id ){
        return repository.findByCustomer_id(customer_id);
    }
    public void calculateCurtainByCustomer_id(Integer customer_id) {
        List<Longcurtain> curtains = getCurtainByCustomer_id(customer_id);

        for (Longcurtain curtain : curtains) {
            Integer curtainWave = curtain.getWave();

            System.out.println(curtainWave);
        }

        //System.out.flush();
    }

    public void calculateMoveTO() {
        List<DictMoveTo> moveToList = dictMoveToRepository.findAll();;

        for (DictMoveTo moveTo : moveToList) {
            String descMoveTo = moveTo.getDescriptionPl();

            System.out.println(descMoveTo);
        }
    }

    @PostConstruct
    public void init()  {
       //calculateCurtainByCustomer_id(48);
       //calculateMoveTO();
        //Thread.sleep(5000);

    }




    //odczytujemy wymiary
    public List<Longcurtain> readAll(){
        return repository.findAll();

    }

}


/*
    public Customer getWafeUser(){
        return customerWriteModel.toProject();
    }

    public void getNumberWave(){
        List<Longcurtain> waveToList = (List<Longcurtain>) getWafeUser();

        for (Longcurtain waveTo : waveToList) {
            Integer waveInt = waveTo.getWave();

            System.out.println(waveInt);
        }

    }

 */
