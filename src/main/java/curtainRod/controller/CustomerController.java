package curtainRod.controller;

import curtainRod.entity.Customer;
import curtainRod.entity.DictWave;
import curtainRod.entity.Longcurtain;
import curtainRod.model.CustomerWriteModel;
import curtainRod.repository.DictSizeSystemRepository;
import curtainRod.repository.DictWaveRepository;
import curtainRod.repository.LongCurtainFirstRepository;
import curtainRod.service.CustomerService;
import curtainRod.service.SizeSystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private final CustomerService service;


    public CustomerController(CustomerService service) {
        this.service = service;

    }



    @GetMapping
    String showIndex(HttpSession session,
            Model model){
        //var customerToEdit = new CustomerWriteModel();
       // customerToEdit.setPlace("test"); // wyświetlanie słowa w formularzu
        model.addAttribute("customer", new CustomerWriteModel());
        long highestClientId = service.getHighestClientId();
        session.setAttribute("clientId", highestClientId + 1);
/*
        List<String> options = Arrays.asList("na lewo", "na prawo");
        model.addAttribute("options", options);

 */
        return "index";
    }

    @PostMapping
    String addCustomer(@ModelAttribute("customer") @Valid CustomerWriteModel current,
                       BindingResult bindingResult, // walidacja czy poprzedni argument miał jakieś błedy
                      //@ModelAttribute("wave") @Valid DictWave dictWave, // działa gdy mamy tylko w formularzu html name = "wave" bez th:field="*{steps[__${stepStat.index}__].wave}"
                       @RequestParam("clientId")String clientId, HttpSession session,

                       Model model){// zaraz po pozytywnym dodaniu bedziemy modyfikować CustomerWriteModel
        if(bindingResult.hasErrors()){
            return "index";
        }
        session.setAttribute("clientId", clientId);
        model.addAttribute("customer", new CustomerWriteModel());

        service.save(current);

        model.addAttribute("message", "Dodano wymiary");
        return "redirect:/curtainRoads";
    }


    @PostMapping(params = "addStep")
    String addCurtainStep(@ModelAttribute("customer") CustomerWriteModel current){
        current.getSteps().add(new Longcurtain()); // bierzemy listę kroków i do tej listy dodajemy nową długość karniszy, znak + na stronie
        return "index";
    }

/*
        int waveValue = step.getId();
        // waveValue2 = step.getSizeOrg();
        System.out.println("Wybrana wartość: " + waveValue);
        //System.out.println("Wybrana wave : " + waveValue2);

        ////////////////////////////
        DictSizeSystem dictSizesystemDB = sizeSystemRepository.findById(waveValue)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono rekordu"));

        String sqlQuery = "select ds.id, ds.dimensiony, ds.dimensionz1, ds.system_id, ds.widthwave from dict_sizesystem ds where ds.id = :waveValue";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("waveValue", waveValue);

        DictSizeSystem dictSizesystemSQL = (DictSizeSystem) query.getSingleResult();

        System.out.println("Wybrana wartość: " + step);
        System.out.println("Parametr 'waveValue' w zapytaniu SQL: " + waveValue);
        System.out.println("Wybrany rekord z bazy danych: " + dictSizesystemDB);




        /////////////////////////////////
        DictSizeSystem dictSizesystem = sizeSystemRepository.findById(waveValue).orElse(null);

        if(dictSizesystem == null) {
            throw new RuntimeException("Nie znaleziono rekordu");
           // System.out.println(waveValue);
        }
        double size = dictSizesystem.getWidthwave() + 9.5 + 15.5;
        step.setCalculated_size(size);

        // Zapisz encję steps w bazie danych
        sizeSystemRepository.save(dictSizesystem);

 */

    /*
        Integer wave = dictWave.getWave();
       // List<Longcurtain> wave = current.getSteps();
        System.out.println("wybrana wartośc wave " + wave);

         */

/*
    // wyświetlenie wartości pola "wave" wybranej przez użytkownika
        List<Longcurtain> steps = current.getSteps();

        for (Longcurtain step : steps) {

            //Integer waveValue = step.getWave();

            DictWave sizeSystem = new DictWave();
            sizeSystem.setId(step.getWave());

            System.out.println("Wybrana wartość pola 'wave': " + step.getWave());
            System.out.println("Wybrana wartość pola 'sizeSystem': " + sizeSystem.getId());

            // przypisanie obiektu SizeSystem do kolumny "sizesystem_id" obiektu Longcurtain
            step.setSizesystem_id(sizeSystem);




        }
 */

    // zapisanie obiektu DictWave do bazy danych
        /*
        DictWave sizeSystem = new DictWave();
        sizeSystem.setId(sizeSystem.getWave());
        dictWaveRepository.save(sizeSystem);

         */

/*
// przypisanie obiektu DictWave do kolumny "sizesystem_id" obiektu Longcurtain
        Customer customer = current.toProject();
        Set<Longcurtain> steps = customer.getSteps();
        for (Longcurtain step : steps) {
            Integer waveValue = step.getWave();

            if (waveValue != null) {
                DictWave sizeSystem = new DictWave();
                sizeSystem.setId(waveValue);
                //sizeSystem.setId(step.getWave());
                step.setSizesystem_id(sizeSystem);
                System.out.println("system " + sizeSystem.getId());
                // step.setSizesystem_id(sizeSystem.getId());
            } else{
                System.out.println("jest null");
            }
        }
 */

}
