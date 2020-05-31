package pl.paytel.LogManager.repository;

import pl.paytel.LogManager.model.Log;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRepository {

    Log save(Log log);

    List<Log> getLogsByParameters(LocalDateTime dateFrom, LocalDateTime dateTo, Integer pageNumber, Integer pageSize);

}
