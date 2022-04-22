package DataProcessor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Processor {
    private final int min_support_count;

    private Input input_processor;
    private final HashMap<Integer, ArrayList<Record>> support_counts;

    private int total_data_count;

    public Processor(int min_support_count) {
        this.min_support_count = min_support_count;
        this.support_counts = new HashMap<>();
    }

    public void configure (String filePath) throws FileNotFoundException {
        this.input_processor = new Input(filePath);
        this.total_data_count = input_processor.databaseSize();
        support_counts.put(1, input_processor.get_one_len_rec());

        for (Record rec: support_counts.get(1)) {
            rec.print();
        }
    }

    private void generate_next_gen (ArrayList<Record> prev_gen) {
        ArrayList<Record> next_gen = new ArrayList<>();

        Record rec;

        for(int i=0; i< prev_gen.size(); i++) {
            for(int j=i+1; j< prev_gen.size(); j++) {
//                prev_gen.get(i)
                

            }
        }
    }

    private boolean find_record (Record rec, ArrayList<Record> rec_list) {
        boolean isFound = false;
        if(rec_list.size() == 0) return false;
        for (Record r: rec_list) {
            if(r.match(rec.items)){
                isFound = true;
                break;
            }
        }
        return isFound;
    }

}
