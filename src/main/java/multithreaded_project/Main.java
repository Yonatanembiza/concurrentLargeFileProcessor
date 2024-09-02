package multithreaded_project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // Define the files to process
        String[] files = {
                "src/main/resources/input/file1.txt",
                "src/main/resources/input/file2.txt",
                "src/main/resources/input/file3.txt"
        };
    }
}