package com.calcul.diabetif.commun.util;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A class containing utility methods for I/O.
 *
 * @author yannarak_w
 */
public class IOUtil {

    private static final String TAG = IOUtil.class.getSimpleName();

    /**
     * Copy data from a given input stream to a given output stream
     *
     * @param is The input stream from the data is read.
     * @param os The output stream to which the data is written.
     */
    public static void copyStream(InputStream is, OutputStream os) {
        Log.v(TAG, "copyStream()");
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count;

                count = is.read(bytes, 0, buffer_size);

                if (count == -1) break;
                os.write(bytes, 0, count);
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}