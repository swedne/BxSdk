package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.MapSearchEntity.XiamenInitList.MapSearchHouseAreaInfoList;

public class ShangYeDiChanListEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4470390715932839958L;

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

		private int pageCount;

		public int getPageCount() {
			return pageCount;
		}

		public void setPageCount(int pageCount) {
			this.pageCount = pageCount;
		}

		@Expose
		private List<AreaList> areaList = new ArrayList<AreaList>();
		private List<BusinessEstateList> businessEstateList = new ArrayList<>();

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

		public List<BusinessEstateList> getBusinessEstateLists() {
			return businessEstateList;
		}

		public void setBusinessEstateLists(List<BusinessEstateList> businessEstateList) {
			this.businessEstateList = businessEstateList;
		}

	}

	public class BusinessEstateList {

		private Integer rental;

		public Integer getRental() {
			return rental;
		}

		public void setRental(Integer rental) {
			this.rental = rental;
		}

		private String surFaceUrl;

		public String getSurFaceUrl() {
			return surFaceUrl;
		}

		public void setSurFaceUrl(String surFaceUrl) {
			this.surFaceUrl = surFaceUrl;
		}

		@Expose
		private Object houseAuthCode;
		@Expose
		private Object dormAcreage;
		@Expose
		private Double latitude;
		@Expose
		private String typeName;
		@Expose
		private String businessListName;
		@Expose
		private String title;
		@Expose
		private String plantAcreage;
		@Expose
		private Integer houseTypeId;
		@Expose
		private String berryGG;
		@Expose
		private Object picUrl;
		@Expose
		private Integer isAuth;
		@Expose
		private Integer roomNum;
		@Expose
		private String areaName;
		@Expose
		private String price;
		@Expose
		private Integer id;
		@Expose
		private Double longitude;
		@Expose
		private Object houseBarCode;
		@Expose
		private Object firstPay;
		@Expose
		private String workAcreage;
		@Expose
		private String address;
		@Expose
		private String buildTime;
		@Expose
		private Integer salePrice;
		@Expose
		private String acreage;
		@Expose
		private String areaListName;
		@Expose
		private String orientationName;
		@Expose
		private String spaceAcreage;
		@Expose
		private Integer hallNum;

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
		 * @return The dormAcreage
		 */
		public Object getDormAcreage() {
			return dormAcreage;
		}

		/**
		 * 
		 * @param dormAcreage
		 *            The dormAcreage
		 */
		public void setDormAcreage(Object dormAcreage) {
			this.dormAcreage = dormAcreage;
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
		public String getbusinessListName() {
			return businessListName;
		}

		/**
		 * 
		 * @param businessName
		 *            The businessName
		 */
		public void setBusinessListName(String businessListName) {
			this.businessListName = businessListName;
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
		public String getPlantAcreage() {
			return plantAcreage;
		}

		/**
		 * 
		 * @param plantAcreage
		 *            The plantAcreage
		 */
		public void setPlantAcreage(String plantAcreage) {
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
		 * @return The workAcreage
		 */
		public String getWorkAcreage() {
			return workAcreage;
		}

		/**
		 * 
		 * @param workAcreage
		 *            The workAcreage
		 */
		public void setWorkAcreage(String workAcreage) {
			this.workAcreage = workAcreage;
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
		public Integer getSalePrice() {
			return salePrice;
		}

		/**
		 * 
		 * @param salePrice
		 *            The salePrice
		 */
		public void setSalePrice(Integer salePrice) {
			this.salePrice = salePrice;
		}

		/**
		 * 
		 * @return The acreage
		 */
		public String getAcreage() {
			return acreage;
		}

		/**
		 * 
		 * @param acreage
		 *            The acreage
		 */
		public void setAcreage(String acreage) {
			this.acreage = acreage;
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
		 * @return The spaceAcreage
		 */
		public String getSpaceAcreage() {
			return spaceAcreage;
		}

		/**
		 * 
		 * @param spaceAcreage
		 *            The spaceAcreage
		 */
		public void setSpaceAcreage(String spaceAcreage) {
			this.spaceAcreage = spaceAcreage;
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

		private ArrayList<MapSearchHouseAreaInfoList> mapSearchHouseAreaInfoList = new ArrayList<>();

		public int getAreaId() {
			return areaId;
		}

		public ArrayList<MapSearchHouseAreaInfoList> getMapSearchHouseAreaInfoList() {
			return mapSearchHouseAreaInfoList;
		}

		public void setMapSearchHouseAreaInfoList(ArrayList<MapSearchHouseAreaInfoList> mapSearchHouseAreaInfoList) {
			this.mapSearchHouseAreaInfoList = mapSearchHouseAreaInfoList;
		}

		public int getNewHouseNumByArea() {
			return newHouseNumByArea;
		}

		public void setNewHouseNumByArea(int newHouseNumByArea) {
			this.newHouseNumByArea = newHouseNumByArea;
		}

		public void setAreaId(int areaId) {
			this.areaId = areaId;
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
