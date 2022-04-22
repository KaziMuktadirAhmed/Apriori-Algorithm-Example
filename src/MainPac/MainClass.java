package MainPac;

import DataProcessor.Input;

import java.io.FileNotFoundException;

public class MainClass {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world");
        run();
    }

    public static void run() throws FileNotFoundException {
        Input input = new Input("src/input.txt");
        input.readFromFile();
    }
}
