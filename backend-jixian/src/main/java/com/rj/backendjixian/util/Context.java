package com.rj.backendjixian.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Context {
    private static final ThreadLocal<Map<String, Object>> contextThreadLocal = ThreadLocal.withInitial(HashMap::new);

    public static void put(String key, Object value) {
        getContextMap().put(key, value);
    }

    public static Object get(String key) {
        return getContextMap().get(key);
    }
    public static <T> T get(String key, Class<T> type) {
        Object value = getContextMap().get(key);
        if (value != null) {
            try {
                return type.cast(value); // 尝试将对象转换为指定类型
            } catch (ClassCastException e) {
                // 处理类型不匹配的情况
                throw new ClassCastException("Value for key '" + key + "' is not of type " + type.getSimpleName());
            }
        }
        return null; // 如果没有找到对应的值，返回null或者可以根据需求进行其他处理
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
