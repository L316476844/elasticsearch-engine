package org.jon.lv.manager;

import org.jon.lv.JunitBaseTest;
import org.junit.Test;

/**
 * @Description: sql查询
 * Author lv bin
 * @date 2016/12/14 9:15
 * version V1.0.0
 */
public class SqlManagerTest extends JunitBaseTest {

    @Test
    public void testSqlToEsQuery(){

//        String sql = "SELECT * FROM megacorp";
//        String sql = "SELECT * FROM megacorp/person";

        String sql = "SELECT first_name, last_name, age, about, interests " +
                "FROM megacorp/employee where age > 30 limit 5";

        System.out.println("sql---" + sql + ":\n----------\n" + SqlManager.sqlToEsQuery(sql));
    }

    @Test
    public void testQueryBySQL(){
        String sql = "SELECT * FROM megacorp/employee";
        SqlManager.queryBySQL(sql);
    }
}
