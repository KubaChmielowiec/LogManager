package pl.paytel.LogManager.service;

import org.junit.jupiter.api.Test;
import pl.paytel.LogManager.model.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LogFactoryTest {

    @Test
    void shouldParseSpringLog() {
        Log logFromString = LogFactory.createLogFromString("2020-06-01 09:23:21.904  WARN 30940 --- [l-1 housekeeper] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Thread starvation or clock leap detected (housekeeper delta=56m39s286ms396µs700ns).");

        assertEquals(LocalDateTime.parse("2020-06-01 09:23:21", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), logFromString.getDate());
        assertEquals("WARN", logFromString.getLevel());
        assertEquals("com.zaxxer.hikari.pool.HikariPool", logFromString.getLoggingClass());
        assertEquals("HikariPool-1 - Thread starvation or clock leap detected (housekeeper delta=56m39s286ms396µs700ns).", logFromString.getContent());
        assertNull(logFromString.getId());
    }

    @Test
    void shouldParseSpringLog2() {
        Log logFromString = LogFactory.createLogFromString("2020-06-01 12:29:12.572  INFO 30940 --- [extShutdownHook] .SchemaDropperImpl$DelayedDropActionImpl : HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'");

        assertEquals(LocalDateTime.parse("2020-06-01 12:29:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), logFromString.getDate());
        assertEquals("INFO", logFromString.getLevel());
        assertEquals(".SchemaDropperImpl$DelayedDropActionImpl", logFromString.getLoggingClass());
        assertEquals("HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'", logFromString.getContent());
        assertNull(logFromString.getId());
    }
}
