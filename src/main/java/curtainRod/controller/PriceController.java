package curtainRod.controller;

import curtainRod.model.CurtainData;
import curtainRod.service.LongCurtainService;
import curtainRod.service.PriceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PriceController {

    private final PriceService priceService;
    private final LongCurtainService longCurtainService;

    public PriceController(PriceService priceService, LongCurtainService longCurtainService) {
        this.priceService = priceService;
        this.longCurtainService = longCurtainService;
    }


    @GetMapping("/curtainPrice")
    public String showCurtainPrice(HttpSession session,
                           Model model) {
        try {
            // Pobranie id klienta z sesji
            String clientIdString = (String) session.getAttribute("clientId");
            Integer clientId = Integer.parseInt(clientIdString);
            List<CurtainData> curtainPrices = priceService.calculateCurtainPrice(clientId);

            model.addAttribute("curtainPrice", curtainPrices);
            //session.removeAttribute("clientId");

            return "curtainPrice";
        } catch(NumberFormatException e){
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // delete curtain
    @GetMapping("/curtainPrice/{id}")
    public String deleteCurtain(@PathVariable Integer id) {
        longCurtainService.deleteCurtainById(id);
        return "redirect:/curtainPrice";

    }
}
