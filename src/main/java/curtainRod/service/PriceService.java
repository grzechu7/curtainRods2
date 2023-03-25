package curtainRod.service;

import curtainRod.entity.DictWave;
import curtainRod.entity.Longcurtain;
import curtainRod.entity.Prices;
import curtainRod.model.CurtainData;
import curtainRod.repository.DictWaveRepository;
import curtainRod.repository.LongCurtainRepository;
import curtainRod.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {
    @Autowired
    private final PricesRepository pricesRepository;
    @Autowired
    private final DictWaveRepository dictWaveRepository;
    @Autowired
    private final LongCurtainRepository longCurtainRepository;

    public PriceService(PricesRepository pricesRepository, DictWaveRepository dictWaveRepository, LongCurtainRepository longCurtainRepository) {
        this.pricesRepository = pricesRepository;
        this.dictWaveRepository = dictWaveRepository;
        this.longCurtainRepository = longCurtainRepository;
    }


 public Integer getHighestPriceId() {
     Prices priceId = pricesRepository.findTopByOrderByIdDesc();
     if (priceId != null) {
         return priceId.getId();
     } else {
         return 1;
     }
 }



    public Prices getPricesById(Integer id){
        Optional<Prices> prices = pricesRepository.findById(id);
        if(prices.isPresent()){
            return prices.get();
        } else {
            throw new RuntimeException("Prices not found with id " + id);
        }
    }

    public List<Longcurtain> getCurtainByCustomer_id(Integer customerId ){
        return longCurtainRepository.findByCustomer_id(customerId);
    }


    public List<CurtainData>  calculateCurtainPrice(Integer customerId) {

        Prices basePriceObj = pricesRepository.findBylengthCurtainBase(100);
        if (basePriceObj == null) {
            throw new RuntimeException("Nie można pobrać ceny bazowej.");
        }
        double basePrice = basePriceObj.getPrice();
        double sumFinalPrice  = 0;

        List<Longcurtain> curtains = getCurtainByCustomer_id(customerId);
        //List<Pair<String, Double>> curtainPrices = new ArrayList<>();
        List<CurtainData> curtainDataList = new ArrayList<>();
        for( int i = 0; i < curtains.size(); i++){
       // for (Longcurtain curtain : curtains) {
            //Integer curtainWave = curtain.getWave();
            Integer curtainWave = curtains.get(i).getWave();
            Integer curtainLength = curtains.get(i).getLengthCurtainRod();
            String room = curtains.get(i).getRoom();
            Integer longcurtainId = curtains.get(i).getId();

            double ratioWave = dictWaveRepository.findById(curtainWave).get().getRatio_wave();
            double ratioWave1 = dictWaveRepository.findById(1).get().getRatio_wave();

            double finalPrice;
            //System.out.println("pokaż ratio" + ratioWave);
            if(curtainLength < 199 && curtainWave == 1){
                finalPrice  = basePrice ;
            } else if (curtainLength < 199 && curtainWave > 1){
                finalPrice = basePrice * ratioWave ;
            } else {
                finalPrice = basePrice  *  Math.pow(ratioWave1, getPowerOfRatioWave(curtainLength)) * ratioWave;
            }

            sumFinalPrice += finalPrice;

            //Pair<String, Double> pair = Pair.of(room, finalPrice); // utworzenie pary
            //curtainPrices.add(pair); // dodanie pary do listy wynikowej
            CurtainData curtainData = new CurtainData(room, curtainLength, finalPrice, longcurtainId);
            curtainDataList.add(curtainData);
/*
            System.out.println("pierwszy wskaźnik " + ratioWave1);
            System.out.println("wskaźnik pierwszy do potegi " + Math.pow(ratioWave1, getPowerOfRatioWave(curtainLength)));
            System.out.println("wskaźnik " + ratioWave);

 */

        }
        curtainDataList.add(new CurtainData(sumFinalPrice));

        //System.out.println("podsumowanie " + sumFinalPrice);
        return curtainDataList;
    }

    // Metoda pomocnicza do obliczenia potęgi wskaźnika ratio_wave na podstawie długości karnisza
    private int getPowerOfRatioWave(int curtainLength) {
        if (curtainLength < 199) {
            return 1;
        } else if (curtainLength >= 200 && curtainLength <299) {
            return 2;
        } else if (curtainLength >= 300 && curtainLength < 399) {
            return 3;
        }  else if (curtainLength >= 400 && curtainLength < 499) {
            return 4;
        }  else if (curtainLength >= 500 && curtainLength < 599) {
            return 5;
        }  else if (curtainLength >= 600 && curtainLength < 699) {
            return 6;
        }  else if (curtainLength >= 700 && curtainLength < 799) {
            return 7;
        }  else if (curtainLength >= 800 && curtainLength < 899) {
            return 8;
        } else if (curtainLength >= 900) {
            return 9;
        } else {
            return 0; // Domyślnie zwracamy potęgę 0, jeśli długość karnisza nie jest równa żadnemu z parametrów
        }
    }

}
