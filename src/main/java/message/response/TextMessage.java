package message.response;

import org.springframework.stereotype.Component;

/**
 * 文本消息
 * 
 */
@Component
public class TextMessage extends BaseMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
