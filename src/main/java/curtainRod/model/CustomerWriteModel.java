package curtainRod.model;

import curtainRod.entity.Customer;
import curtainRod.entity.DictWave;
import curtainRod.entity.Longcurtain;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CustomerWriteModel {

    @NotBlank(message = "pole lokalizacja nie może być puste")
    private String place;
    @NotBlank(message = "pole email nie może być puste")
    private String email;
    private String phone;
    @Valid
    private List<Longcurtain> steps = new ArrayList<>();

    public CustomerWriteModel() {
        steps.add((new Longcurtain()));
    }

    public CustomerWriteModel(Customer customer) {
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

    public List<Longcurtain> getSteps() {
        return steps;
    }

    public void setSteps(List<Longcurtain> steps) {
        this.steps = steps;
    }

    public Customer toProject(){
        var result = new Customer();
        result.setPlace(place);
        result.setEmail(email);
        result.setPhone(phone);
        steps.forEach(step ->step.setCustomer(result)); //dostęp do projectu żeby wszystko sie zapisało na bazie
        result.setSteps(new HashSet<>(steps));
        return result;
    }



}
