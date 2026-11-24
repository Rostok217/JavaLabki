package controller;

import model.Book;
import util.BookFileManager;
import view.BookView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class BookController {
    private final BookView view;
    private final BookFileManager fileManager = new BookFileManager();
    private Book[] books;

    public BookController(BookView view) {
        this.view = view;
        this.books = initializeBooks();
    }

    private Book[] initializeBooks() {
        return new Book[]{
                new Book("Кобзар", "Тарас Шевченко", "Фоліо", 2020, 320, 250),
                new Book("Лісова пісня", "Леся Українка", "А-БА-БА-ГА-ЛА-МА-ГА", 2018, 180, 190),
                new Book("Захар Беркут", "Іван Франко", "Фоліо", 2022, 240, 220),
                new Book("Тигролови", "Іван Багряний", "КСД", 2019, 280, 270),
                new Book("Місто", "Валер'ян Підмогильний", "Фоліо", 2017, 350, 300),
                new Book("Тіні забутих предків", "Михайло Коцюбинський", "КСД", 2021, 160, 180),
                new Book("Маруся Чурай", "Ліна Костенко", "А-БА-БА-ГА-ЛА-МА-ГА", 2023, 220, 290),
                new Book("Чорна рада", "Пантелеймон Куліш", "Ранок", 2016, 300, 210),
                new Book("Кайдашева сім'я", "Іван Нечуй-Левицький", "Ранок", 2024, 200, 230),
                new Book("Intermezzo", "Михайло Коцюбинський", "КСД", 2022, 120, 150),
                new Book("Енеїда", "Іван Котляревський", "Фоліо", 2021, 260, 240),
                new Book("Собор", "Олесь Гончар", "Ранок", 2019, 400, 320)
        };
    }

    public void start() {
        view.showMessage("ПОЧАТКОВИЙ СПИСОК КНИГ:");
        view.showBooks(books);

        boolean running = true;

        while (running) {
            int choice = view.showMenu();

            switch (choice) {
                case 1 -> view.showBooks(books);
                case 2 -> searchByAuthor();
                case 3 -> searchByPublisher();
                case 4 -> searchByYear();
                case 5 -> sortByPublisher();
                case 6 -> saveToFile();
                case 7 -> loadFromFile();
                case 0 -> {
                    view.showMessage("Програму завершено.");
                    running = false;
                }
                default -> view.showMessage("Неправильний пункт меню!");
            }
        }
    }

    private void searchByAuthor() {
        String author = view.readString("Введіть автора: ");

        view.showMessage("\nПошук книг автора: " + author);

        Book[] result = Arrays.stream(books)
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .toArray(Book[]::new);

        view.showBooks(result);
    }

    private void searchByPublisher() {
        String publisher = view.readString("Введіть видавництво: ");

        view.showMessage("\nПошук книг видавництва: " + publisher);

        Book[] result = Arrays.stream(books)
                .filter(book -> book.getPublisher().equalsIgnoreCase(publisher))
                .toArray(Book[]::new);

        view.showBooks(result);
    }

    private void searchByYear() {
        int year;
        while (true) {
            year = view.readInt("Введіть рік: ");
            try {
                if (year < 0 || year > 2100) {
                    throw new IllegalArgumentException("рік має бути в межах 0..2100");
                }
                break;
            } catch (IllegalArgumentException e) {
                view.showMessage("Помилка: " + e.getMessage());
            }
        }

        final int searchYear = year;
        view.showMessage("\nКниги, видані пізніше " + searchYear + " року:");

        Book[] result = Arrays.stream(books)
                .filter(book -> book.getYear() > searchYear)
                .toArray(Book[]::new);

        view.showBooks(result);
    }

    private void sortByPublisher() {
        Book[] sortedBooks = books.clone();

        Arrays.sort(sortedBooks,
                Comparator.comparing(Book::getPublisher));

        view.showMessage("\nКниги, відсортовані за видавництвом:");
        view.showBooks(sortedBooks);
    }

    private void saveToFile() {
        String path = view.readString("Введіть шлях та ім'я файлу для збереження (напр. data/books.dat): ");
        try {
            fileManager.save(books, path);
            view.showMessage("Збережено " + books.length + " книг у файл: " + path);
        } catch (IOException e) {
            view.showMessage("Помилка збереження: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        String path = view.readString("Введіть шлях та ім'я файлу для читання: ");
        try {
            books = fileManager.load(path);
            view.showMessage("Завантажено " + books.length + " книг з файлу: " + path);
            view.showBooks(books);
        } catch (FileNotFoundException e) {
            view.showMessage("Помилка: файл не знайдено — " + path);
        } catch (IOException | ClassNotFoundException e) {
            view.showMessage("Помилка читання файлу: " + e.getMessage());
        }
    }
}
