import java.time.LocalDate;

public class Task extends BoardItem{
    public static final String PLEASE_PROVIDE_ASSIGNEE_WITH_LENGTH_BETWEEN_5_AND_30_CHARS = "Please provide a assignee with length between 5 and 30 chars";
    private static final int MIN_ASSIGNEE_LENGTH = 5;
    private static final int MAX_ASSIGNEE_LENGTH = 30;
    private String assignee;

    public Task(String title, String assignee, LocalDate dueDate) {
        super(title, Status.Todo, dueDate);
        validateAssignee(assignee);
        this.assignee = assignee;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        validateAssignee(assignee);

        logEvent(String.format("Assignee changed from %s to %s", getAssignee(), assignee));

        this.assignee = assignee;
    }

    private void validateAssignee(String assignee) {
        if (assignee.length() < MIN_ASSIGNEE_LENGTH || assignee.length() > MAX_ASSIGNEE_LENGTH) {
            throw new IllegalArgumentException(PLEASE_PROVIDE_ASSIGNEE_WITH_LENGTH_BETWEEN_5_AND_30_CHARS);
        }
    }
}
