package Application;

import Application.Interfaces.*;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public final class DefaultEventLogger implements EventLogger {

    private final AtomicLong counter;
    private final EventFormatter formatter;
    private final EventLogWriter writer;
    private final EventLogReader reader;
    private final Path path;

    private DefaultEventLogger() {
        this.counter = new AtomicLong(0);
        this.formatter = new JsonEventFormatter();
        this.path = Path.of("logs","event.log");
        this.writer = new FileEventLogWriter(path);
        this.reader = new FileEventLogReader(path);
    }

    private static class Holder{
        private static final DefaultEventLogger INSTANCE = new DefaultEventLogger();
    }

    public static DefaultEventLogger getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public boolean logEvent(String name, Map<String, String> attributes) {
        long id = counter.incrementAndGet();
        EventDetails eventDetails = new DefaultEventDetails(name, attributes);
        String json = formatter.format(id, eventDetails, Instant.now());
        writer.append(json);
        return true;
    }

    @Override
    public String readLog() {
        return reader.readAll();
    }
}
