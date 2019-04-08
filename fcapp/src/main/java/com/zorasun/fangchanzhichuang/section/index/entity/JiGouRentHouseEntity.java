package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class JiGouRentHouseEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5945199293958581102L;
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
		private Integer agencyId;
		@Expose
		private AgencyInfoList agencyInfoList;
		@Expose
		private Integer brokerNum;
		@Expose
		private Integer pageCount;
		@Expose
		private Integer rentingNum;
		@Expose
		private List<RentHouseList> rentHouseList = new ArrayList<RentHouseList>();
		@Expose
		private Integer secondHouseNum;

		/**
		 * 
		 * @return The agencyId
		 */
		public Integer getAgencyId() {
			return agencyId;
		}

		/**
		 * 
		 * @param agencyId
		 *            The agencyId
		 */
		public void setAgencyId(Integer agencyId) {
			this.agencyId = agencyId;
		}

		/**
		 * 
		 * @return The agencyInfoList
		 */
		public AgencyInfoList getAgencyInfoList() {
			return agencyInfoList;
		}

		/**
		 * 
		 * @param agencyInfoList
		 *            The agencyInfoList
		 */
		public void setAgencyInfoList(AgencyInfoList agencyInfoList) {
			this.agencyInfoList = agencyInfoList;
		}

		/**
		 * 
		 * @return The brokerNum
		 */
		public Integer getBrokerNum() {
			return brokerNum;
		}

		/**
		 * 
		 * @param brokerNum
		 *            The brokerNum
		 */
		public void setBrokerNum(Integer brokerNum) {
			this.brokerNum = brokerNum;
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
		 * @return The secondHouseList
		 */
		public List<RentHouseList> getRentHouseList() {
			return rentHouseList;
		}

		/**
		 * 
		 * @param secondHouseList
		 *            The secondHouseList
		 */
		public void setRentHouseList(List<RentHouseList> rentHouseList) {
			this.rentHouseList = rentHouseList;
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

	}

	public class AgencyInfoList {

		@Expose
		private String address;
		@Expose
		private String hiring;
		@Expose
		private String companyInfo;
		@Expose
		private String propagandize;
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

	public class RentHouseList {
		public String areaListName;
		public String plantArea;
		@Expose
		private List<String> specials = new ArrayList<String>();
		@Expose
		private Integer isShowImage;
		@Expose
		private String businessName;
		@Expose
		private Object typeName;
		@Expose
		private Integer areaListId;
		@Expose
		private Integer floorSum;
		@Expose
		private String title;
		@Expose
		private Double plantAcreage;
		@Expose
		private String berryGG;
		@Expose
		private Integer floorNum;
		@Expose
		private String picUrl;
		@Expose
		private Integer isAuth;
		@Expose
		private Integer roomNum;
		@Expose
		private String areaName;
		@Expose
		private Integer houseResourceId;
		@Expose
		private Double workAcreage;
		@Expose
		private Double salePrice;
		@Expose
		private String buildTime;
		@Expose
		private Double acreage;
		@Expose
		private String houseTypeName;
		@Expose
		private String levelName;
		@Expose
		private String rental;
		@Expose
		private String payTypeName;
		@Expose
		private String orientationName;
		@Expose
		private String name;
		@Expose
		private String shopTypeName;
		@Expose
		private Integer hallNum;

		/**
		 * 
		 * @return The specials
		 */
		public List<String> getSpecials() {
			return specials;
		}

		/**
		 * 
		 * @param specials
		 *            The specials
		 */
		public void setSpecials(List<String> specials) {
			this.specials = specials;
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
		 * @return The typeName
		 */
		public Object getTypeName() {
			return typeName;
		}

		/**
		 * 
		 * @param typeName
		 *            The typeName
		 */
		public void setTypeName(Object typeName) {
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
		 * @return The plantAcreage
		 */
		public Double getPlantAcreage() {
			return plantAcreage;
		}

		/**
		 * 
		 * @param plantAcreage
		 *            The plantAcreage
		 */
		public void setPlantAcreage(Double plantAcreage) {
			this.plantAcreage = plantAcreage;
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
		 * @return The houseResourceId
		 */
		public Integer getHouseResourceId() {
			return houseResourceId;
		}

		public Integer id;

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
		 * @return The workAcreage
		 */
		public Double getWorkAcreage() {
			return workAcreage;
		}

		/**
		 * 
		 * @param workAcreage
		 *            The workAcreage
		 */
		public void setWorkAcreage(Double workAcreage) {
			this.workAcreage = workAcreage;
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
		 * @return The acreage
		 */
		public Double getAcreage() {
			return acreage;
		}

		/**
		 * 
		 * @param acreage
		 *            The acreage
		 */
		public void setAcreage(Double acreage) {
			this.acreage = acreage;
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
		 * @return The rental
		 */
		public String getRental() {
			return rental;
		}

		/**
		 * 
		 * @param rental
		 *            The rental
		 */
		public void setRental(String rental) {
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
		 * @return The name
		 */
		public String getName() {
			return name;
		}

		/**
		 * 
		 * @param name
		 *            The name
		 */
		public void setName(String name) {
			this.name = name;
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

}
