package pl.paytel.LogManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.paytel.LogManager.model.Log;
import pl.paytel.LogManager.service.LogService;

import java.time.LocalDateTime;
import java.util.IllegalFormatException;

@RestController
@RequestMapping("/log")
public class LogController {

    private LogService logService;

    @Autowired
    public LogController (LogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public ResponseEntity<Log> saveLog(@RequestBody String logString) {
        try {
            return new ResponseEntity<>(logService.saveLog(logString), HttpStatus.CREATED);
        } catch (IllegalFormatException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect log format", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Log>> getLogs(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo,
                                             @RequestParam(required = false) Integer pageNumber,
                                             @RequestParam(required = false) Integer pageSize,
                                             @RequestParam(required = false) String resultFormat) {
        try {
            return new ResponseEntity<>(logService.getLogsByParameters(dateFrom, dateTo, pageNumber, pageSize), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
