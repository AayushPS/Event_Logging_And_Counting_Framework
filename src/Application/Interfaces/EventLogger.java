package Application.Interfaces;

import java.util.Map;

public interface EventLogger {
    boolean logEvent(String name , Map<String,String> attributes);
    String readLog();
}
