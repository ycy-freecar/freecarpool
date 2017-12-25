package com.ycy.freecarpool.domain;

/**
 * 图片
 * @author wxd
 * @date 2015-09-12
 */
public class Image {
	private String MediaId;//通过素材管理接口上传多媒体文件，得到的id。 

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}
