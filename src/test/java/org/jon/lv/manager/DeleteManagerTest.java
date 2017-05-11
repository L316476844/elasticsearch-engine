package org.jon.lv.manager;


import org.jon.lv.JunitBaseTest;
import org.junit.Test;

import java.io.IOException;

/**
 * @Description: 删除
 * Author lv bin
 * @date 2016/12/13 16:40
 * version V1.0.0
 */
public class DeleteManagerTest extends JunitBaseTest {

    @Test
    public void testDeleteEs() throws IOException {
        DeleteManager.deleteById("megacorp","employee","10");
    }

    @Test
    public void testEmptyType(){
        DeleteManager.emptyType("megacorp", "employee");
    }

    @Test
    public void testDeleteType(){
        DeleteManager.deleteType("megacorp", "employee");
    }

    @Test
    public void testDeleteIndex(){
        DeleteManager.deleteIndex("vinuxcart");
    }
}
