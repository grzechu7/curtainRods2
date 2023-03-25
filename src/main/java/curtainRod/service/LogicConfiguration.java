package curtainRod.service;

import curtainRod.repository.CustomerFirstRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogicConfiguration {

    @Bean
    CustomerService customerService(final CustomerFirstRepository repository){
        return new CustomerService(repository);
    }
}
