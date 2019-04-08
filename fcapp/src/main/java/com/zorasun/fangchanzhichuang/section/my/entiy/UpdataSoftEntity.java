package com.zorasun.fangchanzhichuang.section.my.entiy;

import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class UpdataSoftEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Content content;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public class Content {
		private String url;
		private int isMustUpdate;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public int getIsMustUpdate() {
			return isMustUpdate;
		}

		public void setIsMustUpdate(int isMustUpdate) {
			this.isMustUpdate = isMustUpdate;
		}
	}
}
