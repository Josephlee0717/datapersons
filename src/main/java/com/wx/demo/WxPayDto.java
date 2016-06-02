package com.wx.demo;


public class WxPayDto {

	private String orderId;//¶©µ¥ºÅ
	private String totalFee;//½ð¶î
	private String spbillCreateIp;//¶©µ¥Éú³ÉµÄ»úÆ÷ IP
	private String notifyUrl;//ÕâÀïnotify_urlÊÇ Ö§¸¶Íê³ÉºóÎ¢ÐÅ·¢¸ø¸ÃÁ´½ÓÐÅÏ¢£¬¿ÉÒÔÅÐ¶Ï»áÔ±ÊÇ·ñÖ§¸¶³É¹¦£¬¸Ä±ä¶©µ¥×´Ì¬µÈ
	private String body;// ÉÌÆ·ÃèÊö¸ù¾ÝÇé¿öÐÞ¸Ä
	private String openId;//Î¢ÐÅÓÃ»§¶ÔÒ»¸ö¹«ÖÚºÅÎ¨Ò»
	
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the totalFee
	 */
	public String getTotalFee() {
		return totalFee;
	}
	/**
	 * @param totalFee the totalFee to set
	 */
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * @return the spbillCreateIp
	 */
	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}
	/**
	 * @param spbillCreateIp the spbillCreateIp to set
	 */
	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
	/**
	 * @return the notifyUrl
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}
	/**
	 * @param notifyUrl the notifyUrl to set
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}
