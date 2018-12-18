package bioinfa.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CustomFileReader {
    private String sequence1;
    private String sequence2;
    private List<String> sequences;
    private List<List<Integer>> costList;

    public CustomFileReader(String path) {
        costList = new ArrayList<>();
        sequences = new ArrayList<>();
        readDataFromFile(path);
    }

    private void readDataFromFile(String path) {
        try {
            Scanner input = new Scanner(new File(path));
            if(input.hasNextLine()){
                sequence1 = input.nextLine();
            }
            if(input.hasNextLine()){
                sequence2 = input.nextLine();
            }
            if(input.hasNextLine()){
                String[] s = input.nextLine().split(" ");
                sequences = Arrays.asList(s);
            }
            while (input.hasNextLine()) {
                Scanner colReader = new Scanner(input.nextLine());
                List<Integer> col = new ArrayList<>();
                while (colReader.hasNextInt()) {
                    col.add(colReader.nextInt());
                }
                if(!col.isEmpty())
                costList.add(col);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getSequence1() {
        return sequence1;
    }

    public String getSequence2() {
        return sequence2;
    }

    public List<String> getSequences() {
        return sequences;
    }

    public List<List<Integer>> getCostList() {
        return costList;
    }
}
