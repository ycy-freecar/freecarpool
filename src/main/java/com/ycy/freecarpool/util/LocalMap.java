package com.ycy.freecarpool.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程本地临时存储数据
 *
 * @author JiaYY
 * @create 2016/7/18 13:32
 */
public class LocalMap {

    private static final ThreadLocal<Map<String, Object>> CONTEXT = new ThreadLocal<>();

    private LocalMap() {

    }

    /**
     * 向当前线程暂存数据
     *
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        Map<String, Object> map = contextMap();
        if(map != null) {
            map.put(key, value);
        }
    }

    /**
     * 从当前线程获取暂存数据
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        String value = "";
        Map<String, Object> map = contextMap();
        if(map != null && map.get(key) != null) {
            value = String.valueOf(map.get(key));
        }
        return value;
    }

    /**
     * 资源释放
     */
    public static void destroy() {
        Map<String, Object> map = contextMap();
        if (map != null) {
            if (!map.isEmpty()) {
                map.clear();
            }
        }
        CONTEXT.remove();
    }

    /**
     * 获取当前线程里暂存的数据
     * @return
     */
    public static Map<String, Object> contextMap() {
        Map<String, Object> map = CONTEXT.get();
        if (map == null) {
            map = new HashMap<>();
            CONTEXT.set(map);
        }
        return map;
    }

}
