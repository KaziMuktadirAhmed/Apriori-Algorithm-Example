package DataProcessor;

import java.util.ArrayList;
import java.util.Objects;

public class Record {
    public final String recordId;
    public final ArrayList<Integer> items;

    public Record(String recordId, ArrayList<Integer> items) {
        this.recordId = recordId;
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
        return isInside;
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

    public boolean match (ArrayList<Integer> toBeMatched) {
        boolean isMatch = false;
        if (toBeMatched.size() == items.size()) {
              isMatch = has(toBeMatched);
        }
        return  isMatch;
    }
}
