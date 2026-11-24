package util;

import model.Book;

import java.io.*;

public class BookFileManager {

    public void save(Book[] books, String path) throws IOException {
        File file = new File(path);
        File parent = file.getAbsoluteFile().getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IOException("Не вдалося створити каталог: " + parent);
        }

        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)))) {
            out.writeObject(books);
        }
    }

    /**
     * Читає масив книг із файлу.
     */
    public Book[] load(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(path)))) {
            Object obj = in.readObject();
            if (!(obj instanceof Book[] books)) {
                throw new IOException("Файл не містить набору книг.");
            }
            return books;
        }
    }
}
