package DataProcessor;

import java.util.ArrayList;

public class Processor {
    private final int min_support;

    private final ArrayList<Record> oneLengthRecords;
    private ArrayList<Record> twoLengthRecords;

    public Processor(int min_support, ArrayList<Record> oneLengthRecords) {
        this.min_support = min_support;
        this.oneLengthRecords = oneLengthRecords;
    }




}
