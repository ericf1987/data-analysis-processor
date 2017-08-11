package com.fzzq.data.analysis.executor.cache;

import com.hyd.simplecache.EhCacheConfiguration;
import com.hyd.simplecache.SimpleCache;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;

/**
 * 缓存创建工厂
 * Created by fengye on 2017/8/7.
 */
@Component
public class CacheFactory {
    public static final int ONE_HOUR = 3600000;

    //每个数据库使用一个缓存
    private final Map<String, SimpleCacheWrapper> dataBaseCache = new HashMap<>();

    private SimpleCacheWrapper globalCache;

    @PostConstruct
    private void initCacheFactory() {
        globalCache = createCache("global");
    }

    public synchronized SimpleCache getDataBaseCache(String database) {

        shrink(dataBaseCache);

        SimpleCacheWrapper result = dataBaseCache.get(database);
        if (null != result) {
            return result.getSimpleCache();
        } else {
            Function<String, SimpleCacheWrapper> creator = k -> createCache("database:" + database);
            return dataBaseCache.computeIfAbsent(database, creator).getSimpleCache();
        }
    }

    public SimpleCache getGlobalCache() {
        return globalCache.getSimpleCache();
    }

    public SimpleCacheWrapper createCache(String cacheName) {
        EhCacheConfiguration conf = new EhCacheConfiguration();
        conf.setName(cacheName);
        conf.setMaxEntriesLocalHeap(100000);
        conf.setTimeToLiveSeconds(3600);
        conf.setTimeToIdleSeconds(3600);
        return new SimpleCacheWrapper(new SimpleCache(conf));
    }

    public synchronized void removeDataBaseCache(String database) {
        SimpleCacheWrapper simpleCacheWrapper = dataBaseCache.get(database);
        if (null != simpleCacheWrapper) {
            simpleCacheWrapper.close();
            dataBaseCache.remove(database);
        }
    }

    @PreDestroy
    private void close() {
        this.globalCache.close();
        this.dataBaseCache.values().forEach(SimpleCacheWrapper::close);
    }

    private static class SimpleCacheWrapper {
        private SimpleCache simpleCache;

        private long lastAccess = System.currentTimeMillis();

        public SimpleCacheWrapper(SimpleCache simpleCache) {
            this.simpleCache = simpleCache;
        }

        public SimpleCache getSimpleCache() {
            lastAccess = System.currentTimeMillis();
            return simpleCache;
        }

        public boolean expired() {
            return System.currentTimeMillis() - lastAccess > ONE_HOUR;
        }

        public void close() {
            if (this.simpleCache != null)
                this.simpleCache.close();
        }
    }

    //缓存收缩，移除超时缓存
    private void shrink(Map<String, SimpleCacheWrapper> cacheMap) {
        Iterator<Map.Entry<String, SimpleCacheWrapper>> iterator = cacheMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, SimpleCacheWrapper> next = iterator.next();
            if (next.getValue().expired()) {
                next.getValue().close();
                iterator.remove();
            }
        }
    }
}
