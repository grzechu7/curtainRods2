package curtainRod;


import curtainRod.entity.Prices;
import curtainRod.repository.DictWaveRepository;
import curtainRod.repository.LongCurtainRepository;
import curtainRod.repository.PricesRepository;
import curtainRod.service.PriceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@SpringBootApplication
public class CurtainRodApplication {



	public static void main(String[] args) {
		SpringApplication.run(CurtainRodApplication.class, args);


	}


	@Bean
		// klasa zarzadzalna przez springa
	Validator validator(){
		return new LocalValidatorFactoryBean();
	}





}


