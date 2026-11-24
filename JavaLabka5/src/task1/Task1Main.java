package task1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Task1Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MaxWordsLineFinder finder = new MaxWordsLineFinder();

        while (true) {
            System.out.print("Введіть шлях до текстового файлу (або 0 для виходу): ");
            String path = scanner.nextLine().trim();

            if (path.equals("0")) {
                System.out.println("Програму завершено.");
                return;
            }

            try {
                if (path.isEmpty()) {
                    throw new IllegalArgumentException("шлях не може бути порожнім");
                }

                MaxWordsLineFinder.Result result = finder.find(path);

                if (result == null) {
                    System.out.println("Файл порожній.");
                } else {
                    System.out.println("Рядок з максимальною кількістю слів:");
                    System.out.println("  Номер рядка: " + result.lineNumber());
                    System.out.println("  Кількість слів: " + result.wordCount());
                    System.out.println("  Текст: " + result.line());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage());
            } catch (FileNotFoundException e) {
                System.out.println("Помилка: файл не знайдено — " + path);
            } catch (IOException e) {
                System.out.println("Помилка читання файлу: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
