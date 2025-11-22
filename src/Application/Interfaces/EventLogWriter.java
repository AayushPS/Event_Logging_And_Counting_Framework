package Application.Interfaces;

public interface EventLogWriter {
    void append(String formattedLogLine);
}
