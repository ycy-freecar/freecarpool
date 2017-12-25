package com.ycy.freecarpool.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * Created by tianwc on 2016/6/14. redis相关操作
 */
public class RedisUtil {
    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

   /* private static final int MAX_ALLOWED_TIMES = 20;
    private static final String REDIS_KEY = "merchant:";
    private static final List<JedisPool> pools;

    static {
        ResourceBundle redisResource = ResourceBundle.getBundle("biz", Locale.getDefault());
        JedisPoolConfig poolConfig = getPoolConfig(redisResource);
        String[] redisInfoArray = ResourceUtil.getArray("cache.r.redisInfo", redisResource);

        log.info("master redis info {}", JSON.toJSONString(redisInfoArray));

        pools = Lists.newArrayListWithExpectedSize(redisInfoArray.length);
        for (String redisInfo : redisInfoArray) {
            String[] master = redisInfo.split(":");
            pools.add(new JedisPool(poolConfig, master[0], NumberUtils.toInt(master[1], 6379)));
        }
    }

    private static JedisPoolConfig getPoolConfig(ResourceBundle redisResource) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setTestOnBorrow(ResourceUtil.getBoolean("cache.r.testonborrow", redisResource));
        poolConfig.setMaxIdle(ResourceUtil.getInt("cache.r.maxidle", redisResource));
        poolConfig
                .setMinEvictableIdleTimeMillis(ResourceUtil.getInt("cache.r.softMinEvictableIdleTime", redisResource));
        poolConfig.setMaxTotal(ResourceUtil.getInt("cache.r.maxtotal", redisResource));
        poolConfig.setMaxWaitMillis(ResourceUtil.getInt("cache.r.maxwaitmillis", redisResource));
        log.info("redis pool config info is {}", new String[] { JSON.toJSONString(poolConfig) });

        return poolConfig;
    }

    public static List<String> getActiveNumber() {
        List<String> activeNumberList = new ArrayList<>(pools.size());
        for (JedisPool pool : pools) {
            activeNumberList.add(getRedisInfo(pool.getResource()) + "-" + pool.getNumActive());
        }
        return activeNumberList;
    }

    public static List<String> getIdleNumber() {
        List<String> idleNumberList = new ArrayList<>(pools.size());
        for (JedisPool pool : pools) {
            idleNumberList.add(getRedisInfo(pool.getResource()) + "-" + pool.getNumIdle());
        }
        return idleNumberList;
    }

    public static List<String> getWaitNumber() {
        List<String> waitNumberList = new ArrayList<>(pools.size());
        for (JedisPool pool : pools) {
            waitNumberList.add(getRedisInfo(pool.getResource()) + "-" + pool.getNumIdle());
        }
        return waitNumberList;
    }

    private static String getRedisInfo(Jedis jedis) {
        return jedis.getClient().getHost() + ":" + jedis.getClient().getPort();
    }

    private static JedisPool getPool(String key) {
        int index = (key.hashCode()) % pools.size();
        return pools.get(index);
    }

    private static void close(JedisPool pool, Jedis jedis) {
        if (jedis != null && pool != null) {
            pool.returnResource(jedis);
        }
    }

    private static String makeKey(String key) {
        return REDIS_KEY + key;
    }

    public static void set(String key, String value) {
        notBlank(key, "key无意义");
        notBlank(value, "value无意义");
        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            jedis.set(makeKey(key), value);
        } finally {
            close(pool, jedis);
        }
    }

    public static void setExpireWithSecondFormat(String key, int expire) {
        if (StringUtils.isEmpty(key)) {
            return;
        }

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            jedis.expire(makeKey(key), expire);
        } catch (Exception ex) {
            log.warn("occurs exception", ex);
            throw ex;
        } finally {
            close(pool, jedis);
        }
    }

    public static String getAndSet(String key, String value) {
        notBlank(key, "key无意义");
        Assert.notNull(value, "value不能为null");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.getSet(makeKey(key), value);
        } finally {
            close(pool, jedis);
        }
    }

    *//**
     * 带过期时间的保存
     *
     * @param expireAt
     *            过期时间的时间戳,注意精确到秒！！！
     *//*
    public static void setKeyExpireAt(String key, long expireAt) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            jedis.expireAt(makeKey(key), expireAt);
        } finally {
            close(pool, jedis);
        }
    }

    *//**
     * 带过期时间的保存
     *
     * @param expire
     *            过期时间单位秒
     *//*
    public static void setWithExpire(String key, String value, int expire) {
        notBlank(key, "key无意义");
        Assert.notNull(value, "value不能为null");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            jedis.setex(makeKey(key), expire, value);
        } finally {
            close(pool, jedis);
        }
    }

    *//**
     * 由Redis保证的原子加操作
     *//*
    public static long plus(String key, long value) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.incrBy(makeKey(key), value);
        } finally {
            close(pool, jedis);
        }

    }

    public static long plusOne(String key) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.incr(makeKey(key));
        } finally {
            close(pool, jedis);
        }
    }

    *//**
     * 由Redis保证的原子减操作
     *
     * @return
     *//*
    public static long minus(String key, long value) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.decrBy(makeKey(key), value);
        } finally {
            close(pool, jedis);
        }
    }

    public static long minusOne(String key) {
        return minus(key, 1);
    }

    public static String getStr(String key) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.get(makeKey(key));
        } finally {
            close(pool, jedis);
        }
    }

    public static void addIntoHashMap(String key, String fieldKey, String fieldValue) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(fieldKey) || StringUtils.isEmpty(fieldValue))
            return;

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            jedis.hset(makeKey(key), fieldKey, fieldValue);
        } catch (Exception ex) {
            log.warn("occurs exception", ex);
            throw ex;
        } finally {
            close(pool, jedis);
        }
    }

    public static String getValueFromMap(String key, String fieldKey) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(fieldKey))
            return null;

        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.hget(makeKey(key), fieldKey);
        } catch (Exception ex) {
            log.warn("occurs exception", ex);
            throw ex;
        } finally {
            close(pool, jedis);
        }
    }

    public static void deleteFromHashMap(String key, String fieldKey) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(fieldKey))
            return;

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            jedis.hdel(makeKey(key), fieldKey);
        } catch (Exception ex) {
            log.warn("occurs exception", ex);
            throw ex;
        } finally {
            close(pool, jedis);
        }
    }

    public static void addIntoHashMap(String key, Map<String, String> valueMap) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            jedis.hmset(makeKey(key), valueMap);
        } finally {
            close(pool, jedis);
        }
    }

    public static Map<String, String> getHashMap(String key) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.hgetAll(makeKey(key));
        } finally {
            close(pool, jedis);
        }
    }

    public static Long lPush(String key, String... values) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.lpush(makeKey(key), values);
        } finally {
            close(pool, jedis);
        }
    }

    public static String rPop(String key) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.rpop(makeKey(key));
        } finally {
            close(pool, jedis);
        }
    }

    public static List<String> range(String key, long start, long end) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.lrange(makeKey(key), start, end);
        } finally {
            close(pool, jedis);
        }
    }

    public static long delete(String key) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.del(makeKey(key));
        } finally {
            close(pool, jedis);
        }
    }

    public static void notBlank(String obj, String message) {
        if (obj == null || "".equals(obj.trim())) {
            throw new IllegalArgumentException(message);
        }
    }

    *//**
     * 向集合中添加元素
     *
     * @param key
     *            键
     * @param value
     *            值
     * @return 添加成功数
     *//*
    public static long sadd(String key, String value) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.sadd(key, value);
        } finally {
            close(pool, jedis);
        }
    }

    *//**
     * 获取以key为键的集合
     *
     * @param key
     *            键
     * @return 以key为键的集合
     *//*
    public static Set<String> smembers(String key) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.smembers(key);
        } finally {
            close(pool, jedis);
        }
    }

    *//**
     * 获取以key为键的集合
     *
     * @param key
     *            键
     * @return 以key为键的集合
     *//*
    public static Long delete(String key, String value) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.srem(key, value);
        } finally {
            close(pool, jedis);
        }
    }

    *//**
     * 判断键是否存在
     *
     * @param key
     * @return
     *//*
    public static boolean exists(String key) {
        notBlank(key, "key无意义");

        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.exists(key);
        } finally {
            close(pool, jedis);
        }
    }

    *//**
     * 是否允许访问
     *
     * @param key
     * @param ExpiredSecond
     *            key过期时间
     * @return
     *//*
    public static boolean ifAllowed(String key, int ExpiredSecond) {
        boolean flag = true;
        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            if (exists(key)) {
                jedis.incr(key);
                String value = jedis.get(key);
                try {
                    if (Integer.parseInt(value) > MAX_ALLOWED_TIMES) {
                        flag = false;
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            } else {
                jedis.set(key, "1");
                jedis.expire(key, ExpiredSecond);
            }
        } finally {
            close(pool, jedis);
        }
        return flag;
    }

    *//**
     * 判断 member 元素是否集合 key 的成员
     *
     * @param key
     * @param member
     * @return
     *//*
    public static boolean sismember(String key, String member) {
        notBlank(key, "key无意义");
        notBlank(member, "member无意义");
        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(key);
            jedis = pool.getResource();
            return jedis.sismember(key, member);
        } finally {
            close(pool, jedis);
        }
    }
    *//**
     * 判断 member 元素是否集合 key 的成员
     *
     * @param key
     * @param member
     * @param member
     * @return
     *//*
    public static Long smove(String srcKey, String dstKey, String member) {
        notBlank(srcKey, "srcKey无意义");
        notBlank(dstKey, "dstKey无意义");
        notBlank(member, "member无意义");
        Jedis jedis = null;
        JedisPool pool = null;
        try {
            pool = getPool(srcKey);
            jedis = pool.getResource();
            return jedis.smove(srcKey, dstKey, member);
        } finally {
            close(pool, jedis);
        }
    }*/
}
