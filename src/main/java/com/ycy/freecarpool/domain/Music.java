package com.ycy.freecarpool.domain;

/**
 * 音乐
 * @author wxd
 * @date 2015-09-12
 */
public class Music {
	private String Title;//音乐标题    不是必填 
	private String Description;//音乐描述  不是必填 
	private String MusicUrl;//音乐链接   不是必填 
	private String HQMusicUrl;//高质量音乐链接，WIFI环境优先使用该链接播放音乐   不是必填 
	private String ThumbMediaId;//缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id   必填 
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getMusicUrl() {
		return MusicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	
	
}
