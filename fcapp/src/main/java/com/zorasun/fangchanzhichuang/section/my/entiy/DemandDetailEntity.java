package com.zorasun.fangchanzhichuang.section.my.entiy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class DemandDetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 493714423828654136L;
	private Content content;

	public void setContent(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return this.content;
	}

	public class Content implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1614660799106711360L;
		private AppraiseInfo appraiseInfo;

		public AppraiseInfo getAppraiseInfo() {
			return appraiseInfo;
		}

		public void setAppraiseInfo(AppraiseInfo appraiseInfo) {
			this.appraiseInfo = appraiseInfo;
		}

		private ArrayList<SpecialtyList> specialtyList = new ArrayList<>();

		public ArrayList<SpecialtyList> getSpecialtyList() {
			return specialtyList;
		}

		public void setSpecialtyList(ArrayList<SpecialtyList> specialtyList) {
			this.specialtyList = specialtyList;
		}

		@Expose
		private HouseResourceListDetail houseResourceListDetail;

		@Expose
		private String developers;
		@Expose
		private Integer businessId;
		@Expose
		private String businessName;
		@Expose
		private String headUrl;
		@Expose
		private String remark;
		@Expose
		private String brokerMobile;
		@Expose
		private Integer demandId;
		@Expose
		private Integer roomNum;
		@Expose
		private String areaName;
		@Expose
		private String price;
		@Expose
		private String tel;
		@Expose
		private Integer toiletNum;
		@Expose
		private Integer state;
		@Expose
		private String uniqueNum;

		public void setHouseResourceListDetail(HouseResourceListDetail houseResourceListDetail) {
			this.houseResourceListDetail = houseResourceListDetail;
		}

		@Expose
		private String demandTypeName;
		@Expose
		private Integer isExpert;
		@Expose
		private Integer brokerId;
		@Expose
		private String mobile;
		@Expose
		private String houseTypeName;
		@Expose
		private ServiceBusiness serviceBusiness;
		@Expose
		private String userName;
		@Expose
		private Object userId;
		@Expose
		private String square;
		@Expose
		private String realName;
		@Expose
		private String harkBackHouse;
		@Expose
		private Integer areaId;
		@Expose
		private List<HouseResDemandProList> houseResDemandProList = new ArrayList<HouseResDemandProList>();
		@Expose
		private long demandDate;
		@Expose
		private String brokerName;
		@Expose
		private Integer hallNum;

		/**
		 * 
		 * @return The developers
		 */
		public String getDevelopers() {
			return developers;
		}

		/**
		 * 
		 * @param developers
		 *            The developers
		 */
		public void setDevelopers(String developers) {
			this.developers = developers;
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
		 * @return The remark
		 */
		public String getRemark() {
			return remark;
		}

		/**
		 * 
		 * @param remark
		 *            The remark
		 */
		public void setRemark(String remark) {
			this.remark = remark;
		}

		/**
		 * 
		 * @return The brokerMobile
		 */
		public String getBrokerMobile() {
			return brokerMobile;
		}

		/**
		 * 
		 * @param brokerMobile
		 *            The brokerMobile
		 */
		public void setBrokerMobile(String brokerMobile) {
			this.brokerMobile = brokerMobile;
		}

		/**
		 * 
		 * @return The demandId
		 */
		public Integer getDemandId() {
			return demandId;
		}

		/**
		 * 
		 * @param demandId
		 *            The demandId
		 */
		public void setDemandId(Integer demandId) {
			this.demandId = demandId;
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
		 * @return The tel
		 */
		public String getTel() {
			return tel;
		}

		public HouseResourceListDetail getHouseResourceListDetail() {
			return houseResourceListDetail;
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
		 * @return The state
		 */
		public Integer getState() {
			return state;
		}

		/**
		 * 
		 * @param state
		 *            The state
		 */
		public void setState(Integer state) {
			this.state = state;
		}

		/**
		 * 
		 * @return The uniqueNum
		 */
		public String getUniqueNum() {
			return uniqueNum;
		}

		/**
		 * 
		 * @param uniqueNum
		 *            The uniqueNum
		 */
		public void setUniqueNum(String uniqueNum) {
			this.uniqueNum = uniqueNum;
		}

		/**
		 * 
		 * @return The demandTypeName
		 */
		public String getDemandTypeName() {
			return demandTypeName;
		}

		/**
		 * 
		 * @param demandTypeName
		 *            The demandTypeName
		 */
		public void setDemandTypeName(String demandTypeName) {
			this.demandTypeName = demandTypeName;
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
		 * @return The brokerId
		 */
		public Integer getBrokerId() {
			return brokerId;
		}

		/**
		 * 
		 * @param brokerId
		 *            The brokerId
		 */
		public void setBrokerId(Integer brokerId) {
			this.brokerId = brokerId;
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
		 * @return The serviceBusiness
		 */
		public ServiceBusiness getServiceBusiness() {
			return serviceBusiness;
		}

		/**
		 * 
		 * @param serviceBusiness
		 *            The serviceBusiness
		 */
		public void setServiceBusiness(ServiceBusiness serviceBusiness) {
			this.serviceBusiness = serviceBusiness;
		}

		/**
		 * 
		 * @return The userName
		 */
		public String getUserName() {
			return userName;
		}

		/**
		 * 
		 * @param userName
		 *            The userName
		 */
		public void setUserName(String userName) {
			this.userName = userName;
		}

		/**
		 * 
		 * @return The userId
		 */
		public Object getUserId() {
			return userId;
		}

		/**
		 * 
		 * @param userId
		 *            The userId
		 */
		public void setUserId(Object userId) {
			this.userId = userId;
		}

		/**
		 * 
		 * @return The square
		 */
		public String getSquare() {
			return square;
		}

		/**
		 * 
		 * @param square
		 *            The square
		 */
		public void setSquare(String square) {
			this.square = square;
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
		 * @return The harkBackHouse
		 */
		public String getHarkBackHouse() {
			return harkBackHouse;
		}

		/**
		 * 
		 * @param harkBackHouse
		 *            The harkBackHouse
		 */
		public void setHarkBackHouse(String harkBackHouse) {
			this.harkBackHouse = harkBackHouse;
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
		 * @return The houseResDemandProList
		 */
		public List<HouseResDemandProList> getHouseResDemandProList() {
			return houseResDemandProList;
		}

		/**
		 * 
		 * @param houseResDemandProList
		 *            The houseResDemandProList
		 */
		public void setHouseResDemandProList(List<HouseResDemandProList> houseResDemandProList) {
			this.houseResDemandProList = houseResDemandProList;
		}

		/**
		 * 
		 * @return The demandDate
		 */
		public long getDemandDate() {
			return demandDate;
		}

		/**
		 * 
		 * @param demandDate
		 *            The demandDate
		 */
		public void setDemandDate(long demandDate) {
			this.demandDate = demandDate;
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

	public class ServiceBusiness implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1502142907976558782L;
		private String businessName;
		private String hardBackHouse;

		public String getBusinessName() {
			return businessName;
		}

		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

		public String getHardBackHouse() {
			return hardBackHouse;
		}

		public void setHardBackHouse(String hardBackHouse) {
			this.hardBackHouse = hardBackHouse;
		}
	}

	public class HouseResDemandProList implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -789227586834762407L;
		private long lookData;
		private String content;

		public long getLookData() {
			return lookData;
		}

		public void setLookData(long lookData) {
			this.lookData = lookData;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}

	public class HouseResourceListDetail implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1993728797930661974L;
		private int isAuth;
		@Expose
		private Object houseAuthCode;
		@Expose
		private Integer isShowImage;
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
		private String plantAcreage;
		@Expose
		private Integer houseTypeId;
		@Expose
		private String berryGG;
		@Expose
		private Integer floorNum;
		@Expose
		private String picUrl;

		private String surFaceUrl;

		@Expose
		private Integer roomNum;
		@Expose
		private String areaName;
		@Expose
		private long price;

		private Integer unitPrice;
		@Expose
		private Integer id;
		@Expose
		private String payName;
		@Expose
		private Object houseBarCode;
		@Expose
		private String address;
		@Expose
		private Object officeTypeName;
		@Expose
		private String buildTime;
		@Expose
		private Double rentMoney;
		@Expose
		private Object levelName;
		@Expose
		private String areaListName;
		@Expose
		private Object orientationName;
		@Expose
		private String shopTypeName;
		@Expose
		private Integer hallNum;

		public int getIsAuth() {
			return isAuth;
		}

		public void setIsAuth(int isAuth) {
			this.isAuth = isAuth;
		}

		public Integer getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(Integer unitPrice) {
			this.unitPrice = unitPrice;
		}

		private ArrayList<SpecialtyList> specialtyList = new ArrayList<>();

		public ArrayList<SpecialtyList> getSpecialtyList() {
			return specialtyList;
		}

		public void setSpecialtyList(ArrayList<SpecialtyList> specialtyList) {
			this.specialtyList = specialtyList;
		}

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
		public long getPrice() {
			return price;
		}

		/**
		 * 
		 * @param price
		 *            The price
		 */
		public void setPrice(long price) {
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
		 * @return The payName
		 */
		public String getPayName() {
			return payName;
		}

		/**
		 * 
		 * @param payName
		 *            The payName
		 */
		public void setPayName(String payName) {
			this.payName = payName;
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
		public Double getRentMoney() {
			return rentMoney;
		}

		/**
		 * 
		 * @param rentMoney
		 *            The rentMoney
		 */
		public void setRentMoney(Double rentMoney) {
			this.rentMoney = rentMoney;
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

	public class SpecialtyList implements Serializable {
		private String specialtyName;

		public String getSpecialtyName() {
			return specialtyName;
		}

		public void setSpecialtyName(String specialtyName) {
			this.specialtyName = specialtyName;
		}

	}

	public class AppraiseInfo implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3418917790903061352L;
		private String appraiseTime;
		private int service;
		private int appraiseId;
		private int validity;
		private String appraiseContent;
		private int professional;

		public String getAppraiseTime() {
			return appraiseTime;
		}

		public void setAppraiseTime(String appraiseTime) {
			this.appraiseTime = appraiseTime;
		}

		public int getService() {
			return service;
		}

		public void setService(int service) {
			this.service = service;
		}

		public int getAppraiseId() {
			return appraiseId;

		}

		public void setAppraiseId(int appraiseId) {
			this.appraiseId = appraiseId;
		}

		public int getValidity() {
			return validity;
		}

		public void setValidity(int validity) {
			this.validity = validity;
		}

		public String getAppraiseContent() {
			return appraiseContent;
		}

		public void setAppraiseContent(String appraiseContent) {
			this.appraiseContent = appraiseContent;
		}

		public int getProfessional() {
			return professional;
		}

		public void setProfessional(int professional) {
			this.professional = professional;
		}

	}

}
