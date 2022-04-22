package DataProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    private File inputFile;
    private ArrayList<Record> database;

    public Input (String inputFilePath) {
        this.inputFile = new File(inputFilePath);
        database = new ArrayList<>();
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
//            new Record(items[0], ints).print();
            database.add(new Record(items[0], ints));
        }

    }
}
