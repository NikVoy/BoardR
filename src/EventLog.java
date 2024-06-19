import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class EventLog {
    public static final String DESCRIPTION_CANNOT_BE_EMPTY = "Description cannot be empty";
    private final String description;
    private final LocalDateTime timestamp;

    public EventLog(String description) {
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public EventLog() {
        throw new IllegalArgumentException(DESCRIPTION_CANNOT_BE_EMPTY);
    }

    public String getDescription() {
        return description;
    }

    public String viewInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
        String formatDateTime = this.timestamp.format(formatter);

        return String.format("[%s] %s", formatDateTime, this.description);
    }
}
