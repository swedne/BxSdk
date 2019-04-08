package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class RentHouseListEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2693477513710040511L;

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
		private Integer pageCount;
		@Expose
		private List<RentHouseList_> rentHouseList = new ArrayList<RentHouseList_>();
		@Expose
		private List<AreaList> areaList = new ArrayList<AreaList>();

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
		 * @return The rentHouseList
		 */
		public List<RentHouseList_> getRentHouseList() {
			return rentHouseList;
		}

		/**
		 * 
		 * @param rentHouseList
		 *            The rentHouseList
		 */
		public void setRentHouseList(List<RentHouseList_> rentHouseList) {
			this.rentHouseList = rentHouseList;
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

	public class RentHouseList_ {

		@Expose
		private Object houseAuthCode;
		@Expose
		private String houseResourceName;
		@Expose
		private Integer isShowImage;
		@Expose
		private Double latitude;
		@Expose
		private String typeName;
		@Expose
		private Object businessName;
		@Expose
		private Integer areaListId;
		@Expose
		private Integer floorSum;
		@Expose
		private String title;
		@Expose
		private Integer houseTypeId;
		@Expose
		private String berryGG;
		@Expose
		private Integer floorNum;
		@Expose
		private String picUrl;
		@Expose
		private Integer isAuth;
		@Expose
		private String payType;
		@Expose
		private String roomNum;
		@Expose
		private String areaName;
		@Expose
		private Integer id;
		@Expose
		private Double longitude;
		@Expose
		private Object houseBarCode;
		@Expose
		private Object firstPay;
		@Expose
		private String address;
		@Expose
		private String officeTypeName;
		@Expose
		private String buildTime;
		@Expose
		private Object salePrice;
		@Expose
		private String rentMoney;
		@Expose
		private String levelName;
		@Expose
		private String degreeName;
		@Expose
		private String areaListName;
		@Expose
		private List<RentHouseSpecialtyList> rentHouseSpecialtyList = new ArrayList<RentHouseSpecialtyList>();
		@Expose
		private Object orientationName;
		@Expose
		private Object plantArea;
		@Expose
		private Object shopType;
		@Expose
		private String hallNum;

		private String surFaceUrl;

		public String getSurFaceUrl() {
			return surFaceUrl;
		}

		public void setSurFaceUrl(String surFaceUrl) {
			this.surFaceUrl = surFaceUrl;
		}

		/**
		 * 
		 * @return The houseAuthCode
		 */
		public Object getHouseAuthCode() {
			return houseAuthCode;
		}

		/**
		 * 
		 * @param houseAuthCode
		 *            The houseAuthCode
		 */
		public void setHouseAuthCode(Object houseAuthCode) {
			this.houseAuthCode = houseAuthCode;
		}

		/**
		 * 
		 * @return The houseResourceName
		 */
		public String getHouseResourceName() {
			return houseResourceName;
		}

		/**
		 * 
		 * @param houseResourceName
		 *            The houseResourceName
		 */
		public void setHouseResourceName(String houseResourceName) {
			this.houseResourceName = houseResourceName;
		}

		/**
		 * 
		 * @return The isShowImage
		 */
		public Integer getIsShowImage() {
			return isShowImage;
		}

		/**
		 * 
		 * @param isShowImage
		 *            The isShowImage
		 */
		public void setIsShowImage(Integer isShowImage) {
			this.isShowImage = isShowImage;
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
		 * @return The typeName
		 */
		public String getTypeName() {
			return typeName;
		}

		/**
		 * 
		 * @param typeName
		 *            The typeName
		 */
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		/**
		 * 
		 * @return The businessName
		 */
		public Object getBusinessName() {
			return businessName;
		}

		/**
		 * 
		 * @param businessName
		 *            The businessName
		 */
		public void setBusinessName(Object businessName) {
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
		 * @return The floorSum
		 */
		public Integer getFloorSum() {
			return floorSum;
		}

		/**
		 * 
		 * @param floorSum
		 *            The floorSum
		 */
		public void setFloorSum(Integer floorSum) {
			this.floorSum = floorSum;
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
		 * @return The houseTypeId
		 */
		public Integer getHouseTypeId() {
			return houseTypeId;
		}

		/**
		 * 
		 * @param houseTypeId
		 *            The houseTypeId
		 */
		public void setHouseTypeId(Integer houseTypeId) {
			this.houseTypeId = houseTypeId;
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
		public Integer getFloorNum() {
			return floorNum;
		}

		/**
		 * 
		 * @param floorNum
		 *            The floorNum
		 */
		public void setFloorNum(Integer floorNum) {
			this.floorNum = floorNum;
		}

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
		 * @return The payType
		 */
		public String getPayType() {
			return payType;
		}

		/**
		 * 
		 * @param payType
		 *            The payType
		 */
		public void setPayType(String payType) {
			this.payType = payType;
		}

		/**
		 * 
		 * @return The roomNum
		 */
		public String getRoomNum() {
			return roomNum;
		}

		/**
		 * 
		 * @param roomNum
		 *            The roomNum
		 */
		public void setRoomNum(String roomNum) {
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
		 * @return The id
		 */
		public Integer getId() {
			return id;
		}

		/**
		 * 
		 * @param id
		 *            The id
		 */
		public void setId(Integer id) {
			this.id = id;
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
		 * @return The houseBarCode
		 */
		public Object getHouseBarCode() {
			return houseBarCode;
		}

		/**
		 * 
		 * @param houseBarCode
		 *            The houseBarCode
		 */
		public void setHouseBarCode(Object houseBarCode) {
			this.houseBarCode = houseBarCode;
		}

		/**
		 * 
		 * @return The firstPay
		 */
		public Object getFirstPay() {
			return firstPay;
		}

		/**
		 * 
		 * @param firstPay
		 *            The firstPay
		 */
		public void setFirstPay(Object firstPay) {
			this.firstPay = firstPay;
		}

		/**
		 * 
		 * @return The address
		 */
		public String getAddress() {
			return address;
		}

		/**
		 * 
		 * @param address
		 *            The address
		 */
		public void setAddress(String address) {
			this.address = address;
		}

		/**
		 * 
		 * @return The officeTypeName
		 */
		public String getOfficeTypeName() {
			return officeTypeName;
		}

		/**
		 * 
		 * @param officeTypeName
		 *            The officeTypeName
		 */
		public void setOfficeTypeName(String officeTypeName) {
			this.officeTypeName = officeTypeName;
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
		 * @return The salePrice
		 */
		public Object getSalePrice() {
			return salePrice;
		}

		/**
		 * 
		 * @param salePrice
		 *            The salePrice
		 */
		public void setSalePrice(Object salePrice) {
			this.salePrice = salePrice;
		}

		/**
		 * 
		 * @return The rentMoney
		 */
		public String getRentMoney() {
			return rentMoney;
		}

		/**
		 * 
		 * @param rentMoney
		 *            The rentMoney
		 */
		public void setRentMoney(String rentMoney) {
			this.rentMoney = rentMoney;
		}

		/**
		 * 
		 * @return The levelName
		 */
		public String getLevelName() {
			return levelName;
		}

		/**
		 * 
		 * @param levelName
		 *            The levelName
		 */
		public void setLevelName(String levelName) {
			this.levelName = levelName;
		}

		/**
		 * 
		 * @return The degreeName
		 */
		public String getDegreeName() {
			return degreeName;
		}

		/**
		 * 
		 * @param degreeName
		 *            The degreeName
		 */
		public void setDegreeName(String degreeName) {
			this.degreeName = degreeName;
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
		 * @return The rentHouseSpecialtyList
		 */
		public List<RentHouseSpecialtyList> getRentHouseSpecialtyList() {
			return rentHouseSpecialtyList;
		}

		/**
		 * 
		 * @param rentHouseSpecialtyList
		 *            The rentHouseSpecialtyList
		 */
		public void setRentHouseSpecialtyList(List<RentHouseSpecialtyList> rentHouseSpecialtyList) {
			this.rentHouseSpecialtyList = rentHouseSpecialtyList;
		}

		/**
		 * 
		 * @return The orientationName
		 */
		public Object getOrientationName() {
			return orientationName;
		}

		/**
		 * 
		 * @param orientationName
		 *            The orientationName
		 */
		public void setOrientationName(Object orientationName) {
			this.orientationName = orientationName;
		}

		/**
		 * 
		 * @return The plantArea
		 */
		public Object getPlantArea() {
			return plantArea;
		}

		/**
		 * 
		 * @param plantArea
		 *            The plantArea
		 */
		public void setPlantArea(Object plantArea) {
			this.plantArea = plantArea;
		}

		/**
		 * 
		 * @return The shopType
		 */
		public Object getShopType() {
			return shopType;
		}

		/**
		 * 
		 * @param shopType
		 *            The shopType
		 */
		public void setShopType(Object shopType) {
			this.shopType = shopType;
		}

		/**
		 * 
		 * @return The hallNum
		 */
		public String getHallNum() {
			return hallNum;
		}

		/**
		 * 
		 * @param hallNum
		 *            The hallNum
		 */
		public void setHallNum(String hallNum) {
			this.hallNum = hallNum;
		}

	}

	public class RentHouseSpecialtyList {

		private int specialtyId;

		public int getSpecialtyId() {
			return specialtyId;
		}

		public void setSpecialtyId(int specialtyId) {
			this.specialtyId = specialtyId;
		}

		@Expose
		private String specialtyName;

		/**
		 * 
		 * @return The specialtyName
		 */
		public String getSpecialtyName() {
			return specialtyName;
		}

		/**
		 * 
		 * @param specialtyName
		 *            The specialty_name
		 */
		public void setSpecialtyName(String specialtyName) {
			this.specialtyName = specialtyName;
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

}
