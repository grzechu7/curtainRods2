package curtainRod.model;

import curtainRod.entity.Customer;
import curtainRod.entity.DictSizeSystem;
import curtainRod.entity.DictWave;
import curtainRod.entity.Longcurtain;
import curtainRod.repository.DictSizeSystemRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Component
public class WaveCalculateForm {





    private DictSizeSystemRepository dictSizeSystemRepository;
    private List<DictWave> steps;

    public List<DictWave> getSteps() {
        return steps;
    }

    public void setSteps(List<DictWave> steps) {
        this.steps = steps;
    }

    public WaveCalculateForm(DictSizeSystemRepository dictSizeSystemRepository, List<DictWave> steps) {
        this.dictSizeSystemRepository = dictSizeSystemRepository;
        this.steps = steps;

    }


    public void calculateSize(int index) {
        DictWave step = steps.get(index);
        int percent = (int) (step.getId() == 1 ? 1 : step.getId() == 2 ? 2 : step.getId() == 3 ? 3 : step.getId() == 4 ? 4 : 0.0);
        DictSizeSystem dictSizesystem = dictSizeSystemRepository.getReferenceById(percent);
        step.setId  ((int) (dictSizesystem.getWidthwave() + dictSizesystem.getDimensiony() * dictSizesystem.getDimensionz1()));
        System.out.println("Size for Step " + index + ": " + step.getSizePl());
    }

   // @PostConstruct
    public void init() throws InterruptedException {
        calculateSize(1);
        Thread.sleep(5000);

    }


    public void setSizesystemIdForSteps(CustomerWriteModel customerWriteModel) {
        Customer customer = customerWriteModel.toProject();
        Set<Longcurtain> steps = customer.getSteps();
        for (Longcurtain step : steps) {
            Integer waveValue = step.getWave();
            if (waveValue != null) {
                DictWave sizeSystem = new DictWave();
                sizeSystem.setId(waveValue);
                step.setSizesystem_id(sizeSystem);
                System.out.println("system " + sizeSystem.getId());
            }
        }
        //return new CustomerWriteModel(customer);
    }

    public void printSizesystemIdForSteps(CustomerWriteModel current) {
        setSizesystemIdForSteps(current);
        for (Longcurtain step : current.toProject().getSteps()) {
            System.out.println("sizesystem_id for step " + step.getId() + " is " + step.getSizesystem_id().getId());
        }
    }
}
