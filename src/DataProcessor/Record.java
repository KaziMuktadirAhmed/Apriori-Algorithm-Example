package DataProcessor;

import java.util.ArrayList;
import java.util.Objects;

public class Record {
    public final ArrayList<Integer> items;

    public Record(ArrayList<Integer> items) {
        this.items = items;
    }

    public boolean has (Integer targetItem) {
        boolean isInside = false;
        for (Integer itm : items) {
            if (Objects.equals(itm, targetItem)) {
                isInside = true;
                break;
            }
        }
        return  isInside;
    }

    public boolean has (ArrayList<Integer> targetItems) {
        boolean isInside = true;
        for (Integer itm : targetItems) {
            if(!has(itm)) {
                isInside = false;
                break;
            }
        }
        return isInside;
    }
}
