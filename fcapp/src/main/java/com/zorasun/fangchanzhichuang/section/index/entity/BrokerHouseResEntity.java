package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class BrokerHouseResEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8898461183640954444L;

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
		private Broker broker;
		@Expose
		private Integer brokerId;
		@Expose
		private String brokerName;
		@Expose
		private Integer cSize;
		@Expose
		private List<CertificateAuthenticList> certificateAuthenticList = new ArrayList<CertificateAuthenticList>();
		@Expose
		private List<HouseList> houseList = new ArrayList<HouseList>();
		@Expose
		private String houseTypeName;
		@Expose
		private Integer isActtention;
		@Expose
		private Integer pageCount;

		/**
		 * 
		 * @return The broker
		 */
		public Broker getBroker() {
			return broker;
		}

		/**
		 * 
		 * @param broker
		 *            The broker
		 */
		public void setBroker(Broker broker) {
			this.broker = broker;
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
		 * @return The cSize
		 */
		public Integer getCSize() {
			return cSize;
		}

		/**
		 * 
		 * @param cSize
		 *            The cSize
		 */
		public void setCSize(Integer cSize) {
			this.cSize = cSize;
		}

		/**
		 * 
		 * @return The certificateAuthenticList
		 */
		public List<CertificateAuthenticList> getCertificateAuthenticList() {
			return certificateAuthenticList;
		}

		/**
		 * 
		 * @param certificateAuthenticList
		 *            The certificateAuthenticList
		 */
		public void setCertificateAuthenticList(List<CertificateAuthenticList> certificateAuthenticList) {
			this.certificateAuthenticList = certificateAuthenticList;
		}

		/**
		 * 
		 * @return The houseList
		 */
		public List<HouseList> getHouseList() {
			return houseList;
		}

		/**
		 * 
		 * @param houseList
		 *            The houseList
		 */
		public void setHouseList(List<HouseList> houseList) {
			this.houseList = houseList;
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
		 * @return The isActtention
		 */
		public Integer getIsActtention() {
			return isActtention;
		}

		/**
		 * 
		 * @param isActtention
		 *            The isActtention
		 */
		public void setIsActtention(Integer isActtention) {
			this.isActtention = isActtention;
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

	}

	public class AuMap {

		@Expose
		private IsfjBrokerIdentify isfjBrokerIdentify;

		/**
		 * 
		 * @return The isfjBrokerIdentify
		 */
		public IsfjBrokerIdentify getIsfjBrokerIdentify() {
			return isfjBrokerIdentify;
		}

		/**
		 * 
		 * @param isfjBrokerIdentify
		 *            The isfjBrokerIdentify
		 */
		public void setIsfjBrokerIdentify(IsfjBrokerIdentify isfjBrokerIdentify) {
			this.isfjBrokerIdentify = isfjBrokerIdentify;
		}

	}

	public class Broker {

		@Expose
		private Integer rentHouseRes;
		@Expose
		private String rentRes;
		@Expose
		private AuMap auMap;
		@Expose
		private String headUrl;
		@Expose
		private double professional;
		@Expose
		private Integer practiceTime;
		@Expose
		private Integer uid;
		@Expose
		private String oldHouseRes;
		@Expose
		private List<SpecialSkillList> specialSkillList = new ArrayList<SpecialSkillList>();
		@Expose
		private Integer id;
		@Expose
		private String qrcodeUrl;
		@Expose
		private String qq;
		@Expose
		private BusinessService businessService;
		@Expose
		private String personalDetail;
		@Expose
		private Integer IsExpert;
		@Expose
		private String identifyNo;
		@Expose
		private String mobile;
		@Expose
		private String userName;
		@Expose
		private String realName;
		@Expose
		private String harkBackHouse;
		@Expose
		private double service;
		@Expose
		private Integer secondHouseRes;
		@Expose
		private double validity;
		@Expose
		private String brokerName;
		@Expose
		private Integer isActivate;
		@Expose
		private Integer status;

		/**
		 * 
		 * @return The rentHouseRes
		 */
		public Integer getRentHouseRes() {
			return rentHouseRes;
		}

		/**
		 * 
		 * @param rentHouseRes
		 *            The rentHouseRes
		 */
		public void setRentHouseRes(Integer rentHouseRes) {
			this.rentHouseRes = rentHouseRes;
		}

		/**
		 * 
		 * @return The rentRes
		 */
		public String getRentRes() {
			return rentRes;
		}

		/**
		 * 
		 * @param rentRes
		 *            The rentRes
		 */
		public void setRentRes(String rentRes) {
			this.rentRes = rentRes;
		}

		/**
		 * 
		 * @return The auMap
		 */
		public AuMap getAuMap() {
			return auMap;
		}

		/**
		 * 
		 * @param auMap
		 *            The auMap
		 */
		public void setAuMap(AuMap auMap) {
			this.auMap = auMap;
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
		 * @return The professional
		 */
		public double getProfessional() {
			return professional;
		}

		/**
		 * 
		 * @param professional
		 *            The professional
		 */
		public void setProfessional(double professional) {
			this.professional = professional;
		}

		/**
		 * 
		 * @return The practiceTime
		 */
		public Integer getPracticeTime() {
			return practiceTime;
		}

		/**
		 * 
		 * @param practiceTime
		 *            The practiceTime
		 */
		public void setPracticeTime(Integer practiceTime) {
			this.practiceTime = practiceTime;
		}

		/**
		 * 
		 * @return The uid
		 */
		public Integer getUid() {
			return uid;
		}

		/**
		 * 
		 * @param uid
		 *            The uid
		 */
		public void setUid(Integer uid) {
			this.uid = uid;
		}

		/**
		 * 
		 * @return The oldHouseRes
		 */
		public String getOldHouseRes() {
			return oldHouseRes;
		}

		/**
		 * 
		 * @param oldHouseRes
		 *            The oldHouseRes
		 */
		public void setOldHouseRes(String oldHouseRes) {
			this.oldHouseRes = oldHouseRes;
		}

		/**
		 * 
		 * @return The specialSkillList
		 */
		public List<SpecialSkillList> getSpecialSkillList() {
			return specialSkillList;
		}

		/**
		 * 
		 * @param specialSkillList
		 *            The specialSkillList
		 */
		public void setSpecialSkillList(List<SpecialSkillList> specialSkillList) {
			this.specialSkillList = specialSkillList;
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
		 * @return The qrcodeUrl
		 */
		public String getQrcodeUrl() {
			return qrcodeUrl;
		}

		/**
		 * 
		 * @param qrcodeUrl
		 *            The qrcodeUrl
		 */
		public void setQrcodeUrl(String qrcodeUrl) {
			this.qrcodeUrl = qrcodeUrl;
		}

		/**
		 * 
		 * @return The qq
		 */
		public String getQq() {
			return qq;
		}

		/**
		 * 
		 * @param qq
		 *            The qq
		 */
		public void setQq(String qq) {
			this.qq = qq;
		}

		/**
		 * 
		 * @return The businessService
		 */
		public BusinessService getBusinessService() {
			return businessService;
		}

		/**
		 * 
		 * @param businessService
		 *            The businessService
		 */
		public void setBusinessService(BusinessService businessService) {
			this.businessService = businessService;
		}

		/**
		 * 
		 * @return The personalDetail
		 */
		public String getPersonalDetail() {
			return personalDetail;
		}

		/**
		 * 
		 * @param personalDetail
		 *            The personalDetail
		 */
		public void setPersonalDetail(String personalDetail) {
			this.personalDetail = personalDetail;
		}

		/**
		 * 
		 * @return The IsExpert
		 */
		public Integer getIsExpert() {
			return IsExpert;
		}

		/**
		 * 
		 * @param IsExpert
		 *            The IsExpert
		 */
		public void setIsExpert(Integer IsExpert) {
			this.IsExpert = IsExpert;
		}

		/**
		 * 
		 * @return The identifyNo
		 */
		public String getIdentifyNo() {
			return identifyNo;
		}

		/**
		 * 
		 * @param identifyNo
		 *            The identifyNo
		 */
		public void setIdentifyNo(String identifyNo) {
			this.identifyNo = identifyNo;
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
		 * @return The service
		 */
		public double getService() {
			return service;
		}

		/**
		 * 
		 * @param service
		 *            The service
		 */
		public void setService(double service) {
			this.service = service;
		}

		/**
		 * 
		 * @return The secondHouseRes
		 */
		public Integer getSecondHouseRes() {
			return secondHouseRes;
		}

		/**
		 * 
		 * @param secondHouseRes
		 *            The secondHouseRes
		 */
		public void setSecondHouseRes(Integer secondHouseRes) {
			this.secondHouseRes = secondHouseRes;
		}

		/**
		 * 
		 * @return The validity
		 */
		public double getValidity() {
			return validity;
		}

		/**
		 * 
		 * @param validity
		 *            The validity
		 */
		public void setValidity(double validity) {
			this.validity = validity;
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
		 * @return The status
		 */
		public Integer getStatus() {
			return status;
		}

		/**
		 * 
		 * @param status
		 *            The status
		 */
		public void setStatus(Integer status) {
			this.status = status;
		}

	}

	public class CertificateAuthenticList {

		@Expose
		private Object isEducation;
		@Expose
		private Integer state;
		@Expose
		private Integer type;
		@Expose
		private String url;

		/**
		 * 
		 * @return The isEducation
		 */
		public Object getIsEducation() {
			return isEducation;
		}

		/**
		 * 
		 * @param isEducation
		 *            The isEducation
		 */
		public void setIsEducation(Object isEducation) {
			this.isEducation = isEducation;
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
		 * @return The type
		 */
		public Integer getType() {
			return type;
		}

		/**
		 * 
		 * @param type
		 *            The type
		 */
		public void setType(Integer type) {
			this.type = type;
		}

		/**
		 * 
		 * @return The url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * 
		 * @param url
		 *            The url
		 */
		public void setUrl(String url) {
			this.url = url;
		}

	}

	public class HouseList {

		private String salePrice;

		public String getSalePrice() {
			return salePrice;
		}

		public void setSalePrice(String salePrice) {
			this.salePrice = salePrice;
		}

		@Expose
		private Object houseAuthCode;
		@Expose
		private String Pname;
		@Expose
		private String houseResourceName;
		@Expose
		private Integer isShowImage;
		@Expose
		private String surFaceUrl;
		@Expose
		private List<SpecialtyListForList> specialtyListForList = new ArrayList<SpecialtyListForList>();
		@Expose
		private String typeName;
		@Expose
		private Object businessName;
		@Expose
		private Integer areaListId;
		@Expose
		private String title;
		@Expose
		private double plantAcreage;
		@Expose
		private Integer houseTypeId;
		@Expose
		private String berryGG;
		@Expose
		private String floorNum;
		@Expose
		private String picUrl;
		@Expose
		private double totalAcreage;
		@Expose
		private Integer isAuth;
		@Expose
		private String roomNum;
		@Expose
		private String areaName;
		@Expose
		private String price;
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
		private double rentMoney;
		@Expose
		private String levelName;
		@Expose
		private String areaListName;
		@Expose
		private Integer rental;
		@Expose
		private String orientationName;
		@Expose
		private String floorSun;
		@Expose
		private String shopType;
		@Expose
		private String hallNum;

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
		 * @return The Pname
		 */
		public String getPname() {
			return Pname;
		}

		/**
		 * 
		 * @param Pname
		 *            The Pname
		 */
		public void setPname(String Pname) {
			this.Pname = Pname;
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
		public List<SpecialtyListForList> getSpecialtyListForList() {
			return specialtyListForList;
		}

		/**
		 * 
		 * @param specialtyListForList
		 *            The specialtyListForList
		 */
		public void setSpecialtyListForList(List<SpecialtyListForList> specialtyListForList) {
			this.specialtyListForList = specialtyListForList;
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
		public double getPlantAcreage() {
			return plantAcreage;
		}

		/**
		 * 
		 * @param plantAcreage
		 *            The plantAcreage
		 */
		public void setPlantAcreage(double plantAcreage) {
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
		 * @return The totalAcreage
		 */
		public double getTotalAcreage() {
			return totalAcreage;
		}

		/**
		 * 
		 * @param totalAcreage
		 *            The totalAcreage
		 */
		public void setTotalAcreage(double totalAcreage) {
			this.totalAcreage = totalAcreage;
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
		public double getRentMoney() {
			return rentMoney;
		}

		/**
		 * 
		 * @param rentMoney
		 *            The rentMoney
		 */
		public void setRentMoney(double rentMoney) {
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
		public String getFloorSun() {
			return floorSun;
		}

		/**
		 * 
		 * @param floorSun
		 *            The floorSun
		 */
		public void setFloorSun(String floorSun) {
			this.floorSun = floorSun;
		}

		/**
		 * 
		 * @return The shopType
		 */
		public String getShopType() {
			return shopType;
		}

		/**
		 * 
		 * @param shopType
		 *            The shopType
		 */
		public void setShopType(String shopType) {
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

	public class IsfjBrokerIdentify {

		@Expose
		private Object isEducation;
		@Expose
		private Integer state;
		@Expose
		private Integer type;
		@Expose
		private String url;

		/**
		 * 
		 * @return The isEducation
		 */
		public Object getIsEducation() {
			return isEducation;
		}

		/**
		 * 
		 * @param isEducation
		 *            The isEducation
		 */
		public void setIsEducation(Object isEducation) {
			this.isEducation = isEducation;
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
		 * @return The type
		 */
		public Integer getType() {
			return type;
		}

		/**
		 * 
		 * @param type
		 *            The type
		 */
		public void setType(Integer type) {
			this.type = type;
		}

		/**
		 * 
		 * @return The url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * 
		 * @param url
		 *            The url
		 */
		public void setUrl(String url) {
			this.url = url;
		}

	}

	public class SpecialSkillList {

		@Expose
		private Integer brId;
		@Expose
		private String speciaName;
		@Expose
		private Integer speciaskillId;

		/**
		 * 
		 * @return The brId
		 */
		public Integer getBrId() {
			return brId;
		}

		/**
		 * 
		 * @param brId
		 *            The brId
		 */
		public void setBrId(Integer brId) {
			this.brId = brId;
		}

		/**
		 * 
		 * @return The speciaName
		 */
		public String getSpeciaName() {
			return speciaName;
		}

		/**
		 * 
		 * @param speciaName
		 *            The speciaName
		 */
		public void setSpeciaName(String speciaName) {
			this.speciaName = speciaName;
		}

		/**
		 * 
		 * @return The speciaskillId
		 */
		public Integer getSpeciaskillId() {
			return speciaskillId;
		}

		/**
		 * 
		 * @param speciaskillId
		 *            The speciaskillId
		 */
		public void setSpeciaskillId(Integer speciaskillId) {
			this.speciaskillId = speciaskillId;
		}

	}

	public class BusinessService {

		@Expose
		private String businessName;
		@Expose
		private String hardBackHouse;

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
		 * @return The hardBackHouse
		 */
		public String getHardBackHouse() {
			return hardBackHouse;
		}

		/**
		 * 
		 * @param hardBackHouse
		 *            The hardBackHouse
		 */
		public void setHardBackHouse(String hardBackHouse) {
			this.hardBackHouse = hardBackHouse;
		}

	}

	public class SpecialtyListForList {
		private String specialName;
		private int specialId;
		private String specialtyName;

		public String getSpecialtyName() {
			return specialtyName;
		}

		public void setSpecialtyName(String specialtyName) {
			this.specialtyName = specialtyName;
		}

		public String getSpecialName() {
			return specialName;
		}

		public void setSpecialName(String specialName) {
			this.specialName = specialName;
		}

		public int getSpecialId() {
			return specialId;
		}

		public void setSpecialId(int specialId) {
			this.specialId = specialId;
		}

	}

}
