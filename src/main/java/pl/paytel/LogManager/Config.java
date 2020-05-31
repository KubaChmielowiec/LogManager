package pl.paytel.LogManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.paytel.LogManager.repository.LogRepository;
import pl.paytel.LogManager.repository.MockLogRepository;

@Configuration
public class Config {

    @Bean
    public LogRepository logRepository() {
        return new MockLogRepository();
    }
}
