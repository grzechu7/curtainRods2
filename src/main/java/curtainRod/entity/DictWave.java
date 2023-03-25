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
@Table(name = "dictWave")
public class DictWave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sizePl;
    private String sizeOrg;
    private float ratio_wave;


    @Transient
    private double calculated_size;
    @Transient
    private Integer wave;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private DictSizeSystem dictSizeSystem;
}
