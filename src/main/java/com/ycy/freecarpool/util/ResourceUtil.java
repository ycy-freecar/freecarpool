package com.ycy.freecarpool.util;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.util.Assert;

import java.util.ResourceBundle;

/**
 * 获取相关配置信息的值
 */
public class ResourceUtil {
    private static final String SEPARATOR       = ",";

    public static String get(String key,  ResourceBundle... resourceBundles) {
        String value = null;
        for (ResourceBundle resourceBundle: resourceBundles) {
            if (resourceBundle.containsKey(key)) {
                value = resourceBundle.getString(key);
                break;
            }
        }

        Assert.notNull(value, "必要参数［" + key + "]没有配置");
        return value;
    }

    public static String[] getArray(String key, ResourceBundle... resourceBundles) {
        return get(key, resourceBundles).split(SEPARATOR);
    }

    public static boolean getBoolean(String key, ResourceBundle... resourceBundles) {
        return BooleanUtils.toBoolean(get(key, resourceBundles));
    }

    public static int getInt(String key, ResourceBundle...resourceBundles){
        return NumberUtils.toInt(get(key, resourceBundles));
    }

}
