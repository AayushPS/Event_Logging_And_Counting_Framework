package Application;

import Application.Interfaces.EventLogWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileEventLogWriter implements EventLogWriter {
    private final Path logPath;
    public FileEventLogWriter(Path logPath) {
        this.logPath = logPath;
        FileUtils.ensurePathExists(logPath);
    }

    @Override
    public void append(String formattedLogLine) {
        try(var fileBufferedWriter = Files.newBufferedWriter(logPath, StandardCharsets.UTF_8,StandardOpenOption.CREATE ,StandardOpenOption.APPEND)) {
            fileBufferedWriter.write(formattedLogLine+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
