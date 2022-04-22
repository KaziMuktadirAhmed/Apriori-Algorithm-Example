package DataProcessor;

import java.util.ArrayList;

public class Processor {
    private final int min_support_count;
    private final ArrayList<Record> oneLengthRecords;
    private final ArrayList<Record> twoLengthRecords;

    private int total_data_count;

    public Processor(int min_support_count, ArrayList<Record> oneLengthRecords, ArrayList<Record> twoLengthRecords) {
        this.min_support_count = min_support_count;
        this.oneLengthRecords = oneLengthRecords;
        this.twoLengthRecords = twoLengthRecords;
    }

//    public
}
