import java.time.LocalDate;

public class BoardItem {
    public static final String PLEASE_PROVIDE_A_TITLE_WITH_LENGTH_BETWEEN_5_AND_30_CHARS = "Please provide a title with length between 5 and 30 chars";
    public static final String DUE_DATE_SHOULD_NOT_BE_IN_THE_PAST = "Due date should not be in the past!";
    private static final int MIN_TITLE_LENGTH = 5;
    private static final int MAX_TITLE_LENGTH = 30;

    private String title;
    private LocalDate dueDate;
    private Status status;

    public BoardItem(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.status = Status.Open;
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
        if (title.length() < MIN_TITLE_LENGTH || title.length() > MAX_TITLE_LENGTH){
            throw new IllegalArgumentException(PLEASE_PROVIDE_A_TITLE_WITH_LENGTH_BETWEEN_5_AND_30_CHARS);
        }

        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    private void setDueDate(LocalDate dueDate) {
        LocalDate today = LocalDate.now();
        if(dueDate.isBefore(today)){
            throw new IllegalArgumentException(DUE_DATE_SHOULD_NOT_BE_IN_THE_PAST);
        }
        this.dueDate = dueDate;
    }

    public void revertStatus() {
        switch (this.getStatus().name()) {
            case "Todo":
                this.setStatus(Status.Open);
                break;
            case "InProgress":
                this.setStatus(Status.Todo);
                break;
            case "Done":
                this.setStatus(Status.InProgress);
                break;
            case "Verified":
                this.setStatus(Status.Done);
                break;
        }
    }

    public void advanceStatus() {
        switch (this.getStatus().name()) {
            case "Open":
                this.setStatus(Status.Todo);
                break;
            case "Todo":
                this.setStatus(Status.InProgress);
                break;
            case "InProgress":
                this.setStatus(Status.Done);
                break;
            case "Done":
                this.setStatus(Status.Verified);
                break;
        }
    }

    public String viewInfo() {

        return String.format("'%s', [%s | %s]", this.title, this.status.name(), this.dueDate);
    }


}
