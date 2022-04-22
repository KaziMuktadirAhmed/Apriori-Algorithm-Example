package MainPac;

import DataProcessor.Input;
import DataProcessor.Processor;

import java.io.FileNotFoundException;

public class MainClass {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world");
        run();
    }

    public static void run() throws FileNotFoundException {
        Processor processor = new Processor(2);
        processor.configure("src/input.txt");
    }
}
