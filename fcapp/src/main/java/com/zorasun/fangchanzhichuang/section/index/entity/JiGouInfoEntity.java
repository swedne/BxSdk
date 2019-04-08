package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class JiGouInfoEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7746718153909798892L;

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
		private String address;
		@Expose
		private List<RentHouseList> rentHouseList = new ArrayList<RentHouseList>();
		private List<BrokerList> brokerList = new ArrayList<BrokerList>();

		public List<BrokerList> getBrokerList() {
			return brokerList;
		}

		public void setBrokerList(List<BrokerList> brokerList) {
			this.brokerList = brokerList;
		}

		@Expose
		private String hiring;
		@Expose
		private String companyInfo;
		@Expose
		private String propagandize;
		@Expose
		private List<SecondHouseList> secondHouseList = new ArrayList<SecondHouseList>();
		@Expose
		private Object brokerNum;
		@Expose
		private Integer userId;
		@Expose
		private Integer rentingNum;
		@Expose
		private String interMediaryName;
		@Expose
		private String realName;
		@Expose
		private String logoImage;
		@Expose
		private Integer secondHouseNum;
		@Expose
		private Object userIds;
		@Expose
		private String enterpriseHonor;
		@Expose
		private String tel;
		@Expose
		private String companyProfile;
		@Expose
		private String qualificationNo;
		@Expose
		private String email;

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
		 * @return The rentHouseList
		 */
		public List<RentHouseList> getRentHouseList() {
			return rentHouseList;
		}

		/**
		 * 
		 * @param rentHouseList
		 *            The rentHouseList
		 */
		public void setRentHouseList(List<RentHouseList> rentHouseList) {
			this.rentHouseList = rentHouseList;
		}

		/**
		 * 
		 * @return The hiring
		 */
		public String getHiring() {
			return hiring;
		}

		/**
		 * 
		 * @param hiring
		 *            The hiring
		 */
		public void setHiring(String hiring) {
			this.hiring = hiring;
		}

		/**
		 * 
		 * @return The companyInfo
		 */
		public String getCompanyInfo() {
			return companyInfo;
		}

		/**
		 * 
		 * @param companyInfo
		 *            The companyInfo
		 */
		public void setCompanyInfo(String companyInfo) {
			this.companyInfo = companyInfo;
		}

		/**
		 * 
		 * @return The propagandize
		 */
		public String getPropagandize() {
			return propagandize;
		}

		/**
		 * 
		 * @param propagandize
		 *            The propagandize
		 */
		public void setPropagandize(String propagandize) {
			this.propagandize = propagandize;
		}

		/**
		 * 
		 * @return The secondHouseList
		 */
		public List<SecondHouseList> getSecondHouseList() {
			return secondHouseList;
		}

		/**
		 * 
		 * @param secondHouseList
		 *            The secondHouseList
		 */
		public void setSecondHouseList(List<SecondHouseList> secondHouseList) {
			this.secondHouseList = secondHouseList;
		}

		/**
		 * 
		 * @return The brokerNum
		 */
		public Object getBrokerNum() {
			return brokerNum;
		}

		/**
		 * 
		 * @param brokerNum
		 *            The brokerNum
		 */
		public void setBrokerNum(Object brokerNum) {
			this.brokerNum = brokerNum;
		}

		/**
		 * 
		 * @return The userId
		 */
		public Integer getUserId() {
			return userId;
		}

		/**
		 * 
		 * @param userId
		 *            The userId
		 */
		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		/**
		 * 
		 * @return The rentingNum
		 */
		public Integer getRentingNum() {
			return rentingNum;
		}

		/**
		 * 
		 * @param rentingNum
		 *            The rentingNum
		 */
		public void setRentingNum(Integer rentingNum) {
			this.rentingNum = rentingNum;
		}

		/**
		 * 
		 * @return The interMediaryName
		 */
		public String getInterMediaryName() {
			return interMediaryName;
		}

		/**
		 * 
		 * @param interMediaryName
		 *            The interMediaryName
		 */
		public void setInterMediaryName(String interMediaryName) {
			this.interMediaryName = interMediaryName;
		}

		/**
		 * 
		 * @return The realName
		 */
		public String getRealName() {
			return realName;
		}

		/**
		 * 
		 * @param realName
		 *            The realName
		 */
		public void setRealName(String realName) {
			this.realName = realName;
		}

		/**
		 * 
		 * @return The logoImage
		 */
		public String getLogoImage() {
			return logoImage;
		}

		/**
		 * 
		 * @param logoImage
		 *            The logoImage
		 */
		public void setLogoImage(String logoImage) {
			this.logoImage = logoImage;
		}

		/**
		 * 
		 * @return The secondHouseNum
		 */
		public Integer getSecondHouseNum() {
			return secondHouseNum;
		}

		/**
		 * 
		 * @param secondHouseNum
		 *            The secondHouseNum
		 */
		public void setSecondHouseNum(Integer secondHouseNum) {
			this.secondHouseNum = secondHouseNum;
		}

		/**
		 * 
		 * @return The userIds
		 */
		public Object getUserIds() {
			return userIds;
		}

		/**
		 * 
		 * @param userIds
		 *            The userIds
		 */
		public void setUserIds(Object userIds) {
			this.userIds = userIds;
		}

		/**
		 * 
		 * @return The enterpriseHonor
		 */
		public String getEnterpriseHonor() {
			return enterpriseHonor;
		}

		/**
		 * 
		 * @param enterpriseHonor
		 *            The enterpriseHonor
		 */
		public void setEnterpriseHonor(String enterpriseHonor) {
			this.enterpriseHonor = enterpriseHonor;
		}

		/**
		 * 
		 * @return The tel
		 */
		public String getTel() {
			return tel;
		}

		/**
		 * 
		 * @param tel
		 *            The tel
		 */
		public void setTel(String tel) {
			this.tel = tel;
		}

		/**
		 * 
		 * @return The companyProfile
		 */
		public String getCompanyProfile() {
			return companyProfile;
		}

		/**
		 * 
		 * @param companyProfile
		 *            The companyProfile
		 */
		public void setCompanyProfile(String companyProfile) {
			this.companyProfile = companyProfile;
		}

		/**
		 * 
		 * @return The qualificationNo
		 */
		public String getQualificationNo() {
			return qualificationNo;
		}

		/**
		 * 
		 * @param qualificationNo
		 *            The qualificationNo
		 */
		public void setQualificationNo(String qualificationNo) {
			this.qualificationNo = qualificationNo;
		}

		/**
		 * 
		 * @return The email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * 
		 * @param email
		 *            The email
		 */
		public void setEmail(String email) {
			this.email = email;
		}

	}

	public class SecondHouseList {

		@Expose
		private String houseAuthCode;
		@Expose
		private Integer hrlResourceTypeId;
		@Expose
		private Integer bIsLocked;
		@Expose
		private Object businessName;
		@Expose
		private Integer floorSum;
		@Expose
		private Integer houseTypeId;
		@Expose
		private String berryGG;
		@Expose
		private Object specialtyIds;
		@Expose
		private String picUrl;
		@Expose
		private String payType;
		@Expose
		private Integer roomNum;
		@Expose
		private String areaName;
		@Expose
		private Integer price;
		@Expose
		private Integer id;
		@Expose
		private Integer hrlDecorateId;
		@Expose
		private Integer isShowHouse;
		@Expose
		private String searchContent;
		@Expose
		private Double longitude;
		@Expose
		private Object hrlOffbuildlevelId;
		@Expose
		private String houseBarCode;
		@Expose
		private Object officeTypeName;
		@Expose
		private String buildTime;
		@Expose
		private Object rentMoney;
		@Expose
		private String degreeName;
		@Expose
		private Integer hrlIsEdit;
		@Expose
		private Object hrlShopTypeId;
		@Expose
		private String orientationName;
		@Expose
		private String propertyNo;
		@Expose
		private Object shopType;
		@Expose
		private Integer isActivate;
		@Expose
		private Integer hallNum;
		@Expose
		private String houseResourceName;
		@Expose
		private Integer isShowImage;
		@Expose
		private Double latitude;
		@Expose
		private String typeName;
		@Expose
		private Integer areaListId;
		@Expose
		private String hrlCreatedTime;
		@Expose
		private String title;
		@Expose
		private Integer hrlUserId;
		@Expose
		private Integer isList;
		@Expose
		private Integer floorNum;
		@Expose
		private Integer isAuth;
		@Expose
		private Integer hrlIsTooHigh;
		@Expose
		private Double firstPay;
		@Expose
		private String address;
		@Expose
		private Integer salePrice;
		@Expose
		private Integer isDelete;
		@Expose
		private Object levelName;
		@Expose
		private Integer pIsLocked;
		@Expose
		private String areaListName;
		@Expose
		private Integer areaId;
		@Expose
		private Integer isDes;
		@Expose
		private String hrlUpdateTime;
		@Expose
		private Integer hrlBusinessListId;
		@Expose
		private Object plantArea;
		@Expose
		private Integer hrlOrientationId;

		/**
		 * 
		 * @return The houseAuthCode
		 */
		public String getHouseAuthCode() {
			return houseAuthCode;
		}

		/**
		 * 
		 * @param houseAuthCode
		 *            The houseAuthCode
		 */
		public void setHouseAuthCode(String houseAuthCode) {
			this.houseAuthCode = houseAuthCode;
		}

		/**
		 * 
		 * @return The hrlResourceTypeId
		 */
		public Integer getHrlResourceTypeId() {
			return hrlResourceTypeId;
		}

		/**
		 * 
		 * @param hrlResourceTypeId
		 *            The hrlResourceTypeId
		 */
		public void setHrlResourceTypeId(Integer hrlResourceTypeId) {
			this.hrlResourceTypeId = hrlResourceTypeId;
		}

		/**
		 * 
		 * @return The bIsLocked
		 */
		public Integer getBIsLocked() {
			return bIsLocked;
		}

		/**
		 * 
		 * @param bIsLocked
		 *            The bIsLocked
		 */
		public void setBIsLocked(Integer bIsLocked) {
			this.bIsLocked = bIsLocked;
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
		 * @return The specialtyIds
		 */
		public Object getSpecialtyIds() {
			return specialtyIds;
		}

		/**
		 * 
		 * @param specialtyIds
		 *            The specialtyIds
		 */
		public void setSpecialtyIds(Object specialtyIds) {
			this.specialtyIds = specialtyIds;
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
		 * @return The hrlDecorateId
		 */
		public Integer getHrlDecorateId() {
			return hrlDecorateId;
		}

		/**
		 * 
		 * @param hrlDecorateId
		 *            The hrlDecorateId
		 */
		public void setHrlDecorateId(Integer hrlDecorateId) {
			this.hrlDecorateId = hrlDecorateId;
		}

		/**
		 * 
		 * @return The isShowHouse
		 */
		public Integer getIsShowHouse() {
			return isShowHouse;
		}

		/**
		 * 
		 * @param isShowHouse
		 *            The isShowHouse
		 */
		public void setIsShowHouse(Integer isShowHouse) {
			this.isShowHouse = isShowHouse;
		}

		/**
		 * 
		 * @return The searchContent
		 */
		public String getSearchContent() {
			return searchContent;
		}

		/**
		 * 
		 * @param searchContent
		 *            The searchContent
		 */
		public void setSearchContent(String searchContent) {
			this.searchContent = searchContent;
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
		 * @return The hrlOffbuildlevelId
		 */
		public Object getHrlOffbuildlevelId() {
			return hrlOffbuildlevelId;
		}

		/**
		 * 
		 * @param hrlOffbuildlevelId
		 *            The hrlOffbuildlevelId
		 */
		public void setHrlOffbuildlevelId(Object hrlOffbuildlevelId) {
			this.hrlOffbuildlevelId = hrlOffbuildlevelId;
		}

		/**
		 * 
		 * @return The houseBarCode
		 */
		public String getHouseBarCode() {
			return houseBarCode;
		}

		/**
		 * 
		 * @param houseBarCode
		 *            The houseBarCode
		 */
		public void setHouseBarCode(String houseBarCode) {
			this.houseBarCode = houseBarCode;
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
		public Object getRentMoney() {
			return rentMoney;
		}

		/**
		 * 
		 * @param rentMoney
		 *            The rentMoney
		 */
		public void setRentMoney(Object rentMoney) {
			this.rentMoney = rentMoney;
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
		 * @return The hrlIsEdit
		 */
		public Integer getHrlIsEdit() {
			return hrlIsEdit;
		}

		/**
		 * 
		 * @param hrlIsEdit
		 *            The hrlIsEdit
		 */
		public void setHrlIsEdit(Integer hrlIsEdit) {
			this.hrlIsEdit = hrlIsEdit;
		}

		/**
		 * 
		 * @return The hrlShopTypeId
		 */
		public Object getHrlShopTypeId() {
			return hrlShopTypeId;
		}

		/**
		 * 
		 * @param hrlShopTypeId
		 *            The hrlShopTypeId
		 */
		public void setHrlShopTypeId(Object hrlShopTypeId) {
			this.hrlShopTypeId = hrlShopTypeId;
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
		 * @return The propertyNo
		 */
		public String getPropertyNo() {
			return propertyNo;
		}

		/**
		 * 
		 * @param propertyNo
		 *            The propertyNo
		 */
		public void setPropertyNo(String propertyNo) {
			this.propertyNo = propertyNo;
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
		 * @return The isActivate
		 */
		public Integer getIsActivate() {
			return isActivate;
		}

		/**
		 * 
		 * @param isActivate
		 *            The isActivate
		 */
		public void setIsActivate(Integer isActivate) {
			this.isActivate = isActivate;
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
		 * @return The hrlCreatedTime
		 */
		public String getHrlCreatedTime() {
			return hrlCreatedTime;
		}

		/**
		 * 
		 * @param hrlCreatedTime
		 *            The hrlCreatedTime
		 */
		public void setHrlCreatedTime(String hrlCreatedTime) {
			this.hrlCreatedTime = hrlCreatedTime;
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
		 * @return The hrlUserId
		 */
		public Integer getHrlUserId() {
			return hrlUserId;
		}

		/**
		 * 
		 * @param hrlUserId
		 *            The hrlUserId
		 */
		public void setHrlUserId(Integer hrlUserId) {
			this.hrlUserId = hrlUserId;
		}

		/**
		 * 
		 * @return The isList
		 */
		public Integer getIsList() {
			return isList;
		}

		/**
		 * 
		 * @param isList
		 *            The isList
		 */
		public void setIsList(Integer isList) {
			this.isList = isList;
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
		 * @return The hrlIsTooHigh
		 */
		public Integer getHrlIsTooHigh() {
			return hrlIsTooHigh;
		}

		/**
		 * 
		 * @param hrlIsTooHigh
		 *            The hrlIsTooHigh
		 */
		public void setHrlIsTooHigh(Integer hrlIsTooHigh) {
			this.hrlIsTooHigh = hrlIsTooHigh;
		}

		/**
		 * 
		 * @return The firstPay
		 */
		public Double getFirstPay() {
			return firstPay;
		}

		/**
		 * 
		 * @param firstPay
		 *            The firstPay
		 */
		public void setFirstPay(Double firstPay) {
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
		 * @return The isDelete
		 */
		public Integer getIsDelete() {
			return isDelete;
		}

		/**
		 * 
		 * @param isDelete
		 *            The isDelete
		 */
		public void setIsDelete(Integer isDelete) {
			this.isDelete = isDelete;
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
		 * @return The pIsLocked
		 */
		public Integer getPIsLocked() {
			return pIsLocked;
		}

		/**
		 * 
		 * @param pIsLocked
		 *            The pIsLocked
		 */
		public void setPIsLocked(Integer pIsLocked) {
			this.pIsLocked = pIsLocked;
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
		 * @return The isDes
		 */
		public Integer getIsDes() {
			return isDes;
		}

		/**
		 * 
		 * @param isDes
		 *            The isDes
		 */
		public void setIsDes(Integer isDes) {
			this.isDes = isDes;
		}

		/**
		 * 
		 * @return The hrlUpdateTime
		 */
		public String getHrlUpdateTime() {
			return hrlUpdateTime;
		}

		/**
		 * 
		 * @param hrlUpdateTime
		 *            The hrlUpdateTime
		 */
		public void setHrlUpdateTime(String hrlUpdateTime) {
			this.hrlUpdateTime = hrlUpdateTime;
		}

		/**
		 * 
		 * @return The hrlBusinessListId
		 */
		public Integer getHrlBusinessListId() {
			return hrlBusinessListId;
		}

		/**
		 * 
		 * @param hrlBusinessListId
		 *            The hrlBusinessListId
		 */
		public void setHrlBusinessListId(Integer hrlBusinessListId) {
			this.hrlBusinessListId = hrlBusinessListId;
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
		 * @return The hrlOrientationId
		 */
		public Integer getHrlOrientationId() {
			return hrlOrientationId;
		}

		/**
		 * 
		 * @param hrlOrientationId
		 *            The hrlOrientationId
		 */
		public void setHrlOrientationId(Integer hrlOrientationId) {
			this.hrlOrientationId = hrlOrientationId;
		}

	}

	public class RentHouseList {

		@Expose
		private String picUrl;
		@Expose
		private Integer isAuth;
		@Expose
		private Integer roomNum;
		@Expose
		private Double salePrice;
		@Expose
		private Integer isShowImage;
		@Expose
		private String title;
		@Expose
		private Integer houseResourceId;
		public Integer id;
		@Expose
		private Object plantAcreage;
		@Expose
		private Double berryGG;
		@Expose
		private Integer hallNum;
		@Expose
		private Object rental;
		@Expose
		private Integer arealistId;

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
		 * @return The salePrice
		 */
		public Double getSalePrice() {
			return salePrice;
		}

		/**
		 * 
		 * @param salePrice
		 *            The salePrice
		 */
		public void setSalePrice(Double salePrice) {
			this.salePrice = salePrice;
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
		 * @return The houseResourceId
		 */
		public Integer getHouseResourceId() {
			return houseResourceId;
		}

		/**
		 * 
		 * @param houseResourceId
		 *            The houseResourceId
		 */
		public void setHouseResourceId(Integer houseResourceId) {
			this.houseResourceId = houseResourceId;
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
		 * @return The berryGG
		 */
		public Double getBerryGG() {
			return berryGG;
		}

		/**
		 * 
		 * @param berryGG
		 *            The berryGG
		 */
		public void setBerryGG(Double berryGG) {
			this.berryGG = berryGG;
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

		/**
		 * 
		 * @return The rental
		 */
		public Object getRental() {
			return rental;
		}

		/**
		 * 
		 * @param rental
		 *            The rental
		 */
		public void setRental(Object rental) {
			this.rental = rental;
		}

		/**
		 * 
		 * @return The arealistId
		 */
		public Integer getArealistId() {
			return arealistId;
		}

		/**
		 * 
		 * @param arealistId
		 *            The arealistId
		 */
		public void setArealistId(Integer arealistId) {
			this.arealistId = arealistId;
		}

	}

	public class BrokerList {
		private int isExpert;
		private int brokerId;
		private String moblie;
		private int integra;
		private Double goodChance;
		private String headUrl;
		private String brokerName;

		public int getIsExpert() {
			return isExpert;
		}

		public void setIsExpert(int isExpert) {
			this.isExpert = isExpert;
		}

		public int getBrokerId() {
			return brokerId;
		}

		public void setBrokerId(int brokerId) {
			this.brokerId = brokerId;
		}

		public String getMoblie() {
			return moblie;
		}

		public void setMoblie(String moblie) {
			this.moblie = moblie;
		}

		public int getIntegra() {
			return integra;
		}

		public void setIntegra(int integra) {
			this.integra = integra;
		}

		public Double getGoodChance() {
			return goodChance;
		}

		public void setGoodChance(Double goodChance) {
			this.goodChance = goodChance;
		}

		public String getHeadUrl() {
			return headUrl;
		}

		public void setHeadUrl(String headUrl) {
			this.headUrl = headUrl;
		}

		public String getBrokerName() {
			return brokerName;
		}

		public void setBrokerName(String brokerName) {
			this.brokerName = brokerName;
		}
	}

}
