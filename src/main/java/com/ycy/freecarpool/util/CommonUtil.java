package com.ycy.freecarpool.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 通用方法工具类
 *
 * @author JiaYY
 * @create 2016/5/16 15:49
 */
public class CommonUtil {

    /**
     * 比较两个版本:version1>=version2,返回true
     *
     * @param version1
     * @param version2
     * @return
     */
    public static boolean versionCompare(String version1, String version2) {
        if (version1 == null || version2 == null) {
            return false;
        }
        return version1.compareTo(version2) >= 0 ? true : false;
    }

    /**
     * 构造trackId
     *
     * @return
     */
    public static String getTrackId() {
        long times = DateUtil.getaLongTime();
        String random = getRandomNum(5);

        return times + random;
    }

    /**
     * 获取ip最后三位
     *
     * @param ip
     * @return
     */
    public static String getLastIpAddr(String ip) {
        String ipAddr = "";
        if (StringUtils.isNotBlank(ip)) {
            String[] ips = ip.split("\\.");
            ipAddr = ips.length > 3 ? ips[3] : "";
        }
        if (StringUtils.isBlank(ipAddr)) {
            ipAddr = getRandomNum(3);
        }
        if (StringUtils.isNotBlank(ipAddr)) {
            int ipLen = ipAddr.length();
            for (int i = 0; i < 3 - ipLen; i++) {
                ipAddr = "0" + ipAddr;
            }
        }
        return ipAddr;
    }

    /**
     * 获取指定位数随机整数
     *
     * @param len
     * @return
     */
    public static String getRandomNum(int len) {
        return String.valueOf((long) ((Math.random() * 9 + 1) * Math.pow(10, len - 1)));
    }

    /**
     * 获取过期周期
     *
     * @return
     */
    public static int getExpirePeriod() {
        return 30 * 60;
    }

    /**
     * 获取过期时间单位
     *
     * @param second
     * @return
     */
    public static String[] parseExpireTimeUnit(int second) {
        int min = (int) Math.ceil(second / 60);
        if (min < 60) {
            return new String[]{"00", String.valueOf(min)};
        }

        int hour = (int) Math.ceil(second / (60 * 60));
        if (hour < 24) {
            return new String[]{"01", String.valueOf(hour)};
        }

        int day = (int) Math.ceil(second / (60 * 60 * 24));
        if (day < 30) {
            return new String[]{"02", String.valueOf(day)};
        }

        int month = (int) Math.ceil(second / (60 * 60 * 24 * 30));
        if (month > 12) {
            month = 12;
        }

        return new String[]{"03", String.valueOf(month)};
    }

    /**
     * 获取用户性别
     *
     * @param sex
     * @return
     */
    public static int getUserGender(Integer sex) {
        if (sex == null) {
            return Constant.GENDER_ERROR;
        }
        switch (sex) {
            case Constant.SEX_MALE:
                return Constant.GENDER_MALE;
            case Constant.SEX_FEMALE:
                return Constant.GENDER_FEMALE;
            default:
                return Constant.GENDER_UNKNOWN;
        }
    }

    /**
     * 金额转换
     * 元转分、分转元、折扣转换等
     *
     * @param amount
     * @param num
     * @param isMultiply
     * @return
     */
    public static String changeMoney(BigDecimal amount, int num, boolean isMultiply) {
        if (isMultiply) {
            return amount.multiply(BigDecimal.valueOf(num)).toString();
        } else {
            return amount.divide(BigDecimal.valueOf(num)).toString();
        }
    }

    /**
     * 根据颜色名称获取颜色值
     *
     * @param colorName
     * @return
     */
    public static int getCouponColor(String colorName) {
        switch (colorName) {
            case "white":
                return Constant.COUPON_COLOR_WHITE;
            case "red":
                return Constant.COUPON_COLOR_RED;
            case "yellow":
                return Constant.COUPON_COLOR_YELLOW;
            case "blue":
                return Constant.COUPON_COLOR_BLUE;
            case "green":
                return Constant.COUPON_COLOR_GREEN;
            case "black":
                return Constant.COUPON_COLOR_BLACK;
            case "purple":
                return Constant.COUPON_COLOR_PURPLE;
            default:
                return Constant.COUPON_COLOR_RED;
        }
    }

    /**
     * 判断是否为空
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null) {
            return true;
        } else if (str.equals("null")) {
            return true;
        } else if (str.equals("")) {
            return true;
        }
        return false;
    }

}
