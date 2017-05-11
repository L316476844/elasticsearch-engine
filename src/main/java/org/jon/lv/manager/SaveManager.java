package org.jon.lv.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.jon.lv.common.ESTools;
import org.jon.lv.result.APIResult;
import org.jon.lv.utils.ObjectUtils;

import java.util.Map;

/**
 * @Description: ES 保存
 * Author lv bin
 * @date 2016/12/13 16:36
 * version V1.0.0
 */
public class SaveManager {
    public static Client client = ESTools.client;

    /**
     * jsonArray数据保存到ES
     * @param index
     * @param type
     * @param idName
     * @param jsonArray
     * @return
     */
    public static APIResult<String> saveJsonArray(String index, String type, String idName, JSONArray jsonArray) {
        BulkRequestBuilder bulkRequest = client.prepareBulk().setRefresh(true);

        for (Object object : jsonArray) {
            JSONObject json = ObjectUtils.isJSONObject(object);
            String idValue = json.optString(idName);
            if (ObjectUtils.isBlank(idValue)) {
                idValue = idName;
            }

            // 不指定索引id,索引id自动生成
            if (ObjectUtils.isBlank(idName)) {
                IndexRequestBuilder lrb = client.prepareIndex(index, type).setSource(json.toString());
                bulkRequest.add(lrb);
            }

            // 根据指定索引id生成索引
            else {
                IndexRequestBuilder lrb = client.prepareIndex(index, type, idValue).setSource(json.toString());
                bulkRequest.add(lrb);
            }
        }

        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            System.out.println(bulkResponse.getItems().toString());
            return new APIResult<String>(500, "保存ES失败!");
        }
        bulkRequest = client.prepareBulk();
        return new APIResult<String>(200, "保存ES成功!");
    }


    /**
     * 保存Map单条数据保存到ES
     * @param index
     * @param type
     * @param idName
     * @param map
     * @return
     */
    public static APIResult<String> saveMap(String index, String type, String idName, Map<String, Object> map) {
        BulkRequestBuilder bulkRequest = client.prepareBulk().setRefresh(true);

        Object idValue = map.get(idName);
        if (ObjectUtils.isBlank(idValue)) {
            idValue = idName;
        }

        // 不指定索引id,索引id自动生成
        if (ObjectUtils.isBlank(idName)) {
            IndexRequestBuilder lrb = client.prepareIndex(index, type).setSource(map);
            bulkRequest.add(lrb);
        }

        // 根据指定索引id生成索引
        else {
            IndexRequestBuilder lrb = client.prepareIndex(index, type, idValue.toString()).setSource(map);
            bulkRequest.add(lrb);
        }

        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            System.out.println(bulkResponse.getItems().toString());
            return new APIResult<String>(500, "保存ES失败!");
        }
        bulkRequest = client.prepareBulk();
        return new APIResult<String>(200, "保存ES成功!");
    }
}
