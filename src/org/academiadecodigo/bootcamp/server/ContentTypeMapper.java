package org.academiadecodigo.bootcamp.server;

import java.util.HashMap;
import java.util.Map;

class ContentTypeMapper {

    private static final Map<String, String> map = createMap();

    private static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();

        map.put("jpg", "image/jpg");
        map.put("png", "image/png");
        map.put("jpeg", "image/jpeg");
        map.put("ico", "image/png");
        map.put("html", "text/html; charset=UTF-8");

        return map;
    }

    static String getContentType(String key) {
        return map.get(key);
    }
}
