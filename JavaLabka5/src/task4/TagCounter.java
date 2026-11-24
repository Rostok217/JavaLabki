package task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagCounter {
    private static final Pattern TAG_PATTERN = Pattern.compile("<([a-zA-Z][a-zA-Z0-9]*)");

    /** Завантажує HTML-код сторінки за URL. */
    public String download(String address) throws IOException {
        URL url = URI.create(address).toURL();
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setConnectTimeout(10_000);
        connection.setReadTimeout(10_000);

        if (connection instanceof HttpURLConnection http) {
            http.setInstanceFollowRedirects(true);
            int code = http.getResponseCode();
            if (code >= 400) {
                throw new IOException("сервер повернув код " + code);
            }
        }

        StringBuilder html = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                html.append(line).append('\n');
            }
        }
        return html.toString();
    }

    /** Підраховує кількість кожного тегу в HTML-коді. */
    public Map<String, Integer> count(String html) {
        Map<String, Integer> result = new HashMap<>();
        // прибираємо HTML-коментарі, щоб не рахувати теги всередині них
        String cleaned = html.replaceAll("(?s)<!--.*?-->", "");
        Matcher matcher = TAG_PATTERN.matcher(cleaned);
        while (matcher.find()) {
            String tag = matcher.group(1).toLowerCase();
            result.merge(tag, 1, Integer::sum);
        }
        return result;
    }

    /** а) Теги в лексикографічному порядку. */
    public List<Map.Entry<String, Integer>> sortByName(Map<String, Integer> counts) {
        return new ArrayList<>(new TreeMap<>(counts).entrySet());
    }

    /** б) Теги в порядку зростання частоти. */
    public List<Map.Entry<String, Integer>> sortByFrequency(Map<String, Integer> counts) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(counts.entrySet());
        list.sort(Map.Entry.<String, Integer>comparingByValue()
                .thenComparing(Map.Entry.comparingByKey()));
        return list;
    }
}
