package com.calcul.diabetif.commun.cache;

import android.graphics.Bitmap;
import android.util.Log;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * A memory cache for storing Bitmap object.
 *
 * @author yannarak_w
 */
public class MemoryCache {

    private static final String TAG = MemoryCache.class.getSimpleName();

    /**
     * A Map object used to store Bitmap objects
     */
    private HashMap<String, SoftReference<Bitmap>> cache = new HashMap<String, SoftReference<Bitmap>>();

    /**
     * Gets a Bitmap object from the memory cache based on the given id.
     *
     * @param id The id of the Bitmap object in the cache.
     * @return a Bitmap object associated with the given id. If the id does not exists or the Bitmap
     * object has been recycled, null is returned.
     */
    public Bitmap get(String id) {
        Log.v(TAG, "get() id = " + id);
        if (!cache.containsKey(id)) return null;
        SoftReference<Bitmap> ref = cache.get(id);
        Bitmap bitmap = ref.get();
        if (bitmap != null && bitmap.isRecycled()) {
            bitmap = null;
        }
        return bitmap;
    }

    /**
     * Puts a Bitmap object to the cache and associates it with the given id.
     *
     * @param id     The id of the Bitmap object in the cache.
     * @param bitmap The Bitmap object to be cached.
     */
    public void put(String id, Bitmap bitmap) {
        Log.v(TAG, "put() id = " + id);
        cache.put(id, new SoftReference<Bitmap>(bitmap));
    }

    /**
     * Clears the cache.
     */
    public void clear() {
        Log.v(TAG, "clear()");
        cache.clear();
    }
}