package org.jon.lv.common;

/**
 * @Description: ES 配置初始化
 * Author lv bin
 * @date 2016/12/11 21:07
 * version V1.0.0
 */
public class ESInitialize {

    private ESConfig esConfig;

    public ESConfig getEsConfig() {
        return esConfig;
    }

    public void setEsConfig(ESConfig esConfig) {
        this.esConfig = esConfig;
        ESTools.setEsConfig(esConfig);
    }
}
