package multithreaded_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class FileReaderTask implements Runnable {
    private final String filePath;
    private final ExecutorService executorService;

    public FileReaderTask(String filePath, ExecutorService executorService) {
        this.filePath = filePath;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line (for example, count words)
                String processedLine = processLine(line);

                // Submit a FileWriterTask for each processed line
                FileWriterTask writerTask = new FileWriterTask(filePath + ".out", processedLine);
                executorService.submit(writerTask);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
    }

    private String processLine(String line) {
        // Implement processing logic here, e.g., convert to uppercase
        return line.toUpperCase();  // Simple example processing
    }
}
