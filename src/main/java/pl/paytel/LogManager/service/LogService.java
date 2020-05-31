package pl.paytel.LogManager.service;

import pl.paytel.LogManager.model.Log;

import java.time.LocalDateTime;

public interface LogService {

    Log saveLog(String logString);

    Iterable<Log> getLogsByParameters(LocalDateTime dateFrom, LocalDateTime dateTo, Integer pageNumber, Integer pageSize);

}
