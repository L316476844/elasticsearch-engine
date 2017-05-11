package org.jon.lv.manager;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.jon.lv.JunitBaseTest;
import org.jon.lv.query.QueryBo;
import org.junit.Test;

/**
 * @Description: 搜索
 * Author lv bin
 * @date 2016/12/13 16:43
 * version V1.0.0
 */
public class SearchManagerTest extends JunitBaseTest {
    @Test
    public void testById(){
        GetResponse response = SearchManager.findById("megacorp","employee","2");

        System.out.println("------------" + response.isExists());
        System.out.println("------------" + response.isSourceEmpty());
        System.out.println("------------" + response.getSource());
    }

    @Test
    public void testSearch(){
        QueryBo queryBo =  new QueryBo("megacorp", "employee");
//        queryBo.setFilter(QueryBuilders.rangeQuery("age").from(25).to(32)); // 包含起始值
//        queryBo.setFilter(QueryBuilders.rangeQuery("age").gte(25).lte(32)); // 包含起始值
        queryBo.setFilter(QueryBuilders.rangeQuery("age").gt(25).lt(32)); // 不包含起始值
        SearchResponse response = SearchManager.search(queryBo);
        SearchHits searchHits = response.getHits();
        for(SearchHit searchHit: searchHits.getHits()){
            System.out.println("$$$$$$$$$$$$$$$$$$$$" + searchHit.getSource());
        }
    }
}
