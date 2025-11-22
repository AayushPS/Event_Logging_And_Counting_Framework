package Application;

import Application.Interfaces.EventLogReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileEventLogReader implements EventLogReader {
    private final Path logPath;
    public FileEventLogReader(Path logPath) {
        this.logPath = logPath;
        FileUtils.ensurePathExists(logPath);
    }

    @Override
    public String readAll() {
        try {
            return Files.readString(logPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
