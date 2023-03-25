package curtainRod.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// klasa potrzebna do wyswietlania ceny karniszy wraz z nazwą pomieszczenia oraz wymiarami karnisza

@NoArgsConstructor
@Getter
@Setter
public class CurtainData {
    private String room;
    private int curtainLength;
    private double finalPrice;
    private int longcurtainId;
    private double sumFinalPrice;

    public CurtainData(String room, int curtainLength, double finalPrice, int longcurtainId) {
        this.room = room;
        this.curtainLength = curtainLength;
        this.finalPrice = finalPrice;
        this.longcurtainId = longcurtainId;
    }

    public CurtainData(double sumFinalPrice) {
        this.sumFinalPrice = sumFinalPrice;
    }
}
