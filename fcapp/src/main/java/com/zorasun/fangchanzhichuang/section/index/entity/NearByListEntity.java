package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class NearByListEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9037663889739951475L;
	private Content content;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public class Content {

		private int pageCount;

		public int getPageCount() {
			return pageCount;
		}

		public void setPageCount(int pageCount) {
			this.pageCount = pageCount;
		}

		@Expose
		private List<HouseListNearby> houseListNearby = new ArrayList<HouseListNearby>();

		/**
		 * 
		 * @return The houseListNearby
		 */
		public List<HouseListNearby> getHouseListNearby() {
			return houseListNearby;
		}

		/**
		 * 
		 * @param houseListNearby
		 *            The houseListNearby
		 */
		public void setHouseListNearby(List<HouseListNearby> houseListNearby) {
			this.houseListNearby = houseListNearby;
		}

	}

	public class HouseListNearby {

		private Integer salePrice;

		public Integer getSalePrice() {
			return salePrice;
		}

		public void setSalePrice(Integer salePrice) {
			this.salePrice = salePrice;
		}

		@Expose
		private Object houseAuthCode;
		@Expose
		private String houseResourceName;
		@Expose
		private Integer isShowImage;
		@Expose
		private String surFaceUrl;
		@Expose
		private List<SecondHouseSpecialtyList> secondHouseSpecialtyList = new ArrayList<SecondHouseSpecialtyList>();
		private List<RentHouseSpecialtyList> rentHouseSpecialtyList = new ArrayList<RentHouseSpecialtyList>();
		@Expose
		private String typeName;
		@Expose
		private Object businessName;
		@Expose
		private Integer areaListId;
		@Expose
		private String title;
		@Expose
		private Object plantAcreage;
		@Expose
		private Integer houseTypeId;
		@Expose
		private String berryGG;
		@Expose
		private Integer floorNum;
		@Expose
		private Object picUrl;
		@Expose
		private Object totalAcreage;
		@Expose
		private Integer number;
		@Expose
		private Integer isAuth;
		@Expose
		private String payType;
		@Expose
		private Integer roomNum;
		@Expose
		private String areaName;
		@Expose
		private Double price;
		@Expose
		private Integer id;
		@Expose
		private Object houseBarCode;
		@Expose
		private String address;
		@Expose
		private Object officeTypeName;
		@Expose
		private String buildTime;
		@Expose
		private Integer rental;
		@Expose
		private Object levelName;
		@Expose
		private String degreeName;
		@Expose
		private String areaListName;
		@Expose
		private String orientationName;
		@Expose
		private Integer floorSun;
		@Expose
		private Object shopType;
		@Expose
		private Integer hallNum;

		public List<RentHouseSpecialtyList> getRentHouseSpecialtyList() {
			return rentHouseSpecialtyList;
		}

		public void setRentHouseSpecialtyList(List<RentHouseSpecialtyList> rentHouseSpecialtyList) {
			this.rentHouseSpecialtyList = rentHouseSpecialtyList;
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
		 * @return The surFaceUrl
		 */
		public String getSurFaceUrl() {
			return surFaceUrl;
		}

		/**
		 * 
		 * @param surFaceUrl
		 *            The surFaceUrl
		 */
		public void setSurFaceUrl(String surFaceUrl) {
			this.surFaceUrl = surFaceUrl;
		}

		/**
		 * 
		 * @return The specialtyListForList
		 */
		public List<SecondHouseSpecialtyList> getSecondHouseSpecialtyList() {
			return secondHouseSpecialtyList;
		}

		/**
		 * 
		 * @param specialtyListForList
		 *            The specialtyListForList
		 */
		public void setSecondHouseSpecialtyList(List<SecondHouseSpecialtyList> secondHouseSpecialtyList) {
			this.secondHouseSpecialtyList = secondHouseSpecialtyList;
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
		 * @return The plantAcreage
		 */
		public Object getPlantAcreage() {
			return plantAcreage;
		}

		/**
		 * 
		 * @param plantAcreage
		 *            The plantAcreage
		 */
		public void setPlantAcreage(Object plantAcreage) {
			this.plantAcreage = plantAcreage;
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
		public Object getPicUrl() {
			return picUrl;
		}

		/**
		 * 
		 * @param picUrl
		 *            The picUrl
		 */
		public void setPicUrl(Object picUrl) {
			this.picUrl = picUrl;
		}

		/**
		 * 
		 * @return The totalAcreage
		 */
		public Object getTotalAcreage() {
			return totalAcreage;
		}

		/**
		 * 
		 * @param totalAcreage
		 *            The totalAcreage
		 */
		public void setTotalAcreage(Object totalAcreage) {
			this.totalAcreage = totalAcreage;
		}

		/**
		 * 
		 * @return The number
		 */
		public Integer getNumber() {
			return number;
		}

		/**
		 * 
		 * @param number
		 *            The number
		 */
		public void setNumber(Integer number) {
			this.number = number;
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
		public Double getPrice() {
			return price;
		}

		/**
		 * 
		 * @param price
		 *            The price
		 */
		public void setPrice(Double price) {
			this.price = price;
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
		public Object getOfficeTypeName() {
			return officeTypeName;
		}

		/**
		 * 
		 * @param officeTypeName
		 *            The officeTypeName
		 */
		public void setOfficeTypeName(Object officeTypeName) {
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
		 * @return The rentMoney
		 */
		public Integer getRentMoney() {
			return rental;
		}

		/**
		 * 
		 * @param rentMoney
		 *            The rentMoney
		 */
		public void setRentMoney(Integer rental) {
			this.rental = rental;
		}

		/**
		 * 
		 * @return The levelName
		 */
		public Object getLevelName() {
			return levelName;
		}

		/**
		 * 
		 * @param levelName
		 *            The levelName
		 */
		public void setLevelName(Object levelName) {
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
		 * @return The floorSun
		 */
		public Integer getFloorSun() {
			return floorSun;
		}

		/**
		 * 
		 * @param floorSun
		 *            The floorSun
		 */
		public void setFloorSun(Integer floorSun) {
			this.floorSun = floorSun;
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

	public class SecondHouseSpecialtyList {
		private String specialtyName;

		public String getSpecialtyName() {
			return specialtyName;
		}

		public void setSpecialtyName(String specialtyName) {
			this.specialtyName = specialtyName;
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

}
