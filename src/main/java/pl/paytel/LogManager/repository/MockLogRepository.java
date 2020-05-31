package pl.paytel.LogManager.repository;

import org.springframework.stereotype.Repository;
import pl.paytel.LogManager.model.Log;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MockLogRepository implements LogRepository {

    private static Map<Long, Log> repository = new HashMap<>();

    @Override
    public Log save(Log log) {
        Long id = generateId();
        log.setId(id);
        return repository.put(id, log);
    }

    private Long generateId() {
        return repository.keySet().stream().mapToLong(i -> i).max().orElse(0) + 1;
    }

    @Override
    public List<Log> getLogsByParameters(LocalDateTime dateFrom, LocalDateTime dateTo, Integer pageNumber, Integer pageSize) {
        //TODO pagination
        return repository.values().stream()
                .filter(l -> (dateFrom == null || !l.getDate().isBefore(dateFrom)) && (dateTo == null || !l.getDate().isAfter(dateTo)))
                .collect(Collectors.toList());
    }

}
