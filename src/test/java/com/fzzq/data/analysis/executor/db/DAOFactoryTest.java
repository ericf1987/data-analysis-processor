package com.fzzq.data.analysis.executor.db;

import com.example.demo.BaseTest;
import com.fzzq.data.analysis.executor.cache.CacheFactory;
import com.hyd.dao.DAO;
import com.hyd.dao.Row;
import com.hyd.simplecache.SimpleCache;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengye on 2017/8/7.
 */
public class DAOFactoryTest extends BaseTest {

    @Autowired
    DAOFactory daoFactory;

    @Autowired
    CacheFactory cacheFactory;

    @Test
    public void test() throws Exception {

        String dataBase = "kfdb1";

        DAO dao = daoFactory.getKFDao(dataBase);

        String cacheKey = dataBase + ":kf_t_broker:";

        String sql = "select * from kf_t_broker ";

        List<Row> query = dao.query(sql);

        SimpleCache dataBaseCache = cacheFactory.getDataBaseCache(dataBase);

        ArrayList<Row> rows = dataBaseCache.get(cacheKey, () -> new ArrayList<>(query));

        long status = rows.stream().filter(r -> "1".equals(r.getString("status"))).count();

        System.out.println("状态注销的客户数为：" + status);

        System.out.println("客户总数为：" + rows.size());
    }
}