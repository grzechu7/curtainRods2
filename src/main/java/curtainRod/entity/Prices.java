package curtainRod.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "prices")
public class Prices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int lengthCurtainBase;
    private float price;


    @ManyToOne
    @JoinColumn(name = "system_id")
    private DictSystem dictSystem;

}
