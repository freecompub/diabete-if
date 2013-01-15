package com.calcul.diabetif.commun.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil {

    /**
     * Always return a JSON array for the given key.
     * If the object contained at the given key is not a JSONArray, we will return a JSONArray
     * containing the object.
     */
    public static JSONArray getJSONArrayForKey(JSONObject json, String key) {
        JSONArray array = json.optJSONArray(key);
        if (array != null) {
            return array;
        }

        Object object = json.opt(key);
        if (object != null) {
            array = new JSONArray();
            array.put(object);
            return array;
        }

        return null;
    }
}
