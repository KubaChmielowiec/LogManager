package pl.paytel.LogManager.model;

import java.time.LocalDateTime;

public class Log {

    private Long id;
    private LocalDateTime date;
    private String level;
    private String loggingClass;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLoggingClass() {
        return loggingClass;
    }

    public void setLoggingClass(String loggingClass) {
        this.loggingClass = loggingClass;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
