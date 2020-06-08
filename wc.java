import java.io.*;
import java.nio.file.*;

class wc {
    public static void main(String[] args) throws IOException {
        int lineCount = 0, wordCount = 0, charCount = 0, count;
        char[] cbuf = new char[16 * 1024];
        InputStream is = args.length == 0 ? System.in : Files.newInputStream(Paths.get(args[0]));
        try (Reader reader = new InputStreamReader(is)) {
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

        String fileName = args.length > 0 ? " " + args[0] : "";
        System.out.printf("%7d %7d %7d %s\n", lineCount, wordCount, charCount, fileName);
    }

    private static boolean isWhitespace(int charVal) {
        return charVal == ' ' || charVal == '\t' || charVal == '\r' || charVal == '\f';
    }
}