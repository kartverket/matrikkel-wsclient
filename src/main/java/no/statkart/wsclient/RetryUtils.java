package no.statkart.wsclient;

import java.io.IOException;

public class RetryUtils {
    public interface ThrowingSupplier<T> {
        T get() throws IOException, InterruptedException;
    }

    public static <T> T retry(int n,
                              ThrowingSupplier<T> fn) throws IOException, InterruptedException {
        int attempt = 0;
        do {
            try {
                return fn.get();
            } catch (Exception e) {
                attempt++;
                if (attempt == n) throw e;
                var waitTime = (1 + (attempt * attempt)) * 1000;
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(ex);
                }
            }
        }
        while (true);
    }
}
