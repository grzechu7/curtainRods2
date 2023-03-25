package curtainRod.entity;

import jdk.jfr.Name;
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
@Table(name = "dictSizesystem")
public class DictSizeSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float widthwave;
    private float dimensiony;
    private float dimensionz1;
    private int systemId;


}
