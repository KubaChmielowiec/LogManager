package pl.paytel.LogManager.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.paytel.LogManager.model.Log;
import pl.paytel.LogManager.service.LogService;

import java.util.ArrayList;
import java.util.IllegalFormatException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LogController.class)
@ComponentScan("pl.paytel.LogManager")
public class LogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LogService logService;

    @Test
    public void shouldSaveLogWithoutProblem() throws Exception {
        when(logService.saveLog(anyString())).thenReturn(new Log());

        mockMvc.perform(post("/log").content("anyContent"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnUprocessableEntityWhenIncorrectLogFormat() throws Exception {
        when(logService.saveLog(anyString())).thenThrow(IllegalFormatException.class);

        mockMvc.perform(post("/log").content("anyContent"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturnInternalServerErrorWhenAnyOtherExceptionOccurs() throws Exception{
        when(logService.saveLog(anyString())).thenThrow(NullPointerException.class);

        mockMvc.perform(post("/log").content("anyContent"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldReturnAllAddedLogs() throws Exception {
        when(logService.getLogsByParameters(any(), any(), any(), any())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/log"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnInternalServerErrorWhenAnyExceptionOccurs() throws Exception {
        when(logService.getLogsByParameters(any(), any(), any(), any())).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/log"))
                .andExpect(status().isInternalServerError());
    }
}
