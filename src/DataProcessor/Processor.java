package DataProcessor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Processor {
    private final int min_support_count;

    private Input input_processor;
    private HashMap<Integer, ArrayList<Record>> support_counts;

    private int total_data_count;

    public Processor(int min_support_count) {
        this.min_support_count = min_support_count;
        this.support_counts = new HashMap<>();
    }

    public void configure (String filePath) throws FileNotFoundException {
        this.input_processor = new Input(filePath);
        this.total_data_count = input_processor.databaseSize();
        support_counts.put(1, input_processor.get_one_len_rec());

    }


}
