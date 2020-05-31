package pl.paytel.LogManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.paytel.LogManager.model.Log;
import pl.paytel.LogManager.service.LogService;

import java.time.LocalDateTime;
import java.util.IllegalFormatException;
import java.util.List;

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
    public ResponseEntity<List<Log>> getLogs(@RequestParam(required = false) LocalDateTime dateFrom,
                                             @RequestParam(required = false) LocalDateTime dateTo,
                                             @RequestParam(required = false) Integer pageNumber,
                                             @RequestParam(required = false) Integer pageSize,
                                             @RequestParam(required = false) String resultFormat) {
        try {
            List<Log> logsByParameters = logService.getLogsByParameters(dateFrom, dateTo, pageNumber, pageSize);
            return new ResponseEntity<>(logsByParameters, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
