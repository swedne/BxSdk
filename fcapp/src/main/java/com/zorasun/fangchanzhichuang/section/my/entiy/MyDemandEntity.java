package com.zorasun.fangchanzhichuang.section.my.entiy;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class MyDemandEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6181611773789810968L;

	private Content content;

	public void setContent(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return this.content;
	}

	public class Content {

		@Expose
		private String condition;
		@Expose
		private Integer totalPage;
		@Expose
		private Integer pageNum;
		@Expose
		private List<DemandList> demandList = new ArrayList<DemandList>();

		/**
		 * 
		 * @return The condition
		 */
		public String getCondition() {
			return condition;
		}

		/**
		 * 
		 * @param condition
		 *            The condition
		 */
		public void setCondition(String condition) {
			this.condition = condition;
		}

		/**
		 * 
		 * @return The totalPage
		 */
		public Integer getTotalPage() {
			return totalPage;
		}

		/**
		 * 
		 * @param totalPage
		 *            The totalPage
		 */
		public void setTotalPage(Integer totalPage) {
			this.totalPage = totalPage;
		}

		/**
		 * 
		 * @return The pageNum
		 */
		public Integer getPageNum() {
			return pageNum;
		}

		/**
		 * 
		 * @param pageNum
		 *            The pageNum
		 */
		public void setPageNum(Integer pageNum) {
			this.pageNum = pageNum;
		}

		/**
		 * 
		 * @return The demandList
		 */
		public List<DemandList> getDemandList() {
			return demandList;
		}

		/**
		 * 
		 * @param demandList
		 *            The demandList
		 */
		public void setDemandList(List<DemandList> demandList) {
			this.demandList = demandList;
		}

	}

	public class DemandList {

		@Expose
		private String demandTypeName;
		@Expose
		private String square;
		@Expose
		private Integer demandId;
		@Expose
		private String areaName;
		@Expose
		private String price;
		@Expose
		private Integer demandTypeId;
		@Expose
		private String businessName;
		@Expose
		private String houseTypeName;
		@Expose
		private Long createdTime;
		@Expose
		private Object resourceName;
		@Expose
		private Integer state;
		@Expose
		private String uniqueNum;

		/**
		 * 
		 * @return The demandTypeName
		 */
		public String getDemandTypeName() {
			return demandTypeName;
		}

		/**
		 * 
		 * @param demandTypeName
		 *            The demandTypeName
		 */
		public void setDemandTypeName(String demandTypeName) {
			this.demandTypeName = demandTypeName;
		}

		/**
		 * 
		 * @return The square
		 */
		public String getSquare() {
			return square;
		}

		/**
		 * 
		 * @param square
		 *            The square
		 */
		public void setSquare(String square) {
			this.square = square;
		}

		/**
		 * 
		 * @return The demandId
		 */
		public Integer getDemandId() {
			return demandId;
		}

		/**
		 * 
		 * @param demandId
		 *            The demandId
		 */
		public void setDemandId(Integer demandId) {
			this.demandId = demandId;
		}

		/**
		 * 
		 * @return The areaName
		 */
		public String getAreaName() {
			return areaName;
		}

		/**
		 * 
		 * @param areaName
		 *            The areaName
		 */
		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		/**
		 * 
		 * @return The price
		 */
		public String getPrice() {
			return price;
		}

		/**
		 * 
		 * @param price
		 *            The price
		 */
		public void setPrice(String price) {
			this.price = price;
		}

		/**
		 * 
		 * @return The demandTypeId
		 */
		public Integer getDemandTypeId() {
			return demandTypeId;
		}

		/**
		 * 
		 * @param demandTypeId
		 *            The demandTypeId
		 */
		public void setDemandTypeId(Integer demandTypeId) {
			this.demandTypeId = demandTypeId;
		}

		/**
		 * 
		 * @return The businessName
		 */
		public String getBusinessName() {
			return businessName;
		}

		/**
		 * 
		 * @param businessName
		 *            The businessName
		 */
		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

		/**
		 * 
		 * @return The houseTypeName
		 */
		public String getHouseTypeName() {
			return houseTypeName;
		}

		/**
		 * 
		 * @param houseTypeName
		 *            The houseTypeName
		 */
		public void setHouseTypeName(String houseTypeName) {
			this.houseTypeName = houseTypeName;
		}

		/**
		 * 
		 * @return The createdTime
		 */
		public Long getCreatedTime() {
			return createdTime;
		}

		/**
		 * 
		 * @param createdTime
		 *            The createdTime
		 */
		public void setCreatedTime(Long createdTime) {
			this.createdTime = createdTime;
		}

		/**
		 * 
		 * @return The resourceName
		 */
		public Object getResourceName() {
			return resourceName;
		}

		/**
		 * 
		 * @param resourceName
		 *            The resourceName
		 */
		public void setResourceName(Object resourceName) {
			this.resourceName = resourceName;
		}

		/**
		 * 
		 * @return The state
		 */
		public Integer getState() {
			return state;
		}

		/**
		 * 
		 * @param state
		 *            The state
		 */
		public void setState(Integer state) {
			this.state = state;
		}

		/**
		 * 
		 * @return The uniqueNum
		 */
		public String getUniqueNum() {
			return uniqueNum;
		}

		/**
		 * 
		 * @param uniqueNum
		 *            The uniqueNum
		 */
		public void setUniqueNum(String uniqueNum) {
			this.uniqueNum = uniqueNum;
		}

	}

	public class Mydemand {

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

}
