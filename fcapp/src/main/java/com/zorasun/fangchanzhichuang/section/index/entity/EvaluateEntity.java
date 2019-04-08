package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class EvaluateEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3261796592471958855L;

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

		@Expose
		private List<AppraiseList> appraiseList = new ArrayList<AppraiseList>();
		@Expose
		private Integer pageCount;
		@Expose
		private Double professional;
		@Expose
		private Double service;
		@Expose
		private Double validity;

		private String brokerName;
		private String headUrl;

		public String getBrokerName() {
			return brokerName;
		}

		public void setBrokerName(String brokerName) {
			this.brokerName = brokerName;
		}

		public String getHeadUrl() {
			return headUrl;
		}

		public void setHeadUrl(String headUrl) {
			this.headUrl = headUrl;
		}

		/**
		 * 
		 * @return The appraiseList
		 */
		public List<AppraiseList> getAppraiseList() {
			return appraiseList;
		}

		/**
		 * 
		 * @param appraiseList
		 *            The appraiseList
		 */
		public void setAppraiseList(List<AppraiseList> appraiseList) {
			this.appraiseList = appraiseList;
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
		 * @return The professional
		 */
		public Double getProfessional() {
			return professional;
		}

		/**
		 * 
		 * @param professional
		 *            The professional
		 */
		public void setProfessional(Double professional) {
			this.professional = professional;
		}

		/**
		 * 
		 * @return The service
		 */
		public Double getService() {
			return service;
		}

		/**
		 * 
		 * @param service
		 *            The service
		 */
		public void setService(Double service) {
			this.service = service;
		}

		/**
		 * 
		 * @return The validity
		 */
		public Double getValidity() {
			return validity;
		}

		/**
		 * 
		 * @param validity
		 *            The validity
		 */
		public void setValidity(Double validity) {
			this.validity = validity;
		}

	}

	public class AppraiseList {

		@Expose
		private Integer publicUser;
		@Expose
		private String appraiseTime;
		@Expose
		private Integer service;
		@Expose
		private String name;
		@Expose
		private String appraiseContent;
		@Expose
		private Integer validity;
		@Expose
		private String url;
		@Expose
		private Integer professional;

		/**
		 * 
		 * @return The publicUser
		 */
		public Integer getPublicUser() {
			return publicUser;
		}

		/**
		 * 
		 * @param publicUser
		 *            The publicUser
		 */
		public void setPublicUser(Integer publicUser) {
			this.publicUser = publicUser;
		}

		/**
		 * 
		 * @return The appraiseTime
		 */
		public String getAppraiseTime() {
			return appraiseTime;
		}

		/**
		 * 
		 * @param appraiseTime
		 *            The appraiseTime
		 */
		public void setAppraiseTime(String appraiseTime) {
			this.appraiseTime = appraiseTime;
		}

		/**
		 * 
		 * @return The service
		 */
		public Integer getService() {
			return service;
		}

		/**
		 * 
		 * @param service
		 *            The service
		 */
		public void setService(Integer service) {
			this.service = service;
		}

		/**
		 * 
		 * @return The name
		 */
		public String getName() {
			return name;
		}

		/**
		 * 
		 * @param name
		 *            The name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 
		 * @return The appraiseContent
		 */
		public String getAppraiseContent() {
			return appraiseContent;
		}

		/**
		 * 
		 * @param appraiseContent
		 *            The appraiseContent
		 */
		public void setAppraiseContent(String appraiseContent) {
			this.appraiseContent = appraiseContent;
		}

		/**
		 * 
		 * @return The validity
		 */
		public Integer getValidity() {
			return validity;
		}

		/**
		 * 
		 * @param validity
		 *            The validity
		 */
		public void setValidity(Integer validity) {
			this.validity = validity;
		}

		/**
		 * 
		 * @return The url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * 
		 * @param url
		 *            The url
		 */
		public void setUrl(String url) {
			this.url = url;
		}

		/**
		 * 
		 * @return The professional
		 */
		public Integer getProfessional() {
			return professional;
		}

		/**
		 * 
		 * @param professional
		 *            The professional
		 */
		public void setProfessional(Integer professional) {
			this.professional = professional;
		}

	}

}
