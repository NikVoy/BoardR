import java.time.LocalDate;

public class BoardItem {
    String title;
    LocalDate dueDate;
    Status status;

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
