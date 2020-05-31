package pl.paytel.LogManager.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.paytel.LogManager.model.Log;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRepository extends PagingAndSortingRepository<Log, Long> {

    List<Log> getLogsByDateBetween(LocalDateTime dateFrom, LocalDateTime dateTo, Pageable pageable);

    List<Log> getLogsByDateBetween(LocalDateTime dateFrom, LocalDateTime dateTo);

}
