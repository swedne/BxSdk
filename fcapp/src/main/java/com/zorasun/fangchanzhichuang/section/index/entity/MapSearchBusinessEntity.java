package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class MapSearchBusinessEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 857780437440471312L;

	private Content content;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public class Content {
		private List<AreaList> areaList = new ArrayList<>();

		private List<XiamenInitList> xiamenInitList = new ArrayList<>();

		public List<XiamenInitList> getXiamenInitList() {
			return xiamenInitList;
		}

		public void setXiamenInitList(List<XiamenInitList> xiamenInitList) {
			this.xiamenInitList = xiamenInitList;
		}

		public List<AreaList> getAreaList() {
			return areaList;
		}

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

	public class XiamenInitList {
		private int areaId;
		private String areaName;
		private double latitude;
		private double longitude;
		private int houseNumByArea;
		private int newHouseNumByArea;

		public int getNewHouseNumByArea() {
			return newHouseNumByArea;
		}

		public void setNewHouseNumByArea(int newHouseNumByArea) {
			this.newHouseNumByArea = newHouseNumByArea;
		}

		public void setAreaId(int areaId) {
			this.areaId = areaId;
		}

		public int getAreaId() {
			return areaId;
		}

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}

		public int getHouseNumByArea() {
			return houseNumByArea;
		}

		public void setHouseNumByArea(int houseNumByArea) {
			this.houseNumByArea = houseNumByArea;
		}
	}
}
