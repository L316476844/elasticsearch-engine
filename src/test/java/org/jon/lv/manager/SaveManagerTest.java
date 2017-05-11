package org.jon.lv.manager;

import net.sf.json.JSONArray;
import org.jon.lv.JunitBaseTest;
import org.jon.lv.utils.ObjectUtils;
import org.junit.Test;

/**
 * @Description: 保存
 * Author lv bin
 * @date 2016/12/13 16:42
 * version V1.0.0
 */
public class SaveManagerTest extends JunitBaseTest {

    @Test
    public void testSave() {
        // 2: 添加数据到ES
        String json = "[{\"buyId\":521,\"buyName\":\"梦想海贼王\",\"cartFrom\":1,\"cartItemList\":[{\"agentPrice\":0,\"cartId\":7994,\"createTime\":201411031413,\"endTime\":0,\"goodsId\":\"1234567890\",\"goodsInfo\":\"{\\\"登录时间\\\":\\\"2014/11/03\\\",\\\"充值\\\":\\\"10000000\\\",\\\"等级排名\\\":\\\"1\\\",\\\"实力排名\\\":\\\"1\\\",\\\"联系电话\\\":\\\"110\\\",\\\"外号\\\":\\\"习大大\\\"}\",\"goodsType\":3,\"goodsUrl\":\"www.baidu.com\",\"id\":559410,\"imageUrl\":\"www.baidu.com\",\"isSpecial\":0,\"nowPrice\":20000,\"number\":1,\"payEndTime\":0,\"price\":10000,\"saleInfo\":\"圣诞促销\",\"status\":0,\"title\":\"梦想海贼王2\",\"weight\":3},{\"agentPrice\":0,\"cartId\":7994,\"createTime\":201411031412,\"endTime\":0,\"goodsId\":\"23123123\",\"goodsInfo\":\"{\\\"登录时间\\\":\\\"2014/11/03\\\",\\\"充值\\\":\\\"10000000\\\",\\\"等级排名\\\":\\\"1\\\",\\\"实力排名\\\":\\\"1\\\",\\\"联系电话\\\":\\\"110\\\",\\\"外号\\\":\\\"习大大\\\"}\",\"goodsType\":2,\"goodsUrl\":\"www.baidu.com\",\"id\":559409,\"imageUrl\":\"www.baidu.com\",\"isSpecial\":0,\"nowPrice\":20000,\"number\":1,\"payEndTime\":0,\"price\":10000,\"saleInfo\":\"圣诞促销\",\"status\":0,\"title\":\"梦想海贼王\",\"weight\":3}],\"cartNo\":\"52111\",\"cartType\":3,\"comAccount\":\"mxhzw2014\",\"comId\":125,\"comRole\":\"I don`t know!\",\"createTime\":458741963,\"del\":0,\"delTime\":0,\"endTime\":0,\"id\":7994,\"isSpecial\":0,\"kdInfo\":\"\",\"key\":\"\",\"otherUrl\":\"101235445.com\",\"payEndTime\":0,\"payTime\":0,\"payType\":0,\"reserve\":\"\",\"reserve1\":\"\",\"saleId\":0,\"saleName\":\"梦想海贼王222\",\"salePhone\":\"\",\"serviceOrderId\":\"\",\"supplyId\":0,\"weights\":0},{\"buyId\":521,\"buyName\":\"梦想海贼王222\",\"cartFrom\":1,\"cartItemList\":[{\"agentPrice\":0,\"cartId\":7993,\"createTime\":201411031412,\"endTime\":0,\"goodsId\":\"23123123\",\"goodsInfo\":\"{\\\"登录时间\\\":\\\"2014/11/03\\\",\\\"充值\\\":\\\"10000000\\\",\\\"等级排名\\\":\\\"1\\\",\\\"实力排名\\\":\\\"1\\\",\\\"联系电话\\\":\\\"110\\\",\\\"外号\\\":\\\"习大大\\\"}\",\"goodsType\":2,\"goodsUrl\":\"www.baidu.com\",\"id\":559407,\"imageUrl\":\"www.baidu.com\",\"isSpecial\":0,\"nowPrice\":20000,\"number\":1,\"payEndTime\":0,\"price\":10000,\"saleInfo\":\"圣诞促销\",\"status\":0,\"title\":\"梦想海贼王\",\"weight\":3},{\"agentPrice\":0,\"cartId\":7993,\"createTime\":201411031413,\"endTime\":0,\"goodsId\":\"1234567890\",\"goodsInfo\":\"{\\\"登录时间\\\":\\\"2014/11/03\\\",\\\"充值\\\":\\\"10000000\\\",\\\"等级排名\\\":\\\"1\\\",\\\"实力排名\\\":\\\"1\\\",\\\"联系电话\\\":\\\"110\\\",\\\"外号\\\":\\\"习大大\\\"}\",\"goodsType\":3,\"goodsUrl\":\"www.baidu.com\",\"id\":559408,\"imageUrl\":\"www.baidu.com\",\"isSpecial\":0,\"nowPrice\":20000,\"number\":1,\"payEndTime\":0,\"price\":10000,\"saleInfo\":\"圣诞促销\",\"status\":0,\"title\":\"梦想海贼王2\",\"weight\":3}],\"cartNo\":\"521111\",\"cartType\":3,\"comAccount\":\"mxhzw2014\",\"comId\":125,\"comRole\":\"I don`t know!\",\"createTime\":358741963,\"del\":0,\"delTime\":0,\"endTime\":0,\"id\":7993,\"isSpecial\":0,\"kdInfo\":\"\",\"key\":\"\",\"otherUrl\":\"101235445.com\",\"payEndTime\":0,\"payTime\":0,\"payType\":0,\"reserve\":\"\",\"reserve1\":\"\",\"saleId\":0,\"saleName\":\"梦想海贼王\",\"salePhone\":\"\",\"serviceOrderId\":\"\",\"supplyId\":0,\"weights\":0}]";
        JSONArray jsonArray = ObjectUtils.isJSONArray(json);
        SaveManager.saveJsonArray("vinuxcart", "cart", null, jsonArray);
    }
}
