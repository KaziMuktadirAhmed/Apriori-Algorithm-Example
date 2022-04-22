package DataProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Input {
    private final File inputFile;
    private final ArrayList<Record> database;

    public Input (String inputFilePath) throws FileNotFoundException {
        this.inputFile = new File(inputFilePath);
        database = new ArrayList<>();
        readFromFile();
        get_one_len_rec();
    }

    public void readFromFile () throws FileNotFoundException {
        Scanner scanInput = new Scanner(inputFile);

        while (scanInput.hasNextLine()) {
            String line = scanInput.nextLine();
            String[] items = line.split(" ", 0);

            ArrayList<Integer> ints = new ArrayList<>();
            for (int i=1; i<items.length; i++) {
                ints.add(Integer.parseInt(items[i]));
            }
            database.add(new Record(items[0], ints));
        }
    }

    public int databaseSize () {
        return database.size();
    }

    public ArrayList<Record> get_one_len_rec() {
        ArrayList<Record> one_len_count = new ArrayList<>();
        HashMap<Integer, Integer> test = new HashMap<>();

        for (Record rec: database) {
            for (Integer item: rec.items) {
                if(test.get(item) == null)
                    test.put(item, 1);
                else {
                    Integer cnt = test.get(item);
                    test.put(item, cnt+1);
                }
            }
        }

        for (Map.Entry<Integer, Integer> itemCount: test.entrySet()) {
            Record r = new Record("Test", new ArrayList<>());
            r.items.add(itemCount.getKey());
            r.count = itemCount.getValue();
            one_len_count.add(r);
        }

        return one_len_count;
    }
}
