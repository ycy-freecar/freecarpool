package com.ycy.freecarpool.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static CloseableHttpClient httpClient;

    private static RequestConfig defaultRequestConfig;

    /**
     * 最大连接数
     */
    private final static int MAX_TOTAL_CONNECTIONS = 200;

    /**
     * 从连接管理器中获取连接的最大等待时间
     * timeout in milliseconds used when requesting a connection from the connection manager.
     */
    private final static int WAIT_TIMEOUT = 100;

    /**
     * 每个路由最大连接数
     */
    private final static int MAX_ROUTE_CONNECTIONS = 200;

    /**
     * 连接超时时间
     * Determines the timeout in milliseconds until a connection is established.
     */
    private final static int CONNECT_TIMEOUT = 4000;

    /**
     * 读取超时时间
     * Defines the socket timeout in milliseconds,
     * which is the timeout for waiting for data  or, put differently,
     * a maximum period inactivity between two consecutive data packets).
     */
    private final static int READ_TIMEOUT = 10000;

    /**
     * UTF-8编码
     */
    private final static String ENCODING_UTF_8 = "UTF-8";

    public static boolean ubqsOverLoad = false;


    static {
        // SSL context for secure connections can be created either based on
        // system or application specific properties.
        SSLContext sslcontext = SSLContexts.createSystemDefault();
        // Create a registry of custom connection socket factories for supported
        // protocol schemes.
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
        // Create global request configuration
        defaultRequestConfig = RequestConfig.custom().setConnectionRequestTimeout(WAIT_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(READ_TIMEOUT).build();
        // Create an HttpClient with the given custom dependencies and configuration.
        httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(defaultRequestConfig).build();
    }

    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public static String Post(String url, HashMap<String, String> requestMap) throws Exception {
        return Post(url, requestMap, HTTP.UTF_8);
    }
    public static void main(String[] args) {
        HashMap<String, String> requestMap = new HashMap<>();
        requestMap.put("grant_type","client_credential");
        requestMap.put("appid","wx15ae2897cb8f4a01");
        requestMap.put("secret","825adaabc2b2c4b2b7abb3bf6c44ed51");
        try {
            System.out.println(HttpClientUtil.Get("https://api.weixin.qq.com/cgi-bin/token",requestMap));
        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }
    }
    public static String Get(String url, HashMap<String, String> requestMap) throws Exception {
        return Get(url, requestMap, HTTP.UTF_8);
    }

    /**
     * post获取数据
     *
     * @param url        接口URL
     * @param requestMap NameValuePair参数
     * @param coding     编码
     * @return
     * @throws Exception
     */
    public static String Post(String url, Map<String, String> requestMap, String coding) throws Exception {
        Long startTimeMills = System.currentTimeMillis();
        String returnMsg = "";
        String outStr = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<Entry<String, String>> entrys = requestMap.entrySet();
            for (Entry<String, String> entry : entrys) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, coding));
            } catch (UnsupportedEncodingException e) {
                logger.error("httpClient setEntity error! e={}", e);
            }
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.addHeader("Accept-Language", "zh-cn");
            CloseableHttpResponse httpResponse = null;
            try {
                try {
                    httpResponse = httpClient.execute(httpPost, HttpClientContext.create());
                } catch (IOException e) {
                    logger.error("httpClient execute error! e={}", e);
                    throw new Exception();
                }
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    try {
                        returnMsg = EntityUtils.toString(httpEntity, coding == null ? "UTF-8" : coding);

                        solaMap(requestMap);
                        outStr = handlerResult(returnMsg);


                    } catch (IOException e) {
                        logger.error("httpClient execute error! e={}", e);
                        throw new Exception();
                    }
                    try {
                        EntityUtils.consume(httpEntity);
                    } catch (IOException e) {
                        logger.error("httpClient execute error! e={}", e);
                        throw new Exception();
                    }
                }
                httpPost.abort();
            } finally {
                if (httpResponse != null) {
                    try {
                        httpResponse.close();
                    } catch (IOException e) {
                        logger.error("httpClient close error! e=", e);
//                        throw new MercoreException(PaygentErrorCode.HTTP_CONN_FAILURE, "http连接失败!");
                    }
                }
            }
        } finally {
           
            Long endTimeMills = System.currentTimeMillis();
            Long dotimes = endTimeMills - startTimeMills;
            if (dotimes > 4000) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(dotimes)
                        .append(" Mills! httpclient call times more than 4000Millis, url = ")
                        .append(url)
                ;
                logger.warn(buffer.toString());
            }
        }
        return returnMsg;
    }

    public static String Post(String url, String param, String coding) throws Exception {
        Long startTimeMills = System.currentTimeMillis();
        String returnMsg = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            try {
                httpPost.setEntity(new StringEntity(param, coding));
            } catch (Exception e) {
                logger.error("httpClient setEntity error! e={}", e);
            }
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.addHeader("Accept-Language", "zh-cn");
            CloseableHttpResponse httpResponse = null;
            try {
                try {
                    httpResponse = httpClient.execute(httpPost, HttpClientContext.create());
                } catch (IOException e) {
                    logger.error("httpClient execute error! e={}", e);
                    throw new Exception();
                }
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    try {
                        returnMsg = EntityUtils.toString(httpEntity, coding == null ? "UTF-8" : coding);
                    } catch (IOException e) {
                        logger.error("httpClient execute error! e={}", e);
                        throw new Exception();
                    }
                    try {
                        EntityUtils.consume(httpEntity);
                    } catch (IOException e) {
                        logger.error("httpClient execute error! e={}", e);
                        throw new Exception();
                    }
                }
                httpPost.abort();
            } finally {
                if (httpResponse != null) {
                    try {
                        httpResponse.close();
                    } catch (IOException e) {
                        logger.error("httpClient close error! e=", e);
//                        throw new MercoreException(PaygentErrorCode.HTTP_CONN_FAILURE, "http连接失败!");
                    }
                }
            }
        } finally {

            Long endTimeMills = System.currentTimeMillis();
            Long dotimes = endTimeMills - startTimeMills;
            if (dotimes > 4000) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(dotimes)
                        .append(" Mills! httpclient call times more than 4000Millis, url = ")
                        .append(url)
                ;
                logger.warn(buffer.toString());
            }
        }
        return returnMsg;
    }

    public static String Get(String url, HashMap<String, String> requestMap, String coding) throws Exception {
        Long startTimeMills = System.currentTimeMillis();
        String realUrl = url;
        String returnMsg = "";
        String outStr = null;
        try {
            Set<Entry<String, String>> entrys = requestMap.entrySet();
            String params = "";
            boolean first = true;
            for (Entry<String, String> entry : entrys) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value == null) {
                    value = "";
                }
                if (first) {
                    params = params + key + "=" + value;
                    first = false;
                } else {
                    params = params + "&" + key + "=" + value;
                }
            }

            try {
                params = new String(params.getBytes(coding));
            } catch (UnsupportedEncodingException e1) {
                logger.info("http get exception ocus {}", e1);
            }
            url = url + "?" + params;
            RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
                    .setSocketTimeout(READ_TIMEOUT * 3)
                    .setConnectTimeout(CONNECT_TIMEOUT * 2)
                    .setConnectionRequestTimeout(WAIT_TIMEOUT)
                    .build();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse httpResponse = null;
            try {
                httpResponse = httpClient.execute(httpGet, HttpClientContext.create());
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    returnMsg = EntityUtils.toString(httpEntity, coding == null ? "UTF-8" : coding);

                    solaMap(requestMap);
                    outStr = handlerResult(returnMsg);


                    EntityUtils.consume(httpEntity);
                }
                httpGet.abort();
            } catch (SocketTimeoutException e) {
                logger.error("查询信息超时,e={}", e);
                throw new Exception();
            } catch (ClientProtocolException e) {
            } catch (IOException e) {
                throw new Exception();
            } finally {
                if (httpResponse != null) {
                    try {
                        httpResponse.close();
                    } catch (IOException e) {
                    }
                }
            }
        } finally {
            Long endTimeMills = System.currentTimeMillis();
            Long dotimes = endTimeMills - startTimeMills;
            if (dotimes > 1000) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(dotimes)
                        .append(" Mills! httpclient call times more than 1000Millis, url = ")
                        .append(url)
                        .append(", param = ")
                        .append(requestMap.toString());
                logger.warn(buffer.toString());
            }
        }
        return returnMsg;
    }

    private static void solaMap(Map<String ,String> map){
        if(map.containsKey("merchantSign")){
            map.put("merchantSign", "");
        }
        if(map.containsKey("merchantCert")){
            map.put("merchantCert", "");
        }
    }

    /**
     * 日志输出问题
     * @param resultContent
     * @return
     */
    private static String handlerResult(String resultContent){
        String certStr = "&serverCert=[^&]+&";
        String signStr = "&serverSign=[^&]+&";
        String newString = resultContent.replaceFirst(certStr,"&serverCert=''&");
        newString = newString.replaceFirst(signStr,"&serverSign=''&");
        return newString;
    }
}
