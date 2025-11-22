# Event_Logging_And_COunting_Framework

A minimal, thread-safe Java framework for application-level event logging and event counting.  
It uses a Singleton design, asynchronous log writes, JSON-formatted log entries, and a file-based storage backend.  
Currently an MVP with a simple API and one default implementation (`DefaultEventLogger`).

---

## 🚀 Features

- **Thread-safe** by design
- **Async logging** powered by an `ExecutorService`
- **Singleton instance** ensures centralized logging
- **File-based JSON logs** for predictable, structured storage
- **Simple, general-purpose API**
- **MIT Licensed**
- **Planned:** Maven packaging, config system, pluggable backends

---

## 📦 Interface

Your core abstraction:

```java
public interface EventLogger {
    boolean logEvent(String name , Map<String,String> attributes);
    String readLog();
}
