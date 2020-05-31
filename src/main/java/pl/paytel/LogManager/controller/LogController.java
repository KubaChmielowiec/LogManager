package pl.paytel.LogManager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.paytel.LogManager.service.MainLogService;

@RestController
@RequestMapping("/log")
public class LogController {

    private MainLogService mainLogService;

    public LogController (MainLogService mainLogService) {
        this.mainLogService = mainLogService;
    }
}
