package encryptdecrypt;

import java.io.*;

public class FileAccess {

    public String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            line = br.readLine();

            while (line != null) {
                resultStringBuilder.append(line).append("\n");
                line = br.readLine();
            }
        }

        return resultStringBuilder.toString();
    }

    public String readFromFile(String filePath) throws IOException {
        String result = "";
        try (InputStream inputStream = new FileInputStream(filePath)) {
            result = this.readFromInputStream(inputStream);
        }

        return result;
    }

    public void writeToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }
}
