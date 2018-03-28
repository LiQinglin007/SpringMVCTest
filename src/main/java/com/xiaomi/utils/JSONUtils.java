package com.xiaomi.utils;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JSONUtils {
    /**
     * fastjson根据List获取到对应的JSONArray
     *
     * @param list
     * @return
     */
//    public static JSONArray getJSONArrayByList(List<?> list) {
//        JSONArray jsonArray = new JSONArray();
//        if (list == null || list.isEmpty()) {
//            return jsonArray;//nerver return null
//        }
//
//        for (Object object : list) {
//            jsonArray.add(object);
//        }
//        return jsonArray;
//    }

    /**
     * GSON根据List获取到对应的JSONArray
     *
     * @param list
     * @return
     */
    public static JsonArray getJSONArrayByList(List<?> list) {
        JsonArray jsonArray = new JsonArray();
        if (list == null || list.isEmpty()) {
            return jsonArray;//nerver return null
        }
        return new Gson().toJsonTree(list, new TypeToken<List<Object>>() {
        }.getType()).getAsJsonArray();
    }

}
