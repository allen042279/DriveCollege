package message.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 音乐消息
 * 
 */
@Component
public class MusicMessage extends BaseMessage {
	
	// 音乐
	@Autowired
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
