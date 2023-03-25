package curtainRod.controller;

import curtainRod.entity.DictWave;
import curtainRod.service.SizeSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/dictwave")
public class DictWaveController {
    @Autowired
    private SizeSystemService dictWaveService;

    @GetMapping("/dictwave/{dictSizeSystemId}")
    public List<DictWave> getDictWaveByDictSizeSystemId(@PathVariable Integer dictSizeSystemId) {
        return dictWaveService.getDictWaveByDictSizeSystemId(dictSizeSystemId);
    }



}
