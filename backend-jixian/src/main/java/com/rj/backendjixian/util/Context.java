package com.rj.backendjixian.util;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private static final ThreadLocal<Map<String, Object>> contextThreadLocal = ThreadLocal.withInitial(HashMap::new);

    public static void put(String key, Object value) {
        getContextMap().put(key, value);
    }

    public static Object get(String key) {
        return getContextMap().get(key);
    }

    public static void remove(String key) {
        getContextMap().remove(key);
    }

    public static void clear() {
        contextThreadLocal.remove();
    }

    private static Map<String, Object> getContextMap() {
        return contextThreadLocal.get();
    }
}
