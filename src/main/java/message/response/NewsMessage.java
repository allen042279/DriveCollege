package message.response;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文本消息
 * 
 */
@Component
public class NewsMessage extends BaseMessage {
	
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	
	// 多条图文消息信息，默认第一个item为大图
	@Autowired
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}
