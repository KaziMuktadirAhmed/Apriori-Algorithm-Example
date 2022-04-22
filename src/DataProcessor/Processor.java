package DataProcessor;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Processor {
    private final int min_support_count;

    private Input input_processor;
    private ArrayList<Record> oneLengthRecords;
    private ArrayList<Record> twoLengthRecords;

    private int total_data_count;

    public Processor(int min_support_count) {
        this.min_support_count = min_support_count;
    }

    public void configure (String filePath) throws FileNotFoundException {
        this.input_processor = new Input(filePath);
        this.oneLengthRecords = input_processor.get_one_len_rec();
        this.total_data_count = input_processor.databaseSize();
    }


}
