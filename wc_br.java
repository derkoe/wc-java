import java.io.*;
import java.nio.file.*;

class wc_br {
    public static void main(String[] args) throws IOException {
        int lineCount = 0, wordCount = 0, charCount = 0, charVal;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(args[0]))))) {
            boolean prevWhitespace = true;
            while ((charVal = reader.read()) >= 0) {
                charCount++;
                if (charVal == '\n') {
                    lineCount++;
                    prevWhitespace = true;
                } else if (isWhitespace(charVal)) {
                    prevWhitespace = true;
                } else if (prevWhitespace) {
                    wordCount++;
                    prevWhitespace = false;
                }
            }
        }
        System.out.println(lineCount + " " + wordCount + " " + charCount + " " + args[0]);
    }

    private static boolean isWhitespace(int charVal) {
        return charVal == ' ' || charVal == '\t' || charVal == '\r' || charVal == '\f';
    }
}