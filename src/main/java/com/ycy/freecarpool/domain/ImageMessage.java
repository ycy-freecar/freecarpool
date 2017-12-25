package com.ycy.freecarpool.domain;

/**
 * 图片消息
 * @author wxd
 * @date 2015-09-12
 */
public class ImageMessage extends BaseMessage {
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
	
}
