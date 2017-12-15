package com.fzzq.data.analysis.executor.db;

import com.example.demo.BaseTest;
import com.fzzq.data.analysis.executor.cache.CacheFactory;
import com.fzzq.data.analysis.executor.config.DbConfig;
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

        DAO dao = daoFactory.getCCDao(dataBase);

        String ss = "select *\n" +
                "  from (select at.cust_no custNo,\n" +
                "               o.cust_name custName,\n" +
                "               (select t2.value_desc\n" +
                "                  from cc.sy_t_code t2\n" +
                "                 where t2.type_id = '4036'\n" +
                "                   and t2.value_code = o.CUST_LEVEL) custLevel,\n" +
                "               to_char(sum(nvl(at.trade_sum, 0)), 'fm99999999999999990.00') tradeSum,\n" +
                "               to_char(sum(nvl(at.clear_feex, 0)), 'fm99999999999999990.00') clearFeex,\n" +
                "               to_char(sum(case\n" +
                "                             when at.trademonth = '201708' then\n" +
                "                              nvl(at.asset_end, 0)\n" +
                "                             else\n" +
                "                              0\n" +
                "                           end) - sum(case\n" +
                "                                        when at.trademonth = '200001' then\n" +
                "                                         nvl(at.asset_start, 0)\n" +
                "                                        else\n" +
                "                                         0\n" +
                "                                      end) -\n" +
                "                       sum(abs(nvl(at.rollin_mvalue, 0)) -\n" +
                "                           abs(nvl(at.rollout_mvalue, 0)) +\n" +
                "                           abs(nvl(at.rollin_money, 0)) -\n" +
                "                           abs(nvl(at.rollout_money, 0))),\n" +
                "                       'fm99999999999999990.00') profit,\n" +
                "               to_char((sum(case\n" +
                "                              when at.trademonth = '201708' then\n" +
                "                               nvl(at.asset_end, 0)\n" +
                "                              else\n" +
                "                               0\n" +
                "                            end) - sum(case\n" +
                "                                          when at.trademonth = '200001' then\n" +
                "                                           nvl(at.asset_start, 0)\n" +
                "                                          else\n" +
                "                                           0\n" +
                "                                        end) -\n" +
                "                       sum(abs(nvl(at.rollin_mvalue, 0)) -\n" +
                "                            abs(nvl(at.rollout_mvalue, 0)) +\n" +
                "                            abs(nvl(at.rollin_money, 0)) -\n" +
                "                            abs(nvl(at.rollout_money, 0)))) /\n" +
                "                       (max(nvl(at.market_value_high, 0)) + 1) * 100,\n" +
                "                       'fm99999999999999990.00') profitRate,\n" +
                "               to_char(sum(case\n" +
                "                             when at.trademonth = '200001' then\n" +
                "                              nvl(at.asset_start, 0)\n" +
                "                             else\n" +
                "                              0\n" +
                "                           end),\n" +
                "                       'fm99999999999999990.00') assetStart,\n" +
                "               to_char(sum(case\n" +
                "                             when at.trademonth = '201708' then\n" +
                "                              nvl(at.asset_end, 0)\n" +
                "                             else\n" +
                "                              0\n" +
                "                           end),\n" +
                "                       'fm99999999999999990.00') assetEnd,\n" +
                "               to_char(nvl(o.SFWB_ASSET, 0), 'fm99999999999999990.00') currentAsset,\n" +
                "               nvl(o.posit_num, 0) positNum\n" +
                "          from cc.tb_ms_asset_trade at\n" +
                "         inner join cc.tb_cust_overview o\n" +
                "            on at.cust_no = o.customer_no\n" +
                "         where at.trademonth between '200001' and '201708'\n" +
                "         group by at.cust_no,\n" +
                "                  o.cust_name,\n" +
                "                  o.cust_level,\n" +
                "                  o.sfwb_asset,\n" +
                "                  o.posit_num)\n";


        SimpleCache dataBaseCache = cacheFactory.getDataBaseCache(dataBase);

        String cacheKey = dataBase + ":" + "trade";

        System.out.println("开始查询！");
        long start = System.currentTimeMillis();
        ArrayList<Row> rows = dataBaseCache.get(cacheKey, () -> new ArrayList<>(dao.query(ss)));
        long end = System.currentTimeMillis();
        System.out.println("结束查询！查询耗时：" + (end - start));

        System.out.println("记录数为：" + rows.size());
    }

    @Test
    public void test2() throws Exception{
        DAO dcDao = daoFactory.getDCDao(DbConfig.DATABASE_DC);

        String sql = "select * from INFO_MENUCONDITION";

        List<Row> query = dcDao.query(sql);

        System.out.println(query.size());
    }
}