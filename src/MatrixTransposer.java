import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MatrixTransposer {
    private List<String[]> inputMatrix = new ArrayList<>();
    private int firstArrayLength;

    private void readInputMatrixFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputMatrix.add(line.split(" "));
            line = bufferedReader.readLine();
        }
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

    public void transpose(String inputFileName, String outputFileName) throws IOException {
        readInputMatrixFromFile(inputFileName);
        validateMatrixSize();
        writeTransposedMatrixToFile(outputFileName);
    }
}
