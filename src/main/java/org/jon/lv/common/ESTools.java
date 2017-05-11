package org.jon.lv.common;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.collect.HppcMaps;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.plugin.deletebyquery.DeleteByQueryPlugin;
import org.elasticsearch.plugin.nlpcn.SqlPlug;
import org.jon.lv.utils.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * @Description: ES 创建客户端
 * Author lv bin
 * @date 2016/12/11 21:07
 * version V1.0.0
 */
public class ESTools {

	public static ESConfig esConfig;

	public static  Client client = null;

	/**
	 * 创建ElasticSearch 客户端(TCP协议)
	 * @return
	 */
	private static Client buildClient(){
		String ip = esConfig.getEsIp();
		String port = esConfig.getEsPort();
		String clusterName = esConfig.getEsClusterName();
		String message = "当前ES获取的参数:ip[%s],port[%s],es.cluster.name[%s]";
		Logger.debug(ESTools.class, String.format(message, ip, port, clusterName));

		/**
		 * "client.transport.sniff",true : 自动查找当前同一网段的相同节点为集群
		 */
		Settings settings = Settings.settingsBuilder().put("client.transport.sniff", true)
							.put("cluster.name", clusterName).build();
		try {
			// 根据查询删除插件 - sql插件
			client = TransportClient.builder().settings(settings).addPlugin(DeleteByQueryPlugin.class)
					 .addPlugin(SqlPlug.class).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), Integer.parseInt(port)));
		} catch (UnknownHostException e) {
			Logger.error(ESTools.class, e.getMessage());
		}
//		Client client = new TransportClient(ImmutableSettings.settingsBuilder()
//				.put("client.transport.sniff",true)
//					.put("cluster.name", clusterName)
//						.build())
//							.addTransportAddress(new InetSocketTransportAddress(ip,Integer.parseInt(host)));
		return client;
	}

	public static void closeClient() {
		if (client != null) {
			try {
				client.close();
			} catch (Exception e) {
			}
			client = null;
		}
	}

	public static void setEsConfig(ESConfig esConfig) {
		ESTools.esConfig = esConfig;
		client = buildClient();
	}
}
