package task4;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Task4Main {
    private static final String DEFAULT_URL = "https://uk.wikipedia.org/wiki/Java";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TagCounter counter = new TagCounter();

        System.out.print("Введіть URL сторінки (Enter — " + DEFAULT_URL + "): ");
        String url = scanner.nextLine().trim();
        if (url.isEmpty()) {
            url = DEFAULT_URL;
        }

        try {
            if (!url.matches("(?i)^(https?|file):.*")) {
                throw new IllegalArgumentException("URL має починатися з http:// або https://");
            }

            System.out.println("Завантаження сторінки " + url + " ...");
            String html = counter.download(url);
            Map<String, Integer> counts = counter.count(html);

            if (counts.isEmpty()) {
                System.out.println("Тегів не знайдено.");
                return;
            }

            System.out.println("\nа) Теги в лексикографічному порядку:");
            print(counter.sortByName(counts));

            System.out.println("\nб) Теги в порядку зростання частоти:");
            print(counter.sortByFrequency(counts));

            System.out.println("\nУсього різних тегів: " + counts.size());
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: некоректний URL — " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка завантаження сторінки: " + e.getMessage());
        }
    }

    private static void print(List<Map.Entry<String, Integer>> entries) {
        for (Map.Entry<String, Integer> e : entries) {
            System.out.printf("  %-12s %d%n", e.getKey(), e.getValue());
        }
    }
}
