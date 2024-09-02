package multithreaded_project;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriterTask implements Runnable {
    private final String outputFilePath;
    private final String content;

    public FileWriterTask(String outputFilePath, String content) {
        this.outputFilePath = outputFilePath;
        this.content = content;
    }

    @Override
    public void run() {
        Path path = Paths.get(outputFilePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + outputFilePath);
            e.printStackTrace();
        }
    }
}
