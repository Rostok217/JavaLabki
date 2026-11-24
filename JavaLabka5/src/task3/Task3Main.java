package task3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Task3Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введіть текст для шифрування: ");
            String text = scanner.nextLine();

            System.out.print("Введіть ключовий символ: ");
            String keyLine = scanner.nextLine();
            if (keyLine.length() != 1) {
                throw new IllegalArgumentException("ключ має бути рівно одним символом");
            }
            char key = keyLine.charAt(0);

            System.out.print("Введіть шлях та ім'я файлу для зашифрованих даних: ");
            String path = scanner.nextLine().trim();
            if (path.isEmpty()) {
                throw new IllegalArgumentException("шлях не може бути порожнім");
            }

            // 1. Запис із шифруванням через FilterWriter
            try (Writer writer = new EncryptWriter(
                    new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8), key)) {
                writer.write(text);
            }
            System.out.println("Текст зашифровано і записано у файл: " + path);

            // 2. Вміст файлу «як є» (зашифрований)
            StringBuilder encrypted = new StringBuilder();
            try (Reader reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)) {
                int c;
                while ((c = reader.read()) != -1) {
                    encrypted.append((char) c);
                }
            }
            System.out.println("Зашифрований вміст файлу: " + encrypted);

            // 3. Читання з дешифруванням через FilterReader
            StringBuilder decrypted = new StringBuilder();
            try (Reader reader = new DecryptReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8), key)) {
                char[] buffer = new char[64];
                int n;
                while ((n = reader.read(buffer, 0, buffer.length)) != -1) {
                    decrypted.append(buffer, 0, n);
                }
            }
            System.out.println("Розшифрований текст: " + decrypted);

            // 4. Перевірка методів encrypt/decrypt
            System.out.println("Перевірка: " +
                    (Cipher.decrypt(Cipher.encrypt(text, key), key).equals(text)
                            ? "текст відновлено правильно" : "помилка!"));

        } catch (IllegalArgumentException e) {
            System.out.println("Помилка введення: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
        }
    }
}
