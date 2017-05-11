package org.jon.lv.manager;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryAction;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.client.Client;
import org.jon.lv.common.ESTools;
import org.jon.lv.result.APIResult;

/**
 * @Description: ES 删除索引
 * Author lv bin
 * @date 2016/12/13 16:28
 * version V1.0.0
 */
public class DeleteManager {

    public static Client client = ESTools.client;

    // 查询所有
    private static String QUERY_ALL = "{\"query\": {\"match_all\": {}}}";

    /**
     * 根据id删除ES数据
     * @param index
     * @param type
     * @param id
     * @return
     */
    public static APIResult<String> deleteById(String index, String type, String id) {
        DeleteResponse response = client.prepareDelete(index, type, id).setRefresh(true).execute().actionGet();
        boolean isFound = response.isFound();
        if (isFound) {
            return new APIResult<String>(500, "删除ES失败!");
        }
        return new APIResult<String>(200, "删除ES成功!");

    }

    /**
     * 清空index下的某个type---等价于清空表
     * @param index
     * @param type
     * @return
     */
    public static APIResult<String> emptyType(String index, String type) {

        return deleteByQuery(index, type, QUERY_ALL);
    }

    /**
     * 删除index下的某个type
     * ---2.0以上版本已经失效-5.0版本回归支持-请勿使用-目前版本2.4.1 报错
     * @param index
     * @param type
     * @return
     */
    @Deprecated
    public static APIResult<String> deleteType(String index, String type) {
        DeleteResponse response = client.prepareDelete().setIndex(index).setType(type).execute().actionGet();
        boolean isFound = response.isFound();
        if (isFound) {
            return new APIResult<String>(500, "删除ES失败!");
        }

        return new APIResult<String>(200, "删除ES成功!");
    }

    /**
     * 根据索引名称删除索引---等价于 删除整个数据库操作 --勿用
     * @param index 索引名称
     */
    public static void deleteIndex(String index) {

        IndicesExistsRequest indicesExistsRequest = new IndicesExistsRequest();
        indicesExistsRequest.indices(new String[]{index.toLowerCase()});

        boolean exists = client.admin().indices().exists(indicesExistsRequest).actionGet().isExists();
        if (exists) {
            client.admin().indices().prepareDelete(index.toLowerCase()).execute().actionGet();
        }
    }

    /**
     * 根据查询条件删除
     * @param index
     * @param type
     * @param queryString
     * @return
     */
    public static APIResult<String> deleteByQuery(String index, String type, String queryString){
        DeleteByQueryResponse response =  new DeleteByQueryRequestBuilder(client,
                DeleteByQueryAction.INSTANCE)
                .setIndices(index)
                .setTypes(type)
                .setSource(queryString)
                .execute()
                .actionGet();

        if (response.getTotalFailed() > 0l) {
            return new APIResult<String>(500, "删除ES-Type失败!");
        }

        return new APIResult<String>(200, "删除ES-Type成功!");
    }
}
