package Application;

import Application.Interfaces.EventDetails;
import java.util.Map;


public final class DefaultEventDetails implements EventDetails {
    private final   String name;
    private final Map<String, String> attributes;

    public DefaultEventDetails(String eventName, Map<String, String> attributes) {
        if(eventName==null || eventName.isBlank()){
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        this.name = eventName;
        this.attributes = attributes ==null?Map.of():Map.copyOf(attributes);
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
