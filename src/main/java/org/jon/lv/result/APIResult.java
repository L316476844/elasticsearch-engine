package org.jon.lv.result;

import java.io.Serializable;

/**
 * @Description: 调用公共接口返回的结果集
 * Author lv bin
 * @date 2016/12/10 23:13
 * version V1.0.0
 */
public class APIResult<V> implements Serializable{
	
	private static final long serialVersionUID = -7925016012331196255L;
	/**返回状态，
	 * 处理中：100
	 * 成功：200，
	 * 失败：500，
	 * 备用成功：300，(如重复提交，重复执行等，即可返回300，表示已经提交过了)
	 * 其他具体情况自定义 
	 * */
	private int status ;
	/**
	 * 返回具体执行信息
	 */
	private String message;
	/**
	 * V V根据在new APIResult<V>的时候，如果V是存储一个User对象，那么就new APIResult<User>();
	 * 可以达到不用强转，直接Get即可获取。
	 */
	private V result ;

	public APIResult() {
		super();
	}
	public APIResult(int status,String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public APIResult(int status,String message,V result) {
		super();
		this.status = status;
		this.message = message;
		this.result = result ;
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public V getResult() {
		return result;
	}
	public void setResult(V result) {
		this.result = result;
	}
	
}
