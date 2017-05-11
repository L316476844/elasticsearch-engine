package org.jon.lv.common;

/**
 * @Description: ES配置参数
 * Author lv bin
 * @date 2016/12/10 23:30
 * version V1.0.0
 */
public class ESConfig {

    /**
     * es ip
     */
    private  String esIp;

    /**
     * es port
     */
    private  String esPort;

    /**
     * 集群名称
     */
    private  String esClusterName;

    public ESConfig(String esIp, String esPort, String esClusterName) {
        this.esIp = esIp;
        this.esPort = esPort;
        this.esClusterName = esClusterName;
    }

    public String getEsIp() {
        return esIp;
    }

    public void setEsIp(String esIp) {
        this.esIp = esIp;
    }

    public String getEsPort() {
        return esPort;
    }

    public void setEsPort(String esPort) {
        this.esPort = esPort;
    }

    public String getEsClusterName() {
        return esClusterName;
    }

    public void setEsClusterName(String esClusterName) {
        this.esClusterName = esClusterName;
    }
}
