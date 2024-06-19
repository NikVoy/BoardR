import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<BoardItem> items;
    private int totalItems;

    public Board() {
        this.items = new ArrayList<>();
    }

    public int totalItems() {
        return this.items.size();
    }

    public void addItem(BoardItem item){
        if (this.items.contains(item)){
            throw new IllegalArgumentException("Item already in the list");
        }

        this.items.add(item);
   }
}
