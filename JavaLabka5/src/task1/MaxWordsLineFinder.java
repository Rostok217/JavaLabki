package task1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class MaxWordsLineFinder {

    /** Результат пошуку: номер рядка, сам рядок і кількість слів. */
    public record Result(int lineNumber, String line, int wordCount) {
    }

    /**
     * Підраховує кількість слів у рядку.
     */
    public static int countWords(String line) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return 0;
        }
        return trimmed.split("\\s+").length;
    }

    /**
     * Читає файл і повертає рядок з найбільшою кількістю слів.
     */
    public Result find(String path) throws IOException {
        Result best = null;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            int number = 0;
            while ((line = reader.readLine()) != null) {
                number++;
                int count = countWords(line);
                if (best == null || count > best.wordCount()) {
                    best = new Result(number, line, count);
                }
            }
        }
        return best;
    }
}
