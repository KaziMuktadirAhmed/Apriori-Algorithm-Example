package DataProcessor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        generate_combinations();
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
            else break;
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
                        rec = new Record("okay", temp_rec);
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
            if(!r1.has(itm)) uncommon.add(itm);
        }
        return uncommon;
    }

    public void confidence_for_all_cond (ArrayList<Integer> target) {
        Record found = null;
        for (Map.Entry<Integer, ArrayList<Record>> recs: support_counts.entrySet()) {
            for (Record rec: recs.getValue()) {
                if(rec.match(target)) {
                    found = rec;
                    break;
                }
            }
            if(found != null) break;
        }
        if (found == null){
            System.out.println("Not found in confidence hash");
            return;
        }

        for (int i=1; i<target.size(); i++){
            for(int j=0; j<support_counts.get(i).size(); j++) {
                double conf = 0.0;
                Record cond = support_counts.get(i).get(j);
                if(found.has(cond.items)){
                    conf = (double) found.count/cond.count;
                    System.out.println("Cond: "+cond.print()+"=> item: "+found.print()+ " Conf: "+ conf*100+"%");
                }
            }
        }
    }

    private double calculate_confidence (Record target, ArrayList<Integer> given_cond) {
        double confidence = 0;
        Record found = null;
        for (Map.Entry<Integer, ArrayList<Record>> recs: support_counts.entrySet()) {
            for (Record rec: recs.getValue()) {
                if(rec.match(given_cond)) {
                    found = rec;
                    break;
                }
            }
            if(found != null) break;
        }
        if (found != null) confidence = (double) target.count/ found.count;
        return confidence;
    }

    public void calculate_support(ArrayList<Integer> target) {
        double support = 0.0;
        Record found = null;
        for (Map.Entry<Integer, ArrayList<Record>> recs: support_counts.entrySet()) {
            for (Record rec: recs.getValue()) {
                if(rec.match(target)) {
                    found = rec;
                    support = (double) found.count/this.total_data_count;
                    break;
                }
            }
            if(found != null) break;
        }
        if (found == null){
            System.out.println("Not found in confidence hash");
            return;
        }
        System.out.println("Item: " + found.print() + "Support: " + support*100 + "%");
    }
}
