package pl.paytel.LogManager.service;

import org.springframework.stereotype.Service;
import pl.paytel.LogManager.model.Log;
import pl.paytel.LogManager.repository.LogRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MainLogService implements LogService {

    private LogRepository logRepository;

    public MainLogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Log saveLog(String logString) {
        return logRepository.save(LogFactory.createLogFromString(logString));
    }

    @Override
    public List<Log> getLogsByParameters(LocalDateTime dateFrom, LocalDateTime dateTo, Integer pageNumber, Integer pageSize) {
        return logRepository.getLogsByParameters(dateFrom, dateTo, pageNumber, pageSize);
    }
}
