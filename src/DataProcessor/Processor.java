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
//        support_counts.put(1, input_processor.get_one_len_rec());
//
//        ArrayList<Record> two_len_recs = generate_next_gen(support_counts.get(1));
//        support_counts.put(2, two_len_recs);
//
//        ArrayList<Record> three_len_recs = generate_next_gen(two_len_recs);
//        support_counts.put(3, three_len_recs);
//
//        ArrayList<Record> four_len_recs = generate_next_gen(three_len_recs);
        generate_combinations();

        for (Record rec: support_counts.get(4)) {
            rec.print();
        }
    }

    private void generate_combinations () {
        support_counts.put(1, input_processor.get_one_len_rec());

        for(int i=1; ; i++) {
            ArrayList<Record> pre_gen = support_counts.get(i);
            ArrayList<Record> next_gen = generate_next_gen(pre_gen);

            if(next_gen.size() > 0) {
                ArrayList<Record> rec_after_prune = prune_recs(next_gen);
                if(rec_after_prune.size() > 0)
                    support_counts.put(i+1, rec_after_prune);
                else {
                    support_counts.put(i+1, next_gen);
                    break;
                }
            }
            else
                break;
        }
    }

    private ArrayList<Record> prune_recs (ArrayList<Record> recs) {
        ArrayList<Record> result = new ArrayList<>();
        for (Record rec: recs) {
            if(rec.count >= this.min_support_count)
            result.add(rec);
        }
        return result;
    }


    private ArrayList<Record> generate_next_gen (ArrayList<Record> prev_gen) {
        ArrayList<Record> next_gen = new ArrayList<>();

        for(int i=0; i<prev_gen.size(); i++) {
            Record rec;

            for(int j=i+1; j< prev_gen.size(); j++) {
                ArrayList<Integer> uncommon = find_uncommon(prev_gen.get(i), prev_gen.get(j));

                for (Integer num: uncommon) {
                    ArrayList<Integer> temp_rec = new ArrayList<>(prev_gen.get(i).items);
                    temp_rec.add(num);

                    if(!find_record(temp_rec, next_gen)) {
                        rec = new Record("hello" + i + "prev", temp_rec);
                        rec.count = input_processor.count_record(rec);
                        next_gen.add(rec);
                    }
                }
            }
        }

        return next_gen;
    }

    private boolean find_record (ArrayList<Integer> temp_rec, ArrayList<Record> rec_list) {
        boolean isFound = false;
        if(rec_list.size() == 0) return false;
        for (Record r: rec_list) {
            if(r.match(temp_rec)){
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    private ArrayList<Integer> find_uncommon (Record r1, Record r2) {
        ArrayList<Integer> uncommon = new ArrayList<>();
        for (Integer itm: r2.items) {
            if(!r1.has(itm))
                uncommon.add(itm);
        }
        return uncommon;
    }
}
