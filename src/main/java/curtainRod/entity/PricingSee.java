package curtainRod.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pricingsee")
public class PricingSee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float pricingpl;
    private float pricingorg;
    private Timestamp timeprice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "longcurtain_id")
    private Longcurtain longcurtain;



}
