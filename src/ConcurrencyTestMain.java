import Application.DefaultEventLogger;
import Application.Interfaces.EventLogger;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrencyTestMain {
    public static void main(String[] args) throws InterruptedException{
        EventLogger logger = DefaultEventLogger.getInstance();
        ExecutorService executor = Executors.newFixedThreadPool(20);

        int threads = 50;
        int logsPerThread = 10;
        int expected = threads * logsPerThread;

        for (int i = 0; i < threads; i++) {
            int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < logsPerThread; j++) {
                    logger.logEvent("EVENT_" + threadId, Map.of("iteration", String.valueOf(j)));
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        String data = logger.readLog();
        long lines = data.lines().count();

        System.out.println("Total events logged: " + lines);
        System.out.println("Expected: " + expected);
        System.out.println(lines == expected ? "MATCH" : "MISMATCH");
    }
}
