package org.jon.lv.manager;

import org.jon.lv.JunitBaseTest;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: ElasticSearchManager 测试
 * Author lv bin
 * @date 2016/12/12 8:53
 * version V1.0.0
 */
public class ElasticSearchManagerTest extends JunitBaseTest {

    @Test
    public void testDefineIndexTypeMapping() throws IOException {
        // 1: 添加mapping http方式创建索引和mapping
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> value = new HashMap<String, Object>();
        /**
         * index  参数控制字符串以何种方式被索引。它包含以下三个值当中的一个：
         * 1、analyzed 首先分析这个字符串，然后索引。换言之，以全文形式索引此字段。
         * 2、not_analyzed 索引这个字段，使之可以被搜索，但是索引内容和指定值一样。不分析此字段。
         * 3、no 不索引这个字段。这个字段不能为搜索到。
         */
        value.put("index", "not_analyzed");
        /**
         * string  类型字段默认值是 analyzed  。如果我们想映射字段为确切值，我们需要设置它为 not_analyzed  ：
         */
        value.put("type", "string");

        map.put("other", value);

//        XContentBuilder mapBuilder = XContentFactory.jsonBuilder();
//        mapBuilder.startObject()
//                .startObject(TypeName)
//                .startObject("properties")
//                .startObject(IDFieldName).field("type", "long").field("store", "yes").endObject()
//                .startObject(SeqNumFieldName).field("type", "long").field("store", "yes").endObject()
//                .startObject(IMSIFieldName).field("type", "string").field("index", "not_analyzed").field("store", "yes").endObject()
//                .startObject(IMEIFieldName).field("type", "string").field("index", "not_analyzed").field("store", "yes").endObject()
//                .startObject(DeviceIDFieldName).field("type", "string").field("index", "not_analyzed").field("store", "yes").endObject()
//                .startObject(OwnAreaFieldName).field("type", "string").field("index", "not_analyzed").field("store", "yes").endObject()
//                .startObject(TeleOperFieldName).field("type", "string").field("index", "not_analyzed").field("store", "yes").endObject()
//                .startObject(TimeFieldName).field("type", "date").field("store", "yes").endObject()
//                .endObject()
//                .endObject()
//                .endObject();

        ElasticSearchManager.defineIndexTypeMapping("systemlog", "", map, "");
    }
}
