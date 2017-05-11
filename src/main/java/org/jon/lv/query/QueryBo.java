package org.jon.lv.query;

import java.util.List;

import org.apache.lucene.queryparser.xml.FilterBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;

/**
 * @Description: 通用搜索接口有关参数
 * Author lv bin
 * @date 2016/12/10 23:13
 * version V1.0.0
 */
public class QueryBo {
	/** 索引名称 */
	private String index;
	/** 索引类型 */
	private String type;
	/**
	 * 搜索类型
	 * query_then_fetch 是默认的搜索类型
	 * query_and_fetch（查询并且取回）
	 * count（计数）
	 * dfs_query_then_fetch 和 dfs_query_and_fetch dfs  搜索类型有一个预查询的阶段
	 * scan（扫描）
	 */
	private SearchType searchType = SearchType.QUERY_THEN_FETCH;
	/** 当前页码 */
	private Integer pageNo = 1;
	/** 当前页数 */
	private Integer pageSize = 20;
	/** 查询query */
	private QueryBuilder builder;
	/** 过滤Filter */
	private QueryBuilder filter;
	/** 排序sort */
	private List<SortBuilder> sortList;
	/** 分组agg */
	private AbstractAggregationBuilder aggregation;

	public QueryBo(String index, String type) {
		this.index = index;
		this.type = type;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public QueryBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(QueryBuilder builder) {
		this.builder = builder;
	}

	public QueryBuilder getFilter() {
		return filter;
	}

	public void setFilter(QueryBuilder filter) {
		this.filter = filter;
	}

	public List<SortBuilder> getSortList() {
		return sortList;
	}

	public void setSortList(List<SortBuilder> sortList) {
		this.sortList = sortList;
	}

	public AbstractAggregationBuilder getAggregation() {
		return aggregation;
	}

	public void setAggregation(AbstractAggregationBuilder aggregation) {
		this.aggregation = aggregation;
	}

	public SearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}

}
