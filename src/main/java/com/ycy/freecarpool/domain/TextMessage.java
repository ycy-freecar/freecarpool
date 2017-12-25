package com.ycy.freecarpool.domain;

/**
 * 文本消息
 * @author wxd
 * @date 2015-09-12
 *
 */
public class TextMessage extends BaseMessage {
	private String Content;//文本消息内容 
	private String MsgId; //消息id 64位整型
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
}
