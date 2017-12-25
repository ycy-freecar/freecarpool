package com.ycy.freecarpool.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 日志记录工具类
 *
 * @author JiaYY
 * @create 2016/7/14 19:53
 */
public class LogUtil {

    private static final Logger log = LoggerFactory.getLogger(LogUtil.class);

    private static final String LOG_ID = "LOG_ID";
    private static final String LOG_SID = "LOG_SID";

    /**
     * 记录请求日志
     *
     * @param request
     */
    public static void logRequest(HttpServletRequest request) {
        String logId = CommonUtil.getRandomNum(12);

        String logSid = getRequestLogSid(request, logId);

        String paramStr = getParamStr(request);

        logParam(logSid, paramStr);

        LocalMap.set(LOG_ID, logId);
        LocalMap.set(LOG_SID, logSid);
    }

    /**
     * 记录请求参数
     *
     * @param logSid
     * @param param
     * @return
     */
    private static void logParam(String logSid, String param) {
        log.info("{{}, \"param\": {}}", logSid, param);
    }

    /**
     * 记录请求结果
     *
     * @param param
     * @param result
     * @return
     */
    public static String logResult(Object param, Object result) {
        String logSid = getLogSid();
        return String.format("{%s, \"param\": %s, \"result\": %s}", logSid, JsonConverUtil.Bean2Json(param), JsonConverUtil.Bean2Json(result));
    }

    /**
     * 记录错误信息-默认异常
     *
     * @return
     */
    public static String logError() {
        String logId = getLogId();
        return String.format("{\"logId\": \"%s\", \"errmsg\": \"system error\"}", logId);
    }

    /**
     * 记录错误信息-自定义异常
     *
     * @param errmsg
     * @return
     */
    public static String logError(String errmsg) {
        String logId = getLogId();
        return String.format("{\"logId\": \"%s\", \"errmsg\": \"%s\"}", logId, errmsg);
    }

    /**
     * 记录信息日志-自定义异常
     *
     * @param msg
     * @return
     */
    public static String logInfo(String msg) {
        String logId = getLogId();
        return String.format("{\"logId\": \"%s\", \"msg\": \"%s\"}", logId, msg);
    }

    /**
     * 记录调用外部服务的日志
     *
     * @param errmsg
     * @param url
     * @param param
     * @return
     */
    public static String logOuterError(String errmsg, String url, Object param) {
        String logId = getLogId();
        return String.format("{\"logId\": \"%s\", \"errmsg\": \"%s\", \"service\": {\"service_url\": \"%s\", \"service_param\": %s}}", logId, errmsg, url, JsonConverUtil.Bean2Json(param));
    }

    /**
     * 记录调用外部服务的日志
     *
     * @param url
     * @param param
     * @param result
     * @return
     */
    public static String logOuterResult(String url, Object param, Object result) {
        String logId = getLogId();
        return String.format("{\"logId\": \"%s\", \"service\": {\"service_url\": \"%s\", \"service_param\": %s, \"service_result\": %s}}", logId, url, JsonConverUtil.Bean2Json(param), result);
    }

    /**
     * 获取请求日志id
     * @return
     */
    private static String getLogId() {
        return LocalMap.get(LOG_ID);
    }

    /**
     * 获取请求日志sid
     * @return
     */
    private static String getLogSid() {
        return LocalMap.get(LOG_SID);
    }

    /**
     * 构造请求日志序列号
     *
     * @param request
     * @return
     */
    private static String getRequestLogSid(HttpServletRequest request, String logId) {
        String requestIp = HttpRequestUtil.getRemoteHost(request);
        String requestRri = request.getRequestURI();
        String uriPrefix = request.getContextPath();
        String operaCode = StringUtils.substringAfter(requestRri, uriPrefix);

        String logSid = String.format("\"url\": \"%s\", \"ip\": \"%s\", \"logId\": \"%s\"", operaCode, requestIp, logId);

        return logSid;
    }

    /**
     * 获取请求参数字符串
     *
     * @param request
     * @return
     */
    private static String getParamStr(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();

        StringBuffer sb = new StringBuffer();
        if(map != null) {
            String[] value = null;
            sb.append("{");
            for(Map.Entry<String, String[]> entry : map.entrySet()) {
                sb.append("\"").append(entry.getKey()).append("\"").append(":").append("\"");
                value = entry.getValue();
                if(value.length > 1) {
                    for(String s : value) {
                        sb.append(s).append(",");
                    }
                } else {
                    sb.append(value[0]);
                }
                sb.append("\",");
            }
            if(sb.length() > 1) {
                sb.deleteCharAt(sb.length() - 1);
                sb.append("}");
            }
        }

        return sb.toString();
    }


}
