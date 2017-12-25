package com.ycy.freecarpool.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * Map对象处理类
 *
 * @author Liull
 * @date 2016/10/26 17:35
 */
public class MapUtil {

    /**
     * Map对象转化成 JavaBean对象
     *
     * @param javaBean JavaBean实例对象
     * @param map Map对象
     * @return
     */
    public static <T> T map2Bean(T javaBean, Map map) {
        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
            // 创建 JavaBean 对象
            Object obj = javaBean.getClass().newInstance();

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                String propertyName = null; // javaBean属性名
                Object propertyValue = null; // javaBean属性值
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (map.containsKey(propertyName)) {
                        propertyValue = map.get(propertyName);
                        pd.getWriteMethod().invoke(obj, new Object[] { propertyValue });
                    }
                }
                return (T) obj;
            }
        } catch (Exception e) {
        }

        return null;
    }
}


