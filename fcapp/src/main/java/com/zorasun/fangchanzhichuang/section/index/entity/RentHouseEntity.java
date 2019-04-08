package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class RentHouseEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1685024766974036733L;
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
		private RentHouse rentHouse;

		public RentHouse getRentHouse() {
			return rentHouse;
		}

		public void setRentHouse(RentHouse rentHouse) {
			this.rentHouse = rentHouse;
		}
	}

	public class RentHouse {
		private int isCollection;

		private String wxUrlAndroid;
		public String realName;

		public String getWxUrl() {
			return wxUrlAndroid;
		}

		public void setWxUrl(String wxUrl) {
			this.wxUrlAndroid = wxUrl;
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

		private int rentHouseId;
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
		private Object monthPayment;
		@Expose
		private String title;
		@Expose
		private Integer brokeId;
		@Expose
		private String berryGG;
		@Expose
		private Integer floorNum;
		@Expose
		private Integer isAuth;
		@Expose
		private Integer roomNum;
		@Expose
		private String areaName;
		@Expose
		private Object downPayment;
		@Expose
		private String imgUrlFirst;
		@Expose
		private List<AssortFacility> assortFacility = new ArrayList<AssortFacility>();
		@Expose
		private Object toiletNum;
		@Expose
		private Object areaSpecialId;
		@Expose
		private Double longitude;
		@Expose
		private Object balconyNum;
		@Expose
		private Integer isExpert;
		@Expose
		private Object salePrice;
		@Expose
		private String buildTime;
		@Expose
		private String mobile;
		@Expose
		private String houseTypeName;
		@Expose
		private String areaListName;
		@Expose
		private Double rental;
		@Expose
		private String payTypeName;
		@Expose
		private String orientationName;
		@Expose
		private Object propertyName;
		@Expose
		private List<SpecityNameList> speciltyNameList = new ArrayList<SpecityNameList>();
		@Expose
		private String decorateDegreeName;
		@Expose
		private String brokerName;
		@Expose
		private Integer hallNum;

		@Expose
		private long updateTime;

		@Expose
		private long createTime;
		private int estatePrice;

		public int getEstatePrice() {
			return estatePrice;
		}

		public void setEstatePrice(int estatePrice) {
			this.estatePrice = estatePrice;
		}

		/**
		 * 
		 * @return The uniqueNo
		 */
		public String getUniqueNo() {
			return uniqueNo;
		}

		public long getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(long updateTime) {
			this.updateTime = updateTime;
		}

		public long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(long createTime) {
			this.createTime = createTime;
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

		public int getRentHouseId() {
			return rentHouseId;
		}

		public void setRentHouseId(int rentHouseId) {
			this.rentHouseId = rentHouseId;
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
		 * @return The imgUrlFirst
		 */
		public String getImgUrlFirst() {
			return imgUrlFirst;
		}

		/**
		 * 
		 * @param imgUrlFirst
		 *            The imgUrlFirst
		 */
		public void setImgUrlFirst(String imgUrlFirst) {
			this.imgUrlFirst = imgUrlFirst;
		}

		/**
		 * 
		 * @return The assortFacility
		 */
		public List<AssortFacility> getAssortFacility() {
			return assortFacility;
		}

		/**
		 * 
		 * @param assortFacility
		 *            The assortFacility
		 */
		public void setAssortFacility(List<AssortFacility> assortFacility) {
			this.assortFacility = assortFacility;
		}

		/**
		 * 
		 * @return The toiletNum
		 */
		public Object getToiletNum() {
			return toiletNum;
		}

		/**
		 * 
		 * @param toiletNum
		 *            The toiletNum
		 */
		public void setToiletNum(Object toiletNum) {
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
		public Object getBalconyNum() {
			return balconyNum;
		}

		/**
		 * 
		 * @param balconyNum
		 *            The balconyNum
		 */
		public void setBalconyNum(Object balconyNum) {
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
		 * @return The rental
		 */
		public Double getRental() {
			return rental;
		}

		/**
		 * 
		 * @param rental
		 *            The rental
		 */
		public void setRental(Double rental) {
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
		public Object getPropertyName() {
			return propertyName;
		}

		/**
		 * 
		 * @param propertyName
		 *            The propertyName
		 */
		public void setPropertyName(Object propertyName) {
			this.propertyName = propertyName;
		}

		/**
		 * 
		 * @return The speciltyNameList
		 */
		public List<SpecityNameList> getSpeciltyNameList() {
			return speciltyNameList;
		}

		/**
		 * 
		 * @param speciltyNameList
		 *            The speciltyNameList
		 */
		public void setSpeciltyNameList(List<SpecityNameList> speciltyNameList) {
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

	public class AssortFacility {

		@Expose
		private String facilityName;
		@Expose
		private Integer assortFacilityId;

		/**
		 * 
		 * @return The facilityName
		 */
		public String getFacilityName() {
			return facilityName;
		}

		/**
		 * 
		 * @param facilityName
		 *            The facilityName
		 */
		public void setFacilityName(String facilityName) {
			this.facilityName = facilityName;
		}

		/**
		 * 
		 * @return The assortFacilityId
		 */
		public Integer getAssortFacilityId() {
			return assortFacilityId;
		}

		/**
		 * 
		 * @param assortFacilityId
		 *            The assortFacilityId
		 */
		public void setAssortFacilityId(Integer assortFacilityId) {
			this.assortFacilityId = assortFacilityId;
		}

	}

	public class SpecityNameList {
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
