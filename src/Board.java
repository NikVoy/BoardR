import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final String ITEM_ALREADY_IN_THE_LIST = "Item already in the list";

    private final List<BoardItem> items;

    public Board() {
        this.items = new ArrayList<>();
    }

    public void addItem(BoardItem item){
        if (this.items.contains(item)){
            throw new IllegalArgumentException(ITEM_ALREADY_IN_THE_LIST);
        }

        this.items.add(item);
   }

    public int totalItems() {
        return this.items.size();
    }

}
