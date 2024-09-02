package multithreaded_project;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FileProcessorTest {
    @Test
    public void testFileReaderTask() {
        String testFilePath = "src/main/resources/input/testfile.txt";
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        FileReaderTask readerTask = new FileReaderTask(testFilePath, executorService);

        assertDoesNotThrow(() -> executorService.submit(readerTask));
    }

    @Test
    public void testFileWriterTask() {
        String outputFilePath = "src/main/resources/output/testfile_out.txt";
        String content = "This is a test line.";
        FileWriterTask writerTask = new FileWriterTask(outputFilePath, content);

        assertDoesNotThrow(() -> new Thread(writerTask).start());
    }
}
