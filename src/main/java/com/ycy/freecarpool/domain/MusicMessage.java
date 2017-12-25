package com.ycy.freecarpool.domain;

/**
 * 回复音乐消息
 * @author wxd
 * @date 2015-09-12
 */
public class MusicMessage extends BaseMessage {
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
	
	
}
