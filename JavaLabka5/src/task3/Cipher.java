package task3;


public class Cipher {

    /** Шифрування одного символу. */
    public static char encrypt(char c, char key) {
        return (char) (c + key);
    }

    /** Дешифрування одного символу. */
    public static char decrypt(char c, char key) {
        return (char) (c - key);
    }

    /** Шифрування рядка. */
    public static String encrypt(String text, char key) {
        StringBuilder sb = new StringBuilder(text.length());
        for (char c : text.toCharArray()) {
            sb.append(encrypt(c, key));
        }
        return sb.toString();
    }

    /** Дешифрування рядка. */
    public static String decrypt(String text, char key) {
        StringBuilder sb = new StringBuilder(text.length());
        for (char c : text.toCharArray()) {
            sb.append(decrypt(c, key));
        }
        return sb.toString();
    }
}
