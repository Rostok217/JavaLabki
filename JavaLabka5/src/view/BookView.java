package view;

import model.Book;

import java.util.Scanner;

public class BookView {
    private final Scanner scanner = new Scanner(System.in);

    public void showBooks(Book[] books) {
        if (books.length == 0) {
            System.out.println("Книг не знайдено.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public int showMenu() {
        System.out.println("\n===== БІБЛІОТЕКА =====");
        System.out.println("1. Показати всі книги");
        System.out.println("2. Пошук за автором");
        System.out.println("3. Пошук за видавництвом");
        System.out.println("4. Книги після заданого року");
        System.out.println("5. Сортувати за видавництвом");
        System.out.println("6. Зберегти книги у файл");
        System.out.println("7. Завантажити книги з файлу");
        System.out.println("0. Вихід");

        return readInt("Ваш вибір: ");
    }

    /**
     * Зчитує ціле число з клавіатури. Повторює запит,
     * поки користувач не введе коректне значення.
     */
    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Помилка: потрібно ввести ціле число!");
            }
        }
    }

    /**
     * Зчитує непорожній рядок з клавіатури.
     */
    public String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                if (line.isEmpty()) {
                    throw new IllegalArgumentException("Рядок не може бути порожнім!");
                }
                return line;
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }
}
