package curtainRod.controller;

import curtainRod.entity.Customer;
import curtainRod.entity.DictMoveTo;
import curtainRod.entity.DictWave;
import curtainRod.entity.Longcurtain;
import curtainRod.repository.DictMoveToRepository;
import curtainRod.repository.DictWaveRepository;
import curtainRod.repository.LongCurtainFirstRepository;
import curtainRod.repository.LongCurtainRepository;
import curtainRod.service.CustomerService;
import curtainRod.service.LongCurtainService;
import curtainRod.service.SizeSystemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
public class LongCurtainController {

    private final CustomerService service;
    private final LongCurtainService repository;
    private final SizeSystemService sizeSystemService;
    private final LongCurtainRepository longCurtainRepository;
    private final DictMoveToRepository dictMoveToRepository;
    private final DictWaveRepository dictWaveRepository;
    private final LongCurtainFirstRepository longCurtainFirstRepository;


    public LongCurtainController(CustomerService service, LongCurtainService repository, SizeSystemService sizeSystemService, LongCurtainRepository longCurtainRepository, DictMoveToRepository dictMoveToRepository, DictWaveRepository dictWaveRepository, LongCurtainFirstRepository longCurtainFirstRepository) {
        this.service = service;
        this.repository = repository;
        this.sizeSystemService = sizeSystemService;
        this.longCurtainRepository = longCurtainRepository;
        this.dictMoveToRepository = dictMoveToRepository;
        this.dictWaveRepository = dictWaveRepository;
        this.longCurtainFirstRepository = longCurtainFirstRepository;
    }


    @GetMapping("/curtainRoads")
    public String showForm(HttpSession session,
                           Model model) {
        try {
            // Pobranie id klienta z sesji
            String clientIdString = (String) session.getAttribute("clientId");
            Integer clientId = Integer.parseInt(clientIdString);

            List<Map<String, Object>> sizeWal = sizeSystemService.getWavesByCustomerId(clientId);

            model.addAttribute("sizeWal", sizeWal);
            //session.removeAttribute("clientId");

            return "curtainRoads";
        }catch (NumberFormatException e){
            return "redirect:/";
        } catch (Exception e) {
            // Obsługa innych wyjątków
            e.printStackTrace();
            return "error";
        }
    }

   @GetMapping("/edit_curtain/{id}")
    //@GetMapping("/curtaintest/{id}")
    public String editToUpdateCurtain(@PathVariable("id") int id,
                                      HttpSession session,
                                      Model model) {
        Longcurtain longcurtain = longCurtainRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid longcurtain Id:" + id));
        //System.out.println("pokaż id " + longcurtain.getId());
        model.addAttribute("longcurtain", longcurtain);

        List<DictMoveTo> dictMoveTos = dictMoveToRepository.findAll();
        model.addAttribute("dictMoveTos", dictMoveTos);

        List<DictWave> dictWaves = dictWaveRepository.findAll();
        model.addAttribute("dictWaves", dictWaves);

       session.setAttribute("curtainId", longcurtain.getId());

        return "edit_curtain";
    }

    @PostMapping("/curtainRoads/update")
    public String updateCurtain(@RequestParam("curtainId")Integer curtainId,
                                @ModelAttribute Longcurtain curtain) {
        Longcurtain existingCurtain = longCurtainRepository.findById(curtainId).orElse(null);
        if (existingCurtain == null) {
            return "error";
        }

        existingCurtain.setRoom(curtain.getRoom());
        existingCurtain.setLengthCurtainRod(curtain.getLengthCurtainRod());
        existingCurtain.setLeftWall(curtain.getLeftWall());
        existingCurtain.setRightWall(curtain.getRightWall());
        existingCurtain.setMove_to(curtain.getMove_to());
        existingCurtain.setWave(curtain.getWave());
        longCurtainRepository.save(existingCurtain);

        return "redirect:/curtainRoads";
    }


    // delete curtain
    @GetMapping("/curtainRoads/{id}")
    public String deleteCurtain(@PathVariable Integer id) {
        repository.deleteCurtainById(id);
        return "redirect:/curtainRoads";

    }

    //add curtain

    @GetMapping("/add_curtain")
    String showAddCurtain(HttpSession session,
                     Model model){
        model.addAttribute("longcurtainUp", new Longcurtain());
        long highestClientId = service.getHighestClientId();
        session.setAttribute("clientIdl", highestClientId);

        List<DictMoveTo> dictMoveTos = dictMoveToRepository.findAll();
        model.addAttribute("dictMoveTos", dictMoveTos);

        List<DictWave> dictWaves = dictWaveRepository.findAll();
        model.addAttribute("dictWaves", dictWaves);

        return "add_curtain";
    }


    @PostMapping("/curtainRoads/add")
    public String addCustomer(@ModelAttribute("longcurtainUp") @Valid Longcurtain longcurtain,
                              BindingResult bindingResult, // walidacja czy poprzedni argument miał jakieś błedy
                              @RequestParam("clientIdl") int clientIdl){
        if(bindingResult.hasErrors()){
            return "add_curtain";
        }
        Customer customer = new Customer();
        customer.setId(clientIdl);
        longcurtain.setCustomer(customer);

        //System.out.println("pokaż co masz " + longcurtain.getCustomer().getId());
        longCurtainRepository.save(longcurtain);

        return "redirect:/curtainRoads";
    }





}


 /*
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                session.removeAttribute("clientId");
            }
        };


        // Zaplanowanie zadania na 60 sekund od teraz
        timer.schedule(task, 60000);

        */
 /*
        for(Map sizew : sizeWal) {
            System.out.println("lista długości karniszy" + sizew);
        }

         */

/*
//model.addAttribute("longCurtain", repository.getCurtainByCustomer_id(clientId));
        List<Longcurtain> curtains = repository.getCurtainByCustomer_id(clientId);
        List<Map<String, Object>> sizeWal = sizeSystemService.getWavesByCustomerId(clientId);

        // Zapisanie informacji w jednej liście
        List<Object> data = new ArrayList<>();
        data.addAll(curtains);
        data.addAll(sizeWal);

        for(Map sizew : sizeWal) {
            System.out.println("lista długości karniszy" + sizew);
        }

        model.addAttribute("sizeWal", sizeWal);
        session.removeAttribute("clientId");


        return "curtainRoads";


        // @GetMapping("/edit_curtain/{id}")
    @GetMapping("/curtaintest/{id}")
    public String editCurtainForm(@PathVariable Integer id, Model model) {
        List<Map<String, Object>> LongCurtainId = sizeSystemService.getLongCurtainId(id);

        for(Map editCurtain : LongCurtainId) {
            System.out.println("lista długości karniszy" + editCurtain);
        }
        model.addAttribute("longCurtain", new Longcurtain());
        model.addAttribute("longCurtain", LongCurtainId);
       // return "edit_curtain";
        return "curtaintest";
    }
 */

/*
        String clientIdString  = (String) session.getAttribute("clientId");
        Integer clientId = Integer.parseInt(clientIdString);
        longcurtain.setId(clientId);

        */

/*
        String clientIdString  = (String) session.getAttribute("clientIdl");
        Integer clientId = Integer.parseInt(clientIdString);

        longcurtain.setId(clientId);

 */
//longcurtain.getCustomer((Integer) session.getAttribute("clientIdl"));



// Long customerId = (Long) session.getAttribute("clientIdl");

