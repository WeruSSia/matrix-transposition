import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for transposing given matrix read from file and writing it to another file after validating its correctness.<br>
 * <br>
 * Input matrix in the input file must meet those requirements:<br>
 * - elements of the matrix must be separated by " ",<br>
 * - row sizes must be equal.
 */
@SuppressWarnings("WeakerAccess")
public class MatrixTransposer {
    private final List<String[]> inputMatrix = new ArrayList<>();
    private int firstArrayLength;

    private void readInputMatrixFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputMatrix.add(line.split(" "));
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    private void writeTransposedMatrixToFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        StringBuilder outputFileContent = new StringBuilder();
        for (int i = 0; i < firstArrayLength; i++) {
            for (String[] matrixRow : inputMatrix) {
                outputFileContent.append(matrixRow[i]);
                outputFileContent.append(" ");
            }
            outputFileContent.append("\n");
        }
        fileWriter.write(outputFileContent.toString());
        fileWriter.close();
    }

    private void validateMatrixSize() throws IOException {
        firstArrayLength = inputMatrix.get(0).length;
        for (String[] matrixRow : inputMatrix) {
            if (matrixRow.length != firstArrayLength) {
                throw new IOException("Row sizes are not equal");
            }
        }
    }

    /**
     * Reads input matrix from input file, then it checks if the matrix is correct (if row sizes are equal) and then writes transposed matrix to the output file.
     *
     * @param inputFileName  Name of an input file containing input matrix (to be transposed).
     * @param outputFileName Name of an output file that will store output matrix (transposed input matrix).
     * @throws IOException When something goes wrong with reading/writing to file or when input matrix is not correct (row sizes are not equal).
     */
    public void transpose(String inputFileName, String outputFileName) throws IOException {
        readInputMatrixFromFile(inputFileName);
        validateMatrixSize();
        writeTransposedMatrixToFile(outputFileName);
    }
}
