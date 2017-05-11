package org.jon.lv.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryAction;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.jon.lv.common.ESTools;
import org.jon.lv.utils.ObjectUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


/**
 * 封装ES公共方法，如需修改，请找相关人员或继承该方法进行拓展
 *
 */
public class ElasticSearchManager {

	public static Client client = ESTools.client;

	/**
	 * 当Elasticsearch在你的文档中探测到一个新的字符串字段，它将自动设置它为全文 string  字段并用 standard  分析器分析。
	 * 你不可能总是想要这样做。也许你想使用一个更适合这个数据的语言分析器。或者，你只想把字符串字段当作一个普通的字
	 * 段——不做任何分析，只存储确切值，就像字符串类型的用户ID或者内部状态字段或者标签。
	 * 为了达到这种效果，我们必须通过映射(mapping)人工设置这些字段。
	 */
	/**
	 * 定义索引的映射类型
	 * @param index
	 * @param type
	 * @param map
	 * @param expire
     * @throws IOException
     */
	public static void defineIndexTypeMapping(String index, String type, Map<String, Object> map, String expire) throws IOException {
		// Client client = ElasticSearchManager.client;
		XContentBuilder mapping = jsonBuilder().startObject().startObject(type).startObject("_ttl").field("enabled", true).field("default", expire).endObject().startObject("properties");

		// map包含mapping相关数据类型信息
		if (ObjectUtils.isNotBlank(map)) {
			Iterator<String> iter = map.keySet().iterator();
			while (iter.hasNext()) {
				String field = iter.next().toString();
				@SuppressWarnings("unchecked")
				Map<String, Object> val = (Map<String, Object>) map.get(field);
				String fieldType = (String) val.get("type");
				String FieldIndex = (String) val.get("index");
				mapping.startObject(field).field("type", fieldType).field("index", FieldIndex).endObject();
			}
		}
		mapping.endObject().endObject().endObject();
		// 指定索引类型
		if (ObjectUtils.isNotBlank(type)) {
			PutMappingRequest mappingRequest = Requests.putMappingRequest(index).type(type).source(mapping);
			client.admin().indices().putMapping(mappingRequest).actionGet();
		}
		// 指定索引名称
		else {
			PutMappingRequest mappingRequest = Requests.putMappingRequest(index).source(mapping);
			client.admin().indices().putMapping(mappingRequest).actionGet();
		}
	}
}

