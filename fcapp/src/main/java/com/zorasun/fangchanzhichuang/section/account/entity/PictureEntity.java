package com.zorasun.fangchanzhichuang.section.account.entity;

import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class PictureEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Content content;

	public String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return this.content;
	}

	public class Content {
		public PublicUser publicUser;
	}

	public class PublicUser {
		private String address;

		private int field;

		private int id;

		private int width;
		private int height;

		@Override
		public String toString() {
			return "Content [address=" + address + ", field=" + field + ", id=" + id + ", width=" + width + ", height="
					+ height + "]";
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getAddress() {
			return this.address;
		}

		public void setField(int field) {
			this.field = field;
		}

		public int getField() {
			return this.field;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getId() {
			return this.id;
		}

	}
}
