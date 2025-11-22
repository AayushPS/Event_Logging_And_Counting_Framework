package Application;

import Application.Interfaces.EventDetails;
import Application.Interfaces.EventFormatter;

import java.time.Instant;
import java.util.Map;

public class JsonEventFormatter implements EventFormatter {
    @Override
    public String format(long eventId, EventDetails eventDetails, Instant timeStamp) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":").append(eventId)
                .append(",\"name\":\"").append(escape(eventDetails.getName()))
                .append("\",\"timestamp\":\"").append(timeStamp.toString())
                .append("\",\"attributes\":{");

        Map<String, String> attributes = eventDetails.getAttributes();
        boolean first = true;
        for (var entry : attributes.entrySet().stream().sorted(Map.Entry.comparingByKey()).toList()) {
            if (!first) sb.append(",");
            first = false;
            sb.append("\"").append(escape(entry.getKey()))
                    .append("\":\"").append(escape(entry.getValue())).append("\"");
        }

        sb.append("}}");
        return sb.toString();
    }
    private String escape(String s) {
        if (s == null) {
            return "";
        }
        return s
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

}
