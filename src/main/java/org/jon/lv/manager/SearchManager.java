package org.jon.lv.manager;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.jon.lv.common.ESTools;
import org.jon.lv.query.QueryBo;
import org.jon.lv.utils.ObjectUtils;

import java.util.List;

/**
 * @Description: ES 查询索引
 * Author lv bin
 * @date 2016/12/13 16:30
 * version V1.0.0
 */
public class SearchManager {
    public static Client client = ESTools.client;

    /**
     * 根据id查找
     * @param index
     * @param type
     * @param id
     * @return
     */
    public static GetResponse findById(String index, String type, String id){
        /**
         * 默认情况下， operationThreaded  设置为true表示操作执行在不同的线程上面。
         * 下面是一个设置为false的例子。
         */
//		return client.prepareGet(index, type, id).get();
        return client.prepareGet(index, type, id).setOperationThreaded(false).execute().actionGet();
    }

    /**
     * ES通用搜索接口参数封装
     * @param queryBo
     * @return
     */
    public static SearchResponse search(QueryBo queryBo) {

        /**
         * SearchResponse response = client.prepareSearch("index1", "index2")
         * .setTypes("type1", "type2")
         * .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
         * .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
         * .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
         * .setFrom(0).setSize(60).setExplain(true)
         * .execute()
         * .actionGet();
         */

        return search(queryBo.getIndex(), queryBo.getType(), queryBo.getPageNo(), queryBo.getPageSize(), queryBo.getBuilder(), queryBo.getFilter(), queryBo.getSortList(),
                queryBo.getAggregation());
    }

    /**
     * ES通用搜索接口
     * @param index
     * @param type
     * @param pageNo
     * @param pageSize
     * @param builder
     * @param filter
     * @param sortList
     * @param aggregation
     * @return
     */
    private static SearchResponse search(String index, String type, Integer pageNo, Integer pageSize, QueryBuilder builder, QueryBuilder filter, List<SortBuilder> sortList,
                                         AbstractAggregationBuilder aggregation) {
        /** 判断参数 **/
        // 1:index
        SearchRequestBuilder searchRequset = ESTools.client.prepareSearch();
        if (ObjectUtils.isNotBlank(index)) {
            searchRequset.setIndices(index).setSearchType(SearchType.DFS_QUERY_THEN_FETCH); // 索引index与精确查询
        }

        // 2:type
        if (ObjectUtils.isNotBlank(type)) {
            searchRequset.setTypes(type); // 索引type
        }

        // 3:query
        if (ObjectUtils.isNotBlank(builder)) {
            searchRequset.setQuery(builder); // 一:查询query
        }

        // 4:Filter
        if (ObjectUtils.isNotBlank(filter)) {
            searchRequset.setPostFilter(filter); // 二:过滤Filter
        }

        // 5:page
        if (ObjectUtils.isNotBlank(pageNo) && ObjectUtils.isNotBlank(pageSize)) {
            searchRequset.setFrom((pageNo - 1) * pageSize).setSize(pageSize); // 三:分页page
            searchRequset.setExplain(true);// 设置是否按查询匹配度排序
        }
        // 6:sort
        if (ObjectUtils.isNotBlank(sortList) && sortList.size() > 0) {
            for (SortBuilder sort : sortList) {
                searchRequset.addSort(sort); // 四:排序sort
            }
        }
        // 7:aggregation
        if (ObjectUtils.isNotBlank(aggregation)) {
            searchRequset.addAggregation(aggregation);// 五:分组agg
        }
        SearchResponse searchResponse = searchRequset.execute().actionGet(); // 六:执行搜索

        return searchResponse;
    }
}
