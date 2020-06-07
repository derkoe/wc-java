import java.io.*;
import java.nio.file.*;

class wc {
    public static void main(String[] args) throws IOException {
        int lineCount = 0, wordCount = 0, charCount = 0, count;
        char[] cbuf = new char[16 * 1024];
        try (Reader reader = new InputStreamReader(Files.newInputStream(Paths.get(args[0])))) {
            boolean prevWhitespace = true;
            while ((count = reader.read(cbuf)) >= 0) {
                for (int i = 0; i < count; i++) {
                    char charVal = cbuf[i];
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
        }
        System.out.println(lineCount + " " + wordCount + " " + charCount + " " + args[0]);
    }

    private static boolean isWhitespace(int charVal) {
        return charVal == ' ' || charVal == '\t' || charVal == '\r' || charVal == '\f';
    }
}