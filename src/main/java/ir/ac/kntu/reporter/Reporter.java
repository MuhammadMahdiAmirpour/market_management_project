package ir.ac.kntu.reporter;

import java.io.*;

public class Reporter {
    public void report(String report) {
        File file = new File("F:\\university\\AdvancedProgramming\\HomeWorks\\project2\\src\\main\\reports" +
                "\\report.txt");
        try (FileWriter fileWriter = new FileWriter(file, true)){
            fileWriter.write(report);
        } catch (Exception e) {
            System.out.println("You have got some error while writing yor report");
            e.printStackTrace();
        }
    }
}
