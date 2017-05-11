package org.jon.lv.manager;

import net.sf.json.JSONObject;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.jon.lv.common.ESTools;
import org.jon.lv.result.APIResult;
import org.jon.lv.utils.ObjectUtils;

import java.util.Map;

/**
 * @Description: ES 修改
 * Author lv bin
 * @date 2016/12/13 16:35
 * version V1.0.0
 */
public class UpdateManager {

    public static Client client = ESTools.client;

    /**
     * 7：Map单条数据更新到ES
     * @param index
     * @param type
     * @param idValue
     * @param updateMap
     * @return
     */
    public static APIResult<String> updateMap(String index, String type, String idValue, Map<String, Object> updateMap) {
        BulkRequestBuilder bulkRequest = client.prepareBulk().setRefresh(true);
        UpdateRequestBuilder request = client.prepareUpdate();
        if (ObjectUtils.isNotBlank(index)) {
            request.setIndex(index);
        }
        if (ObjectUtils.isNotBlank(type)) {
            request.setType(type);
        }
        if (ObjectUtils.isNotBlank(idValue)) {
            request.setId(idValue);
        }

        if (ObjectUtils.isNotBlank(updateMap)) {
            request.setDoc(updateMap);
        }
        bulkRequest.add(request);
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            System.out.println(bulkResponse.getItems().toString());
            return new APIResult<String>(500, "更新ES失败!");
        }
        bulkRequest = client.prepareBulk();
        return new APIResult<String>(200, "更新ES成功!");
    }

    /**
     * 8: 存在则更新  不存在则插入
     * @param index
     * @param type
     * @param idValue
     * @param jsonObject
     * @return
     */
    public static APIResult<String> upsertJsonObject(String index, String type, String idValue, JSONObject jsonObject) {

        IndexRequest indexRequest = new IndexRequest(index, type, idValue)
                .source(jsonObject);

        UpdateRequest updateRequest = new UpdateRequest(index, type, idValue)
                .doc(jsonObject)
                .upsert(indexRequest);

        UpdateResponse response = client.update(updateRequest).actionGet();

        if(response.getShardInfo().getFailed() > 0 ){
            return new APIResult<String>(500, "更新ES失败!");
        }

        if(response.isCreated()){
            return new APIResult<String>(200, "新增ES成功!");
        }

        return new APIResult<String>(200, "更新ES成功!");
    }

}
