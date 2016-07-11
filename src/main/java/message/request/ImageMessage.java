package message.request;

import org.springframework.stereotype.Component;


/**
 * 图片消息
 * 
 */
@Component
public class ImageMessage extends BaseMessage {
	
	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}

