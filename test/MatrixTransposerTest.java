import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class MatrixTransposerTest {

    private MatrixTransposer matrixTransposer;
    private final String inputFileName = "inputFile.txt";
    private final String outputFileName = "outputFile.txt";

    @Before
    public void setUp() {
        matrixTransposer = new MatrixTransposer();
    }

    @Test
    public void transpose_success() throws IOException {
        FileWriter fileWriter = new FileWriter(inputFileName);
        fileWriter.write("1 2 3 \n4 5 6 \n7 8 9 \n");
        fileWriter.close();

        matrixTransposer.transpose(inputFileName, outputFileName);

        File file = new File(outputFileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder outputFileContent = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null) {
            outputFileContent.append(line);
            outputFileContent.append("\n");
            line = bufferedReader.readLine();
        }

        assertEquals("1 4 7 \n2 5 8 \n3 6 9 \n", outputFileContent.toString());
        bufferedReader.close();
    }

    @Test(expected = IOException.class)
    public void transpose_matrixValidation() throws IOException {
        FileWriter fileWriter = new FileWriter(inputFileName);
        fileWriter.write("1 2 3 \n4 5 \n");
        fileWriter.close();

        matrixTransposer.transpose(inputFileName, outputFileName);
    }

    @Test(expected = IOException.class)
    public void transpose_fileDoesNotExist() throws IOException {
        matrixTransposer.transpose(inputFileName, outputFileName);
    }
}