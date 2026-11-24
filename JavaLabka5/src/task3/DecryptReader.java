package task3;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Фільтруючий потік читання: дешифрує кожен прочитаний символ.
 */
public class DecryptReader extends FilterReader {
    private final char key;

    public DecryptReader(Reader in, char key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return (c == -1) ? -1 : Cipher.decrypt((char) c, key);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int n = super.read(cbuf, off, len);
        for (int i = off; i < off + Math.max(n, 0); i++) {
            cbuf[i] = Cipher.decrypt(cbuf[i], key);
        }
        return n;
    }
}
