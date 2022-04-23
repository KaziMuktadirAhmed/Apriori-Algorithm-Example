package MainPac;

import DataProcessor.Processor;
import DataProcessor.Record;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world");
        run();
    }

    public static void run() throws IOException {
        initial_input();
        Processor processor = new Processor(2);
        processor.configure("src/input.txt");
        processor.confidence_for_all_cond(conf_inp());
    }

    public static void initial_input() throws IOException {
        System.out.println("Do you want to add to the existing data ? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String usr_inp = scanner.nextLine();
        if(usr_inp.equalsIgnoreCase("Y")){
            System.out.println("Input format: <record size> <item 1> <item 2> <item 3> ..... \nTo end input 0");

            int inp_len = scanner.nextInt();
            while (inp_len != 0) {
                ArrayList<Integer> inp_list = new ArrayList<>();
                for(int i=0; i<inp_len; i++) {
                    inp_list.add(scanner.nextInt());
                }
                write_in_file(inp_len, inp_list);
                inp_len = scanner.nextInt();
            }
        }
    }

    public static ArrayList<Integer> conf_inp() {
        System.out.println("Please input record: (Input format: <record size> <item 1> <item 2> <item 3> .....)");
        Scanner scanner = new Scanner(System.in);
        int inp_len = scanner.nextInt();
        ArrayList<Integer> inp_list = new ArrayList<>();
        for(int i=0; i<inp_len; i++) {
            inp_list.add(scanner.nextInt());
        }
        return inp_list;
    }

    public static void write_in_file(int len, ArrayList<Integer> items) throws IOException {
        FileWriter fileWriter = new FileWriter(new File("src/input.txt"), true);
        String id = "\nS0"+len;
        fileWriter.write(id+" ");
        for (Integer itm: items) {
            fileWriter.write(itm+" ");
        }
        fileWriter.close();
    }
}











