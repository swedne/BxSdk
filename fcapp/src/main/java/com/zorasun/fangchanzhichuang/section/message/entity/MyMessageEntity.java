package com.zorasun.fangchanzhichuang.section.message.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class MyMessageEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7144987089918087578L;

	@Expose
	private Content content;

	/**
	 * 
	 * @return The content
	 */
	public Content getContent() {
		return content;
	}

	/**
	 * 
	 * @param content
	 *            The content
	 */
	public void setContent(Content content) {
		this.content = content;
	}

	public class Content {

		// 公告链接
		private String messageUrl;

		private AnnouncementList announcementList;

		public AnnouncementList getAnnouncementList() {
			return announcementList;
		}

		public void setAnnouncementList(AnnouncementList announcementList) {
			this.announcementList = announcementList;
		}

		public String getMessageUrl() {
			return messageUrl;
		}

		public void setMessageUrl(String messageUrl) {
			this.messageUrl = messageUrl;
		}

		@Expose
		private List<AdvicesAll> advicesAll = new ArrayList<AdvicesAll>();
		@Expose
		private Integer pageCount;
		@Expose
		private Integer type;

		/**
		 * 
		 * @return The advicesAll
		 */
		public List<AdvicesAll> getAdvicesAll() {
			return advicesAll;
		}

		/**
		 * 
		 * @param advicesAll
		 *            The advicesAll
		 */
		public void setAdvicesAll(List<AdvicesAll> advicesAll) {
			this.advicesAll = advicesAll;
		}

		/**
		 * 
		 * @return The pageCount
		 */
		public Integer getPageCount() {
			return pageCount;
		}

		/**
		 * 
		 * @param pageCount
		 *            The pageCount
		 */
		public void setPageCount(Integer pageCount) {
			this.pageCount = pageCount;
		}

		/**
		 * 
		 * @return The type
		 */
		public Integer getType() {
			return type;
		}

		/**
		 * 
		 * @param type
		 *            The type
		 */
		public void setType(Integer type) {
			this.type = type;
		}

	}

	public class Mymsg {

		@Expose
		private Integer code;
		@Expose
		private String msg;
		@Expose
		private Content content;

		/**
		 * 
		 * @return The code
		 */
		public Integer getCode() {
			return code;
		}

		/**
		 * 
		 * @param code
		 *            The code
		 */
		public void setCode(Integer code) {
			this.code = code;
		}

		/**
		 * 
		 * @return The msg
		 */
		public String getMsg() {
			return msg;
		}

		/**
		 * 
		 * @param msg
		 *            The msg
		 */
		public void setMsg(String msg) {
			this.msg = msg;
		}

		/**
		 * 
		 * @return The content
		 */
		public Content getContent() {
			return content;
		}

		/**
		 * 
		 * @param content
		 *            The content
		 */
		public void setContent(Content content) {
			this.content = content;
		}

	}

	public class AnnouncementList {
		private long announcementId;
		private String announcementTitle;
		private int isReaded;
		private long announcementTime;

		public long getAnnouncementId() {
			return announcementId;
		}

		public void setAnnouncementId(long announcementId) {
			this.announcementId = announcementId;
		}

		public String getAnnouncementTitle() {
			return announcementTitle;
		}

		public void setAnnouncementTitle(String announcementTitle) {
			this.announcementTitle = announcementTitle;
		}

		public int getIsReaded() {
			return isReaded;
		}

		public void setIsReaded(int isReaded) {
			this.isReaded = isReaded;
		}

		public long getAnnouncementTime() {
			return announcementTime;
		}

		public void setAnnouncementTime(long announcementTime) {
			this.announcementTime = announcementTime;
		}

	}

	public class AdvicesAll {

		@Expose
		private String picUrl;
		@Expose
		private String msgType;
		@Expose
		private Integer advicesId;
		@Expose
		private long advicesTime;
		@Expose
		private Integer isRead;
		@Expose
		private String msgId;
		@Expose
		private String type;
		@Expose
		private String title;
		@Expose
		private String content;

		/**
		 * 
		 * @return The picUrl
		 */
		public String getPicUrl() {
			return picUrl;
		}

		/**
		 * 
		 * @param picUrl
		 *            The picUrl
		 */
		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		/**
		 * 
		 * @return The msgType
		 */
		public String getMsgType() {
			return msgType;
		}

		/**
		 * 
		 * @param msgType
		 *            The msgType
		 */
		public void setMsgType(String msgType) {
			this.msgType = msgType;
		}

		/**
		 * 
		 * @return The advicesId
		 */
		public Integer getAdvicesId() {
			return advicesId;
		}

		/**
		 * 
		 * @param advicesId
		 *            The advicesId
		 */
		public void setAdvicesId(Integer advicesId) {
			this.advicesId = advicesId;
		}

		/**
		 * 
		 * @return The advicesTime
		 */
		public long getAdvicesTime() {
			return advicesTime;
		}

		/**
		 * 
		 * @param advicesTime
		 *            The advicesTime
		 */
		public void setAdvicesTime(long advicesTime) {
			this.advicesTime = advicesTime;
		}

		/**
		 * 
		 * @return The isRead
		 */
		public Integer getIsRead() {
			return isRead;
		}

		/**
		 * 
		 * @param isRead
		 *            The isRead
		 */
		public void setIsRead(Integer isRead) {
			this.isRead = isRead;
		}

		/**
		 * 
		 * @return The msgId
		 */
		public String getMsgId() {
			return msgId;
		}

		/**
		 * 
		 * @param msgId
		 *            The msgId
		 */
		public void setMsgId(String msgId) {
			this.msgId = msgId;
		}

		/**
		 * 
		 * @return The type
		 */
		public String getType() {
			return type;
		}

		/**
		 * 
		 * @param type
		 *            The type
		 */
		public void setType(String type) {
			this.type = type;
		}

		/**
		 * 
		 * @return The title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * 
		 * @param title
		 *            The title
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * 
		 * @return The content
		 */
		public String getContent() {
			return content;
		}

		/**
		 * 
		 * @param content
		 *            The content
		 */
		public void setContent(String content) {
			this.content = content;
		}

	}

}
