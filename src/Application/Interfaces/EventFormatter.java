package Application.Interfaces;

import java.time.Instant;

public interface EventFormatter {
    String format(long eventID, EventDetails eventDetails, Instant timeStamp);
}
