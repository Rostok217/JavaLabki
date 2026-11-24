import java.util.HashMap;
import java.util.Map;

/**
 * Перекладач з англійської мови на українську.
 * Зберігає словник у вигляді колекції HashMap<String, String>.
 */
public class Translator {

    // ключ - англійське слово, значення - український переклад
    private final Map<String, String> dictionary = new HashMap<>();

    /** Додає пару слів до словника. */
    public void addWord(String english, String ukrainian) {
        if (english == null || ukrainian == null
                || english.isBlank() || ukrainian.isBlank()) {
            throw new IllegalArgumentException("Слова не можуть бути порожніми");
        }
        dictionary.put(english.trim().toLowerCase(), ukrainian.trim().toLowerCase());
    }

    /**
     * Перекладає фразу пословно.
     * Якщо слова немає у словнику, воно лишається без змін у квадратних дужках.
     */
    public String translate(String phrase) {
        if (phrase == null || phrase.isBlank()) {
            return "";
        }
        String[] words = phrase.trim().toLowerCase().split("[^a-z']+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            String translation = dictionary.get(word);
            if (result.length() > 0) {
                result.append(' ');
            }
            result.append(translation != null ? translation : "[" + word + "]");
        }
        return result.toString();
    }

    public int size() {
        return dictionary.size();
    }

    public void printDictionary() {
        dictionary.forEach((en, ua) -> System.out.println("  " + en + " - " + ua));
    }
}