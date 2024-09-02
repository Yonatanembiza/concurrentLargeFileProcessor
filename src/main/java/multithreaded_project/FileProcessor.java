package multithreaded_project;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileProcessor {
    private final ExecutorService executorService;
    private final List<String> filesToProcess;

    // Constructor to initialize the FileProcessor with the required parameters
    public FileProcessor(int numberOfThreads, List<String> filesToProcess) {
        this.executorService = Executors.newFixedThreadPool(numberOfThreads);
        this.filesToProcess = filesToProcess;
    }

    // Method to start processing files
    public void startProcessing() {
        for (String filePath : filesToProcess) {
            FileReaderTask readerTask = new FileReaderTask(filePath, executorService);
            executorService.submit(readerTask);
        }

        // Initiating shutdown of the executor service
        executorService.shutdown();

        // Waiting for all tasks to complete
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    // Method to process a single file immediately (for synchronous operations or testing)
    public void processSingleFile(String filePath) {
        FileReaderTask readerTask = new FileReaderTask(filePath, executorService);
        executorService.submit(readerTask);
    }

    // Method to clean up resources, useful if the object is being destroyed or reused
    public void cleanUp() {
        if (!executorService.isShutdown()) {
            executorService.shutdownNow();
        }
    }
}
