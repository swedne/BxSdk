package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class SecondHouseInfoEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4845450475196334143L;

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
		private SecondHouse secondHouse;

		/**
		 * 
		 * @return The secondHouse
		 */
		public SecondHouse getSecondHouse() {
			return secondHouse;
		}

		/**
		 * 
		 * @param secondHouse
		 *            The secondHouse
		 */
		public void setSecondHouse(SecondHouse secondHouse) {
			this.secondHouse = secondHouse;
		}

	}

	public class SecondHouse {

		private String houseAuthCode;

		public String getHouseAuthCode() {
			return houseAuthCode;
		}

		public void setHouseAuthCode(String houseAuthCode) {
			this.houseAuthCode = houseAuthCode;
		}

		private int isCollection;

		private String wxUrlAndroid;

		public String getWxUrl() {
			return wxUrlAndroid;
		}

		public void setWxUrl(String wxUrlAndroid) {
			this.wxUrlAndroid = wxUrlAndroid;
		}

		public int getIsCollection() {
			return isCollection;
		}

		public void setIsCollection(int isCollection) {
			this.isCollection = isCollection;
		}

		private List<ImageUrlList> imageUrlList = new ArrayList<ImageUrlList>();

		public List<ImageUrlList> getImageUrlList() {
			return imageUrlList;
		}

		public void setImageUrlList(ArrayList<ImageUrlList> imageUrlList) {
			this.imageUrlList = imageUrlList;
		}

		@Expose
		private String uniqueNo;
		@Expose
		private Double latitude;
		@Expose
		private String businessName;
		@Expose
		private Integer areaListId;
		@Expose
		private String headUrl;
		@Expose
		private String houseResourceDesc;
		@Expose
		private String monthPayment;
		@Expose
		private String title;
		@Expose
		private Integer brokeId;
		@Expose
		private String berryGG;
		@Expose
		private String floorNum;
		@Expose
		private Integer isAuth;
		@Expose
		private Integer roomNum;
		@Expose
		private String areaName;
		@Expose
		private Integer price;
		@Expose
		private Integer downPayment;
		@Expose
		private Integer toiletNum;
		@Expose
		private Object areaSpecialId;
		@Expose
		private Double longitude;
		@Expose
		private Integer balconyNum;
		@Expose
		private Integer isExpert;
		@Expose
		private int salePrice;
		@Expose
		private String buildTime;
		@Expose
		private String mobile;
		@Expose
		private String houseTypeName;
		@Expose
		private String areaListName;
		@Expose
		private String orientationName;
		@Expose
		private String propertyName;
		@Expose
		private List<SpeciltyNameList> speciltyNameList = new ArrayList<SpeciltyNameList>();
		@Expose
		private String decorateDegreeName;
		@Expose
		private String brokerName;
		@Expose
		private Integer hallNum;

		private String realName;

		public void setRealName(String realName) {
			this.realName = realName;
		}

		public String getRealName() {
			return realName;
		}

		/**
		 * 
		 * @return The uniqueNo
		 */
		public String getUniqueNo() {
			return uniqueNo;
		}

		/**
		 * 
		 * @param uniqueNo
		 *            The uniqueNo
		 */
		public void setUniqueNo(String uniqueNo) {
			this.uniqueNo = uniqueNo;
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
		 * @return The areaListId
		 */
		public Integer getAreaListId() {
			return areaListId;
		}

		/**
		 * 
		 * @param areaListId
		 *            The areaListId
		 */
		public void setAreaListId(Integer areaListId) {
			this.areaListId = areaListId;
		}

		/**
		 * 
		 * @return The headUrl
		 */
		public String getHeadUrl() {
			return headUrl;
		}

		/**
		 * 
		 * @param headUrl
		 *            The headUrl
		 */
		public void setHeadUrl(String headUrl) {
			this.headUrl = headUrl;
		}

		/**
		 * 
		 * @return The houseResourceDesc
		 */
		public String getHouseResourceDesc() {
			return houseResourceDesc;
		}

		/**
		 * 
		 * @param houseResourceDesc
		 *            The houseResourceDesc
		 */
		public void setHouseResourceDesc(String houseResourceDesc) {
			this.houseResourceDesc = houseResourceDesc;
		}

		/**
		 * 
		 * @return The monthPayment
		 */
		public String getMonthPayment() {
			return monthPayment;
		}

		/**
		 * 
		 * @param monthPayment
		 *            The monthPayment
		 */
		public void setMonthPayment(String monthPayment) {
			this.monthPayment = monthPayment;
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
		 * @return The brokeId
		 */
		public Integer getBrokeId() {
			return brokeId;
		}

		/**
		 * 
		 * @param brokeId
		 *            The brokeId
		 */
		public void setBrokeId(Integer brokeId) {
			this.brokeId = brokeId;
		}

		/**
		 * 
		 * @return The berryGG
		 */
		public String getBerryGG() {
			return berryGG;
		}

		/**
		 * 
		 * @param berryGG
		 *            The berryGG
		 */
		public void setBerryGG(String berryGG) {
			this.berryGG = berryGG;
		}

		/**
		 * 
		 * @return The floorNum
		 */
		public String getFloorNum() {
			return floorNum;
		}

		/**
		 * 
		 * @param floorNum
		 *            The floorNum
		 */
		public void setFloorNum(String floorNum) {
			this.floorNum = floorNum;
		}

		/**
		 * 
		 * @return The isAuth
		 */
		public Integer getIsAuth() {
			return isAuth;
		}

		/**
		 * 
		 * @param isAuth
		 *            The isAuth
		 */
		public void setIsAuth(Integer isAuth) {
			this.isAuth = isAuth;
		}

		/**
		 * 
		 * @return The roomNum
		 */
		public Integer getRoomNum() {
			return roomNum;
		}

		/**
		 * 
		 * @param roomNum
		 *            The roomNum
		 */
		public void setRoomNum(Integer roomNum) {
			this.roomNum = roomNum;
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
		public Integer getPrice() {
			return price;
		}

		/**
		 * 
		 * @param price
		 *            The price
		 */
		public void setPrice(Integer price) {
			this.price = price;
		}

		/**
		 * 
		 * @return The downPayment
		 */
		public Integer getDownPayment() {
			return downPayment;
		}

		/**
		 * 
		 * @param downPayment
		 *            The downPayment
		 */
		public void setDownPayment(Integer downPayment) {
			this.downPayment = downPayment;
		}

		/**
		 * 
		 * @return The toiletNum
		 */
		public Integer getToiletNum() {
			return toiletNum;
		}

		/**
		 * 
		 * @param toiletNum
		 *            The toiletNum
		 */
		public void setToiletNum(Integer toiletNum) {
			this.toiletNum = toiletNum;
		}

		/**
		 * 
		 * @return The areaSpecialId
		 */
		public Object getAreaSpecialId() {
			return areaSpecialId;
		}

		/**
		 * 
		 * @param areaSpecialId
		 *            The areaSpecialId
		 */
		public void setAreaSpecialId(Object areaSpecialId) {
			this.areaSpecialId = areaSpecialId;
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
		 * @return The balconyNum
		 */
		public Integer getBalconyNum() {
			return balconyNum;
		}

		/**
		 * 
		 * @param balconyNum
		 *            The balconyNum
		 */
		public void setBalconyNum(Integer balconyNum) {
			this.balconyNum = balconyNum;
		}

		/**
		 * 
		 * @return The isExpert
		 */
		public Integer getIsExpert() {
			return isExpert;
		}

		/**
		 * 
		 * @param isExpert
		 *            The isExpert
		 */
		public void setIsExpert(Integer isExpert) {
			this.isExpert = isExpert;
		}

		/**
		 * 
		 * @return The salePrice
		 */
		public int getSalePrice() {
			return salePrice;
		}

		/**
		 * 
		 * @param salePrice
		 *            The salePrice
		 */
		public void setSalePrice(int salePrice) {
			this.salePrice = salePrice;
		}

		/**
		 * 
		 * @return The buildTime
		 */
		public String getBuildTime() {
			return buildTime;
		}

		/**
		 * 
		 * @param buildTime
		 *            The buildTime
		 */
		public void setBuildTime(String buildTime) {
			this.buildTime = buildTime;
		}

		/**
		 * 
		 * @return The mobile
		 */
		public String getMobile() {
			return mobile;
		}

		/**
		 * 
		 * @param mobile
		 *            The mobile
		 */
		public void setMobile(String mobile) {
			this.mobile = mobile;
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
		 * @return The areaListName
		 */
		public String getAreaListName() {
			return areaListName;
		}

		/**
		 * 
		 * @param areaListName
		 *            The areaListName
		 */
		public void setAreaListName(String areaListName) {
			this.areaListName = areaListName;
		}

		/**
		 * 
		 * @return The orientationName
		 */
		public String getOrientationName() {
			return orientationName;
		}

		/**
		 * 
		 * @param orientationName
		 *            The orientationName
		 */
		public void setOrientationName(String orientationName) {
			this.orientationName = orientationName;
		}

		/**
		 * 
		 * @return The propertyName
		 */
		public String getPropertyName() {
			return propertyName;
		}

		/**
		 * 
		 * @param propertyName
		 *            The propertyName
		 */
		public void setPropertyName(String propertyName) {
			this.propertyName = propertyName;
		}

		/**
		 * 
		 * @return The speciltyNameList
		 */
		public List<SpeciltyNameList> getSpeciltyNameList() {
			return speciltyNameList;
		}

		/**
		 * 
		 * @param speciltyNameList
		 *            The speciltyNameList
		 */
		public void setSpeciltyNameList(List<SpeciltyNameList> speciltyNameList) {
			this.speciltyNameList = speciltyNameList;
		}

		/**
		 * 
		 * @return The decorateDegreeName
		 */
		public String getDecorateDegreeName() {
			return decorateDegreeName;
		}

		/**
		 * 
		 * @param decorateDegreeName
		 *            The decorateDegreeName
		 */
		public void setDecorateDegreeName(String decorateDegreeName) {
			this.decorateDegreeName = decorateDegreeName;
		}

		/**
		 * 
		 * @return The brokerName
		 */
		public String getBrokerName() {
			return brokerName;
		}

		/**
		 * 
		 * @param brokerName
		 *            The brokerName
		 */
		public void setBrokerName(String brokerName) {
			this.brokerName = brokerName;
		}

		/**
		 * 
		 * @return The hallNum
		 */
		public Integer getHallNum() {
			return hallNum;
		}

		/**
		 * 
		 * @param hallNum
		 *            The hallNum
		 */
		public void setHallNum(Integer hallNum) {
			this.hallNum = hallNum;
		}

	}

	public class SpeciltyNameList {
		private String specialtyName;

		public String getSpecialtyName() {
			return specialtyName;
		}

		public void setSpecialtyName(String specialtyName) {
			this.specialtyName = specialtyName;
		}

	}

	public class ImageUrlList {
		private String picUrl;
		private int imageId;
		private String surFaceUrl;

		public String getSurFaceUrl() {
			return surFaceUrl;
		}

		public void setSurFaceUrl(String surFaceUrl) {
			this.surFaceUrl = surFaceUrl;
		}

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public int getImageId() {
			return imageId;
		}

		public void setImageId(int imageId) {
			this.imageId = imageId;
		}
	}

}
