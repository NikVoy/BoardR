import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BoardItem item = new BoardItem("Rewrite everything", LocalDate.now().plusDays(2));

// compilation error if you uncomment the next line:
// item.title = "Rewrite everything immediately!!!";
// we made title private so it cannot be accessed directly anymore

        item.setTitle("Rewrite everything ASAP!!!"); // properly changing the title
        System.out.println(item.getTitle()); // properly accessing the title
        item.setTitle("Huh?"); // Exception thrown: Please provide a title with length between 5 and 30 chars


    }
}