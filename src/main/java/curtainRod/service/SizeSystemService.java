package curtainRod.service;

import curtainRod.entity.DictSizeSystem;
import curtainRod.entity.DictWave;
import curtainRod.repository.DictSizeSystemRepository;
import curtainRod.repository.DictWaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SizeSystemService {


    @Autowired
    private DictSizeSystemRepository sizeSystemRepository;
    @Autowired
    private DictWaveRepository dictWaveRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DictWave> getDictWaveByDictSizeSystemId(Integer id) {
        Optional<DictSizeSystem> dictSizeSystem = sizeSystemRepository.findById(id);

        if (dictSizeSystem == null) {
            throw new RuntimeException("DictSizeSystem with id " + id + " not found");
        }

        return dictWaveRepository.findByDictSizeSystem(dictSizeSystem);
    }


    public List<Map<String, Object>> getWavesByCustomerId(Integer customerId) {
        String sql =  "SELECT *, l.id as longcurtain_id, " +
                "CASE " +
                "WHEN l.move_to = 3 THEN (ds.dimensiony + ds.dimensionz1 + (ds.widthwave * l.length_curtain_rod/100/2)) " +
                "ELSE (ds.dimensiony + ds.dimensionz1 + (ds.widthwave * l.length_curtain_rod/100)) " +
                "END AS calculated_value " +
                "FROM dict_wave dw " +
                "INNER JOIN dict_sizesystem ds ON dw.id=ds.id " +
                "RIGHT JOIN longcurtain l ON ds.id = l.wave " +
                "INNER JOIN dict_moveto dm on dm.id = l.move_to " +
                "WHERE l.customer_id = ? " +
                "ORDER by l.id";

        return jdbcTemplate.queryForList(sql, customerId);
    }





}
