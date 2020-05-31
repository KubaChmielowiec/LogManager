package pl.paytel.LogManager.service;

import pl.paytel.LogManager.model.Log;

import java.time.LocalDateTime;
import java.util.List;

public interface LogService {

    Log saveLog(String logString);

    List<Log> getLogsByParameters(LocalDateTime dateFrom, LocalDateTime dateTo, Integer pageNumber, Integer pageSize);

}
