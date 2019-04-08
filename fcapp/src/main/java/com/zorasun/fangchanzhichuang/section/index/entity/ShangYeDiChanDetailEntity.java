package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class ShangYeDiChanDetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4891277963630786924L;
	private Content content;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public class Content {
		private BusinessEstate businessEstate;

		public BusinessEstate getBusinessEstate() {
			return businessEstate;
		}

		public void setBusinessEstate(BusinessEstate businessEstate) {
			this.businessEstate = businessEstate;
		}
	}

	public class BusinessEstate {

		private String businessListName;
		private String parkingResourceDesc;
		private int isCollection;
		private String wxUrlAndroid;

		public String getWxUrl() {
			return wxUrlAndroid;
		}

		public void setWxUrl(String wxUrl) {
			this.wxUrlAndroid = wxUrl;
		}

		private ArrayList<ImageUrlList> imageUrlList = new ArrayList<>();

		public ArrayList<ImageUrlList> getImageUrlList() {
			return imageUrlList;
		}

		public void setImageUrlList(ArrayList<ImageUrlList> imageUrlList) {
			this.imageUrlList = imageUrlList;
		}

		public int getIsCollection() {
			return isCollection;
		}

		public void setIsCollection(int isCollection) {
			this.isCollection = isCollection;
		}

		@Expose
		private String uniqueNo;
		@Expose
		private Object dormAcreage;
		@Expose
		private Double latitude;
		@Expose
		private Integer businessId;
		@Expose
		private String businessName;
		@Expose
		private Integer areaListId;
		@Expose
		private String headUrl;
		@Expose
		private Double estatePrice;
		@Expose
		private String houseResourceDesc;
		@Expose
		private Object monthPayment;
		@Expose
		private String floorSum;
		@Expose
		private String title;
		@Expose
		private Integer brokeId;
		@Expose
		private String plantAcreage;
		@Expose
		private String floorNum;
		@Expose
		private String berryGG;
		@Expose
		private Integer isAuth;
		@Expose
		private String roomNum;
		@Expose
		private String areaName;
		@Expose
		private List<Object> businessEstateSpecialtyList = new ArrayList<Object>();
		private List<AssortFacilityList> assortFacilityList = new ArrayList<AssortFacilityList>();

		public List<AssortFacilityList> getAssortFacilityList() {
			return assortFacilityList;
		}

		public void setAssortFacilityList(List<AssortFacilityList> assortFacilityList) {
			this.assortFacilityList = assortFacilityList;
		}

		@Expose
		private Object downPayment;
		@Expose
		private long createdTime;
		@Expose
		private Integer toiletNum;
		@Expose
		private Double longitude;
		@Expose
		private Integer balconyNum;
		@Expose
		private Integer isExpert;
		@Expose
		private String workAcreage;
		@Expose
		private String officeTypeName;
		@Expose
		private Integer salePrice;
		@Expose
		private String buildTime;
		@Expose
		private String officeLevel;
		@Expose
		private String mobile;
		@Expose
		private String acreage;
		@Expose
		private long updateTime;
		@Expose
		private String areaListName;
		@Expose
		private String electricPower;
		@Expose
		private Integer rental;
		@Expose
		private String payTypeName;
		@Expose
		private Integer areaId;
		@Expose
		private String spaceAcreage;
		@Expose
		private String decorateDegreeName;
		@Expose
		private String brokerName;
		@Expose
		private String shopTypeName;
		@Expose
		private Integer hallNum;
		public String realName;

		public String getParkingResourceDesc() {
			return parkingResourceDesc;
		}

		public void setParkingResourceDesc(String parkingResourceDesc) {
			this.parkingResourceDesc = parkingResourceDesc;
		}

		public String getBusinessName() {
			return businessName;
		}

		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

		public String getBusinessListName() {
			return businessListName;
		}

		public void setBusinessListName(String businessListName) {
			this.businessListName = businessListName;
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
		 * @return The businessId
		 */
		public Integer getBusinessId() {
			return businessId;
		}

		/**
		 * 
		 * @param businessId
		 *            The businessId
		 */
		public void setBusinessId(Integer businessId) {
			this.businessId = businessId;
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
		 * @return The estatePrice
		 */
		public Double getEstatePrice() {
			return estatePrice;
		}

		/**
		 * 
		 * @param estatePrice
		 *            The estatePrice
		 */
		public void setEstatePrice(Double estatePrice) {
			this.estatePrice = estatePrice;
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
		public Object getMonthPayment() {
			return monthPayment;
		}

		/**
		 * 
		 * @param monthPayment
		 *            The monthPayment
		 */
		public void setMonthPayment(Object monthPayment) {
			this.monthPayment = monthPayment;
		}

		/**
		 * 
		 * @return The floorSum
		 */
		public String getFloorSum() {
			return floorSum;
		}

		/**
		 * 
		 * @param floorSum
		 *            The floorSum
		 */
		public void setFloorSum(String floorSum) {
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
		 * @return The businessEstateSpecialtyList
		 */
		public List<Object> getBusinessEstateSpecialtyList() {
			return businessEstateSpecialtyList;
		}

		/**
		 * 
		 * @param businessEstateSpecialtyList
		 *            The businessEstateSpecialtyList
		 */
		public void setBusinessEstateSpecialtyList(List<Object> businessEstateSpecialtyList) {
			this.businessEstateSpecialtyList = businessEstateSpecialtyList;
		}

		/**
		 * 
		 * @return The downPayment
		 */
		public Object getDownPayment() {
			return downPayment;
		}

		/**
		 * 
		 * @param downPayment
		 *            The downPayment
		 */
		public void setDownPayment(Object downPayment) {
			this.downPayment = downPayment;
		}

		/**
		 * 
		 * @return The createdTime
		 */
		public long getCreatedTime() {
			return createdTime;
		}

		/**
		 * 
		 * @param createdTime
		 *            The createdTime
		 */
		public void setCreatedTime(long createdTime) {
			this.createdTime = createdTime;
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
		 * @return The officeLevel
		 */
		public String getOfficeLevel() {
			return officeLevel;
		}

		/**
		 * 
		 * @param officeLevel
		 *            The officeLevel
		 */
		public void setOfficeLevel(String officeLevel) {
			this.officeLevel = officeLevel;
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
		 * @return The updateTime
		 */
		public long getUpdateTime() {
			return updateTime;
		}

		/**
		 * 
		 * @param updateTime
		 *            The updateTime
		 */
		public void setUpdateTime(long updateTime) {
			this.updateTime = updateTime;
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
		 * @return The electricPower
		 */
		public String getElectricPower() {
			return electricPower;
		}

		/**
		 * 
		 * @param electricPower
		 *            The electricPower
		 */
		public void setElectricPower(String electricPower) {
			this.electricPower = electricPower;
		}

		/**
		 * 
		 * @return The rental
		 */
		public Integer getRental() {
			return rental;
		}

		/**
		 * 
		 * @param rental
		 *            The rental
		 */
		public void setRental(Integer rental) {
			this.rental = rental;
		}

		/**
		 * 
		 * @return The payTypeName
		 */
		public String getPayTypeName() {
			return payTypeName;
		}

		/**
		 * 
		 * @param payTypeName
		 *            The payTypeName
		 */
		public void setPayTypeName(String payTypeName) {
			this.payTypeName = payTypeName;
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
		 * @return The shopTypeName
		 */
		public String getShopTypeName() {
			return shopTypeName;
		}

		/**
		 * 
		 * @param shopTypeName
		 *            The shopTypeName
		 */
		public void setShopTypeName(String shopTypeName) {
			this.shopTypeName = shopTypeName;
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

	public class AssortFacilityList {
		private String houseTypeName;
		private String facilityName;
		private int houseTypeId;

		public String getHouseTypeName() {
			return houseTypeName;
		}

		public void setHouseTypeName(String houseTypeName) {
			this.houseTypeName = houseTypeName;
		}

		public String getFacilityName() {
			return facilityName;
		}

		public void setFacilityName(String facilityName) {
			this.facilityName = facilityName;
		}

		public int getHouseTypeId() {
			return houseTypeId;
		}

		public void setHouseTypeId(int houseTypeId) {
			this.houseTypeId = houseTypeId;
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
