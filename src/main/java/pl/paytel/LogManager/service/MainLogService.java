package pl.paytel.LogManager.service;

import org.springframework.stereotype.Service;
import pl.paytel.LogManager.repository.LogRepository;

@Service
public class MainLogService {

    private LogRepository logRepository;

    public MainLogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }
}
