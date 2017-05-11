package org.jon.lv.manager;

import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.jon.lv.common.ESTools;
import org.jon.lv.utils.Logger;
import org.nlpcn.es4sql.SearchDao;
import org.nlpcn.es4sql.query.QueryAction;

/**
 * @Description: sql 查询 - http://192.168.88.176:9200/_plugin/sql/
 * Author lv bin
 * @date 2016/12/14 9:11
 * version V1.0.0
 */
public class SqlManager {
    public static Client client = ESTools.client;

    private static SearchDao searchDao = new SearchDao(client);

    /**
     * 生成es查询语句
     * @param sql
     * @return
     */
    public static String sqlToEsQuery(String sql) {
        try {
            return searchDao.explain(sql).explain().explain();
        } catch (Exception e) {
            Logger.error(SqlManager.class, e.getMessage());
        }
        return null;
    }

    public static SearchResponse queryBySQL(String sql){
        try {
            System.out.println("*******************************");
            System.out.println(searchDao.explain(sql).explain().getBuilder().execute().actionGet());
            System.out.println("------------------------------");
//            return (SearchResponse)searchDao.explain(sql).explain().getBuilder().execute().actionGet();
            return null;
        } catch (Exception e) {
            Logger.error(SqlManager.class, e.getMessage());
        }
        return null;
    }

}
