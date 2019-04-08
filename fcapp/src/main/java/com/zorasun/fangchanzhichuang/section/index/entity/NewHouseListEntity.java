package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class NewHouseListEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5520615486917112523L;
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
		private List<NewHouseList_> newHouseList = new ArrayList<NewHouseList_>();
		@Expose
		private Integer pageCount;
		@Expose
		private List<AreaList> areaList = new ArrayList<AreaList>();

		/**
		 * 
		 * @return The newHouseList
		 */
		public List<NewHouseList_> getNewHouseList() {
			return newHouseList;
		}

		/**
		 * 
		 * @param newHouseList
		 *            The newHouseList
		 */
		public void setNewHouseList(List<NewHouseList_> newHouseList) {
			this.newHouseList = newHouseList;
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
		 * @return The areaList
		 */
		public List<AreaList> getAreaList() {
			return areaList;
		}

		/**
		 * 
		 * @param areaList
		 *            The areaList
		 */
		public void setAreaList(List<AreaList> areaList) {
			this.areaList = areaList;
		}

	}

	public class AreaList {

		@Expose
		private Integer areaId;
		@Expose
		private String areaName;
		@Expose
		private String letter;
		@Expose
		private String businessName;
		@Expose
		private Integer sign;
		@Expose
		private Integer businessListId;

		/**
		 * 
		 * @return The areaId
		 */
		public Integer getAreaId() {
			return areaId;
		}

		/**
		 * 
		 * @param areaId
		 *            The areaId
		 */
		public void setAreaId(Integer areaId) {
			this.areaId = areaId;
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
		 * @return The letter
		 */
		public String getLetter() {
			return letter;
		}

		/**
		 * 
		 * @param letter
		 *            The letter
		 */
		public void setLetter(String letter) {
			this.letter = letter;
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
		 * @return The sign
		 */
		public Integer getSign() {
			return sign;
		}

		/**
		 * 
		 * @param sign
		 *            The sign
		 */
		public void setSign(Integer sign) {
			this.sign = sign;
		}

		/**
		 * 
		 * @return The businessListId
		 */
		public Integer getBusinessListId() {
			return businessListId;
		}

		/**
		 * 
		 * @param businessListId
		 *            The businessListId
		 */
		public void setBusinessListId(Integer businessListId) {
			this.businessListId = businessListId;
		}

	}

	public class SpecialsList {

		@Expose
		private String specialName;
		@Expose
		private Integer specialId;

		/**
		 * 
		 * @return The specialName
		 */
		public String getSpecialName() {
			return specialName;
		}

		/**
		 * 
		 * @param specialName
		 *            The specialName
		 */
		public void setSpecialName(String specialName) {
			this.specialName = specialName;
		}

		/**
		 * 
		 * @return The specialId
		 */
		public Integer getSpecialId() {
			return specialId;
		}

		/**
		 * 
		 * @param specialId
		 *            The specialId
		 */
		public void setSpecialId(Integer specialId) {
			this.specialId = specialId;
		}

	}

	public class NewHouseList_ {

		@Expose
		private int average;
		@Expose
		private String projectUrl;
		@Expose
		private String newhouseName;
		@Expose
		private String hostHouseType;
		@Expose
		private Double latitude;
		@Expose
		private String businessName;
		@Expose
		private Integer newhouseId;
		@Expose
		private Integer businessListId;
		@Expose
		private String url;
		@Expose
		private Integer specialId;
		@Expose
		private Integer areaId;
		@Expose
		private String areaName;
		@Expose
		private Double longitude;
		@Expose
		private List<SpecialsList> specialsList = new ArrayList<SpecialsList>();

		/**
		 * 
		 * @return The average
		 */
		public int getAverage() {
			return average;
		}

		/**
		 * 
		 * @param average
		 *            The average
		 */
		public void setAverage(int average) {
			this.average = average;
		}

		/**
		 * 
		 * @return The projectUrl
		 */
		public String getProjectUrl() {
			return projectUrl;
		}

		/**
		 * 
		 * @param projectUrl
		 *            The projectUrl
		 */
		public void setProjectUrl(String projectUrl) {
			this.projectUrl = projectUrl;
		}

		/**
		 * 
		 * @return The newhouseName
		 */
		public String getNewhouseName() {
			return newhouseName;
		}

		/**
		 * 
		 * @param newhouseName
		 *            The newhouseName
		 */
		public void setNewhouseName(String newhouseName) {
			this.newhouseName = newhouseName;
		}

		/**
		 * 
		 * @return The hostHouseType
		 */
		public String getHostHouseType() {
			return hostHouseType;
		}

		/**
		 * 
		 * @param hostHouseType
		 *            The hostHouseType
		 */
		public void setHostHouseType(String hostHouseType) {
			this.hostHouseType = hostHouseType;
		}

		/**
		 * 
		 * @return The latitude
		 */
		public Double getLatitude() {
			return latitude;
		}

		/**
		 * 
		 * @param latitude
		 *            The latitude
		 */
		public void setLatitude(Double latitude) {
			this.latitude = latitude;
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
		 * @return The newhouseId
		 */
		public Integer getNewhouseId() {
			return newhouseId;
		}

		/**
		 * 
		 * @param newhouseId
		 *            The newhouseId
		 */
		public void setNewhouseId(Integer newhouseId) {
			this.newhouseId = newhouseId;
		}

		/**
		 * 
		 * @return The businessListId
		 */
		public Integer getBusinessListId() {
			return businessListId;
		}

		/**
		 * 
		 * @param businessListId
		 *            The businessListId
		 */
		public void setBusinessListId(Integer businessListId) {
			this.businessListId = businessListId;
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
		 * @return The specialId
		 */
		public Integer getSpecialId() {
			return specialId;
		}

		/**
		 * 
		 * @param specialId
		 *            The specialId
		 */
		public void setSpecialId(Integer specialId) {
			this.specialId = specialId;
		}

		/**
		 * 
		 * @return The areaId
		 */
		public Integer getAreaId() {
			return areaId;
		}

		/**
		 * 
		 * @param areaId
		 *            The areaId
		 */
		public void setAreaId(Integer areaId) {
			this.areaId = areaId;
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
		 * @return The longitude
		 */
		public Double getLongitude() {
			return longitude;
		}

		/**
		 * 
		 * @param longitude
		 *            The longitude
		 */
		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}

		/**
		 * 
		 * @return The specialsList
		 */
		public List<SpecialsList> getSpecialsList() {
			return specialsList;
		}

		/**
		 * 
		 * @param specialsList
		 *            The specialsList
		 */
		public void setSpecialsList(List<SpecialsList> specialsList) {
			this.specialsList = specialsList;
		}

	}

}
