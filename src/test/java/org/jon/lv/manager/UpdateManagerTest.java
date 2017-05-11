package org.jon.lv.manager;

import net.sf.json.JSONObject;
import org.jon.lv.JunitBaseTest;
import org.junit.Test;

/**
 * @Description: 更新
 * Author lv bin
 * @date 2016/12/13 16:44
 * version V1.0.0
 */
public class UpdateManagerTest extends JunitBaseTest {
    @Test
    public void testUpsert(){
        // 5:新增修改数据
        String json = "{\n" +
                "\"id\" : \"10\",\n" +
                "\"first_name\" : \"jon\",\n" +
                "\"last_name\" : \"snow\",\n" +
                "\"age\" : 28,\n" +
                "\"about\" : \"I love to go rock climbing\",\n" +
                "\"interests\": [ \"basketball\", \"music\" ]\n" +
                "}";
        UpdateManager.upsertJsonObject("megacorp", "employee", "10", JSONObject.fromObject(json));
    }

    @Test
    public void testUpdateMap(){
        // 6：修改数据
        String json = "{\n" +
                "\"id\" : \"11\",\n" +
                "\"first_name\" : \"curry\",\n" +
                "\"last_name\" : \"stephen\",\n" +
                "\"age\" : 27,\n" +
                "\"about\" : \"I love to go rock climbing\",\n" +
                "\"interests\": [ \"basketball\", \"music\" , \"football\"]\n" +
                "}";
        UpdateManager.updateMap("megacorp", "employee", "11", JSONObject.fromObject(json));
    }
}
