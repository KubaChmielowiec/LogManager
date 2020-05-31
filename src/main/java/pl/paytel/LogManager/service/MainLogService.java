package pl.paytel.LogManager.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.paytel.LogManager.model.Log;
import pl.paytel.LogManager.repository.LogRepository;

import java.time.LocalDateTime;

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
    public Iterable<Log> getLogsByParameters(LocalDateTime dateFrom, LocalDateTime dateTo, Integer pageNumber, Integer pageSize) {
        boolean usePageable = pageNumber != null && pageSize != null;
        boolean useBetweenDates = dateFrom != null && dateTo != null;
        if (!useBetweenDates && !usePageable) {
            return logRepository.findAll();
        } else if (useBetweenDates && !usePageable) {
            return logRepository.getLogsByDateBetween(dateFrom, dateTo);
        } else if (!useBetweenDates && usePageable) {
            return logRepository.findAll(PageRequest.of(pageNumber, pageSize));
        } else {
            return logRepository.getLogsByDateBetween(dateFrom, dateTo, PageRequest.of(pageNumber, pageSize));
        }
    }
}
