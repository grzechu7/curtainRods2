package curtainRod.controller;


import curtainRod.entity.Prices;
import curtainRod.model.CurtainData;
import curtainRod.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class ProjectController {
    @GetMapping("/projects")
    public String showProjects() {
        return "projects";
    }

    @Autowired
    private PriceService priceService;

    @GetMapping("/highestPriceId")
    public ResponseEntity<Integer> getHighestPriceId() {
        Integer highestPriceId = priceService.getHighestPriceId();
        return ResponseEntity.ok(highestPriceId);
    }

    /*
        W tym kodzie, zamiast zwracać prosty typ Integer, metoda getHighestPriceId() zwraca ResponseEntity<Integer>.
        Wykorzystanie ResponseEntity umożliwia zwrócenie nie tylko samej wartości, ale także kodu statusu HTTP oraz nagłówków.
        W tym przypadku, metoda ResponseEntity.ok() tworzy odpowiedź HTTP o kodzie statusu 200 OK i zawiera wartość highestPriceId.

         */
    @GetMapping("/getPriceId")
    public ResponseEntity<Prices> getPriceId(Integer id) {
        Prices getPriceId = priceService.getPricesById(1);
        return ResponseEntity.ok(getPriceId);
    }


    @GetMapping("/getPrice/{id}")
    public ResponseEntity<List<CurtainData>> getPrice(@PathVariable Integer id) {
        List<CurtainData> curtainPrices = priceService.calculateCurtainPrice(id);
        return ResponseEntity.ok(curtainPrices);
    }
}


