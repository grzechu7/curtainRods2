package curtainRod.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;



@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Place description must not be empty")
    private String place;
    @NotBlank(message = "Email must not be empty")
    private String email;
    private String phone;

   @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
   // @OneToMany(targetEntity = Longcurtain.class,cascade = CascadeType.ALL)
    //@JoinColumn(name ="customer_id",referencedColumnName = "id")
    private Set<Longcurtain> steps;




    public Set<Longcurtain> getSteps() {
        return steps;
    }

    public void setSteps(Set<Longcurtain> steps) {
        this.steps = steps;
    }

    public Customer() {
    }

    public Customer(Customer customer) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
