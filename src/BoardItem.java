import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BoardItem {
    public static final String PLEASE_PROVIDE_A_TITLE_WITH_LENGTH_BETWEEN_5_AND_30_CHARS = "Please provide a title with length between 5 and 30 chars";
    public static final String DUE_DATE_SHOULD_NOT_BE_IN_THE_PAST = "Due date should not be in the past!";
    private static final int MIN_TITLE_LENGTH = 5;
    private static final int MAX_TITLE_LENGTH = 30;
    public static final String CAN_T_REVERT_ALREADY_AT_OPEN = "Can't revert, already at Open";
    public static final String CAN_T_ADVANCE_ALREADY_AT_VERIFIED = "Can't advance, already at Verified";

    private String title;
    private LocalDate dueDate;
    private Status status;
    private List<EventLog> itemsHistory = new ArrayList<>();

    public BoardItem(String title, LocalDate dueDate) {
        setTitle(title);
        setDueDate(dueDate);
        this.status = Status.Open;
        this.itemsHistory.add(new EventLog(String.format("Item created: '%s', [%s | %s]"
                , this.title, this.status, this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length() < MIN_TITLE_LENGTH || title.length() > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException(PLEASE_PROVIDE_A_TITLE_WITH_LENGTH_BETWEEN_5_AND_30_CHARS);
        }

        if (this.title != null) {
            this.itemsHistory.add(new EventLog(String.format("Title changed from %s to %s", this.title, title)));
        }
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        LocalDate today = LocalDate.now();
        if (dueDate.isBefore(today)) {
            throw new IllegalArgumentException(DUE_DATE_SHOULD_NOT_BE_IN_THE_PAST);
        }

        if (this.dueDate != null) {
            this.itemsHistory.add(new EventLog(String.format("DueDate changed from %s to %s",
                    this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
        }

        this.dueDate = dueDate;
    }

    public void revertStatus() {
        switch (this.getStatus().name()) {
            case "Open":
                this.itemsHistory.add(new EventLog(CAN_T_REVERT_ALREADY_AT_OPEN));
                break;
            case "Todo":
                this.itemsHistory.add(new EventLog(String.format("Status changed from %s to %s", Status.Todo, Status.Open)));
                this.setStatus(Status.Open);
                break;
            case "InProgress":
                this.itemsHistory.add(new EventLog(String.format("Status changed from %s to %s", Status.InProgress, Status.Todo)));
                this.setStatus(Status.Todo);
                break;
            case "Done":
                this.itemsHistory.add(new EventLog(String.format("Status changed from %s to %s", Status.Done, Status.InProgress)));
                this.setStatus(Status.InProgress);
                break;
            case "Verified":
                this.itemsHistory.add(new EventLog(String.format("Status changed from %s to %s", Status.Verified, Status.Done)));
                this.setStatus(Status.Done);
                break;
        }
    }

    public void advanceStatus() {
        switch (this.getStatus().name()) {
            case "Open":
                this.itemsHistory.add(new EventLog(String.format("Status changed from %s to %s", Status.Open, Status.Todo)));
                this.setStatus(Status.Todo);
                break;
            case "Todo":
                this.itemsHistory.add(new EventLog(String.format("Status changed from %s to %s", Status.Todo, Status.InProgress)));
                this.setStatus(Status.InProgress);
                break;
            case "InProgress":
                this.itemsHistory.add(new EventLog(String.format("Status changed from %s to %s", Status.InProgress, Status.Done)));
                this.setStatus(Status.Done);
                break;
            case "Done":
                this.itemsHistory.add(new EventLog(String.format("Status changed from %s to %s", Status.Done, Status.Verified)));
                this.setStatus(Status.Verified);
                break;
            case "Verified":
                this.itemsHistory.add(new EventLog(CAN_T_ADVANCE_ALREADY_AT_VERIFIED));
                break;
        }
    }

    public String viewInfo() {

        return String.format("'%s', [%s | %s]", this.title, this.status.name(), this.dueDate);
    }

    public void displayHistory() {
        for (EventLog eventLog : itemsHistory) {
            System.out.println(eventLog.viewInfo());
        }
    }


}
