import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoardItem {
    public static final String PLEASE_PROVIDE_A_TITLE_WITH_LENGTH_BETWEEN_5_AND_30_CHARS = "Please provide a title with length between 5 and 30 chars";
    public static final String DUE_DATE_SHOULD_NOT_BE_IN_THE_PAST = "Due date should not be in the past!";

    private static final int MIN_TITLE_LENGTH = 5;
    private static final int MAX_TITLE_LENGTH = 30;
    public static final Status INITIAL_STATUS = Status.Open;
    protected static final Status FINAL_STATUS = Status.Verified;

    private String title;
    private LocalDate dueDate;
    private Status status;
    private final List<EventLog> itemsHistory = new ArrayList<>();

    public BoardItem(String title, LocalDate dueDate) {
        validateDueDate(dueDate);
        validateTitle(title);

        this.title = title;
        this.dueDate = dueDate;
        this.status = INITIAL_STATUS;

        logEvent(String.format("Item created: %s", viewInfo()));
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        logEvent(String.format("Status changed from %s to %s", getStatus(), status));

        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateTitle(title);

        logEvent(String.format("Title changed from %s to %s", getTitle(), title));

        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        validateDueDate(dueDate);

        logEvent(String.format("DueDate changed from %s to %s", getDueDate(), dueDate));

        this.dueDate = dueDate;
    }

    public void revertStatus() {
        if (status != INITIAL_STATUS) {
            setStatus(Status.values()[status.ordinal() - 1]);
        } else {
            logEvent(String.format("Can't revert, already at %s", getStatus()));
        }
    }


    public void advanceStatus() {
        if (status != FINAL_STATUS) {
            setStatus(Status.values()[status.ordinal() + 1]);
        } else {
            logEvent(String.format("Can't advance, already at %s", getStatus()));
        }
    }

    public String viewInfo() {

        return String.format("'%s', [%s | %s]", this.title, this.status, this.dueDate);
    }

    public void displayHistory() {
        for (EventLog eventLog : itemsHistory) {
            System.out.println(eventLog.viewInfo());
        }
    }

    private void logEvent(String event) {
        itemsHistory.add(new EventLog(event));
    }


    private void validateTitle(String title) {
        if (title.length() < MIN_TITLE_LENGTH || title.length() > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException(PLEASE_PROVIDE_A_TITLE_WITH_LENGTH_BETWEEN_5_AND_30_CHARS);
        }
    }

    private void validateDueDate(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(DUE_DATE_SHOULD_NOT_BE_IN_THE_PAST);
        }
    }
}
