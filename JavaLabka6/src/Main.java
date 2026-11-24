import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Translator translator = new Translator();
        fillDefaultDictionary(translator);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("===== Перекладач EN -> UA =====");
            System.out.println("1 - Додати пару слів");
            System.out.println("2 - Перекласти фразу");
            System.out.println("3 - Показати словник");
            System.out.println("0 - Вихід");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addWords(scanner, translator);
                case "2" -> translatePhrase(scanner, translator);
                case "3" -> {
                    System.out.println("Словник (" + translator.size() + " слів):");
                    translator.printDictionary();
                }
                case "0" -> {
                    System.out.println("До побачення!");
                    return;
                }
                default -> System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }

    private static void fillDefaultDictionary(Translator t) {
        t.addWord("i", "я");
        t.addWord("you", "ти");
        t.addWord("we", "ми");
        t.addWord("love", "люблю");
        t.addWord("like", "подобається");
        t.addWord("java", "джава");
        t.addWord("programming", "програмування");
        t.addWord("cat", "кіт");
        t.addWord("dog", "собака");
        t.addWord("is", "є");
        t.addWord("big", "великий");
        t.addWord("small", "маленький");
        t.addWord("hello", "привіт");
        t.addWord("world", "світ");
        t.addWord("good", "добрий");
        t.addWord("day", "день");
    }

    private static void addWords(Scanner scanner, Translator translator) {
        System.out.println("Введіть пари у форматі: english=українська");
        System.out.println("Порожній рядок - завершити введення.");
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            String[] parts = line.split("=", 2);
            if (parts.length != 2) {
                System.out.println("Невірний формат. Приклад: apple=яблуко");
                continue;
            }
            try {
                translator.addWord(parts[0], parts[1]);
                System.out.println("Додано: " + parts[0].trim() + " - " + parts[1].trim());
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }

    private static void translatePhrase(Scanner scanner, Translator translator) {
        System.out.print("Введіть фразу англійською: ");
        String phrase = scanner.nextLine();
        System.out.println("Переклад: " + translator.translate(phrase));
    }
}