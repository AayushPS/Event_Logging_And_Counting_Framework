# Event_Logging_And_COunting_Framework

A minimal, thread-safe Java framework for event logging and event counting.  
This MVP uses a Singleton design, asynchronous file-based logging, and stores each event as a JSON object.

---

## 🚀 Features

- **Thread-safe** (tested in multithreaded scenarios)
- **Async logging** using `ExecutorService`
- **Singleton architecture**
- **JSON-formatted events**
- **File-based logging backend**
- **General-purpose event logger**
- **MIT License**

Planned for future:
- Maven packaging
- Customizable log path
- Multiple storage backends
- Configurable async queue
- Log rotation

---

## 📦 Core Interface

```java
public interface EventLogger {
    boolean logEvent(String name , Map<String,String> attributes);
    String readLog();
}
```

## 🧩 Default Implementation
DefaultEventLogger implements the framework with:

* A Singleton instance

* Asynchronous log writing via ExecutorService

* JSON log entries (1 event per line)

* A fixed file path (will be configurable later)

* Internal synchronization + async queue for safe multithreading

## 🛠️ Usage Example

```java
public class Demo {
    public static void main(String[] args) {
        EventLogger logger = DefaultEventLogger.getInstance();

        Map<String, String> attrs = new HashMap<>();
        attrs.put("action", "button_press");
        attrs.put("screen", "dashboard");

        logger.logEvent("UserAction", attrs);

        System.out.println(logger.readLog());
    }
}
```

## 📁 Log Format (JSON per line)

#### Example entry:

```json
{
  "timestamp": "2025-11-22T18:45:12Z",
  "name": "UserAction",
  "attributes": {
    "action": "button_press",
    "screen": "dashboard"
  }
}
```
Each event is appended as a separate JSON object.

## 🧱 Design Notes
* Singleton ensures single global logger instance

* Async write queue prevents blocking the caller

* JSON makes logs easily parseable by tools

* Fixed log path keeps MVP simple

## 🗺️ Roadmap
* Configurable log directory

* Pluggable writers (File, DB, HTTP)

* Event schema definition

* Log rotation & retention policies

* Maven/Gradle packaging

* Unit tests and benchmarks

## 📜 License
MIT License.

## 🤝 Contributing
This is currently an MVP.
Suggestions, issues, and later PRs are welcome once structure stabilizes.   