package curtainRod.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;



@Entity
@Table(name = "longcurtain")
public class Longcurtain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "pole nazwa pomieszczenia nie może być puste")
    private String room;
    private int lengthCurtainRod;
    private int leftWall;
    private int rightWall;
    private int move_to;
    private int wave;

    @Transient
    private DictWave  sizesystem_id;

    @Transient
    private float calculated_value;


    @Transient
    private int customer_id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Longcurtain() {
    }

    public int getId() {
        return id;
    }

    public int getMove_to() {
        return move_to;
    }

    public void setMove_to(int move_to) {
        this.move_to = move_to;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getLengthCurtainRod() {
        return lengthCurtainRod;
    }

    public void setLengthCurtainRod(int lengthCurtainRod) {
        this.lengthCurtainRod = lengthCurtainRod;
    }

    public int getLeftWall() {
        return leftWall;
    }

    public void setLeftWall(int leftWall) {
        this.leftWall = leftWall;
    }

    public int getRightWall() {
        return rightWall;
    }

    public void setRightWall(int rightWall) {
        this.rightWall = rightWall;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public DictWave getSizesystem_id() {
        return sizesystem_id;
    }

    public void setSizesystem_id(DictWave sizesystem_id) {
        this.sizesystem_id = sizesystem_id;
    }

    public float getCalculated_value() {
        return calculated_value;
    }

    public void setCalculated_value(float calculated_value) {
        this.calculated_value = calculated_value;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }


}
