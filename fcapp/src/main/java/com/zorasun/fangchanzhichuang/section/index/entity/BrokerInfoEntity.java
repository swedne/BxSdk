package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class BrokerInfoEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7737267545316983261L;

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

		private String wxUrlAndroid;

		public String getWxUrl() {
			return wxUrlAndroid;
		}

		public void setWxUrl(String wxUrl) {
			this.wxUrlAndroid = wxUrl;
		}

		@Expose
		private BrokerInfo_ brokerInfo;
		@Expose
		private List<RentHouseList> rentHouseList = new ArrayList<RentHouseList>();
		@Expose
		private List<SecondHouseList> secondHouseList = new ArrayList<SecondHouseList>();
		@Expose
		private List<CertificateAuthenticList> certificateAuthenticList = new ArrayList<CertificateAuthenticList>();
		@Expose
		private Integer cSize;

		/**
		 * 
		 * @return The brokerInfo
		 */
		public BrokerInfo_ getBrokerInfo() {
			return brokerInfo;
		}

		/**
		 * 
		 * @param brokerInfo
		 *            The brokerInfo
		 */
		public void setBrokerInfo(BrokerInfo_ brokerInfo) {
			this.brokerInfo = brokerInfo;
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

	}

	public class AuMap {

		@Expose
		private IsfjBrokerIdentify isfjBrokerIdentify;
		@Expose
		private IsxmQualityIdentify isxmQualityIdentify;

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

		/**
		 * 
		 * @return The isxmQualityIdentify
		 */
		public IsxmQualityIdentify getIsxmQualityIdentify() {
			return isxmQualityIdentify;
		}

		/**
		 * 
		 * @param isxmQualityIdentify
		 *            The isxmQualityIdentify
		 */
		public void setIsxmQualityIdentify(IsxmQualityIdentify isxmQualityIdentify) {
			this.isxmQualityIdentify = isxmQualityIdentify;
		}

	}

	public class BrokerInfo_ {

		private String wxUrl;

		public String getWxUrl() {
			return wxUrl;
		}

		public void setWxUrl(String wxUrl) {
			this.wxUrl = wxUrl;
		}

		private String businessName;

		public String getBusinessName() {
			return businessName;
		}

		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

		@Expose
		private Integer rentRes;
		@Expose
		private AuMap auMap;
		@Expose
		private Integer isActtention;
		@Expose
		private String headUrl;
		@Expose
		private Double professional;
		@Expose
		private Integer practiceTime;
		@Expose
		private Integer uid;
		@Expose
		private Integer oldHouseRes;
		@Expose
		private List<SpecialSkillList> specialSkillList = new ArrayList<SpecialSkillList>();
		@Expose
		private Integer attion;
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
		private Double service;
		@Expose
		private Double validity;
		@Expose
		private String brokerName;
		@Expose
		private Integer isActivate;
		@Expose
		private Integer status;

		/**
		 * 
		 * @return The rentRes
		 */
		public Integer getRentRes() {
			return rentRes;
		}

		/**
		 * 
		 * @param rentRes
		 *            The rentRes
		 */
		public void setRentRes(Integer rentRes) {
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
		public Double getProfessional() {
			return professional;
		}

		/**
		 * 
		 * @param professional
		 *            The professional
		 */
		public void setProfessional(Double professional) {
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
		public Integer getOldHouseRes() {
			return oldHouseRes;
		}

		/**
		 * 
		 * @param oldHouseRes
		 *            The oldHouseRes
		 */
		public void setOldHouseRes(Integer oldHouseRes) {
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
		 * @return The attion
		 */
		public Integer getAttion() {
			return attion;
		}

		/**
		 * 
		 * @param attion
		 *            The attion
		 */
		public void setAttion(Integer attion) {
			this.attion = attion;
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
		public Double getService() {
			return service;
		}

		/**
		 * 
		 * @param service
		 *            The service
		 */
		public void setService(Double service) {
			this.service = service;
		}

		/**
		 * 
		 * @return The validity
		 */
		public Double getValidity() {
			return validity;
		}

		/**
		 * 
		 * @param validity
		 *            The validity
		 */
		public void setValidity(Double validity) {
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

	public class CertificateAuthenticList {

		@Expose
		private Integer isEducation;
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
		public Integer getIsEducation() {
			return isEducation;
		}

		/**
		 * 
		 * @param isEducation
		 *            The isEducation
		 */
		public void setIsEducation(Integer isEducation) {
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

	public class IsfjBrokerIdentify {

		@Expose
		private Integer isEducation;
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
		public Integer getIsEducation() {
			return isEducation;
		}

		/**
		 * 
		 * @param isEducation
		 *            The isEducation
		 */
		public void setIsEducation(Integer isEducation) {
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

	public class IsxmQualityIdentify {

		@Expose
		private Integer isEducation;
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
		public Integer getIsEducation() {
			return isEducation;
		}

		/**
		 * 
		 * @param isEducation
		 *            The isEducation
		 */
		public void setIsEducation(Integer isEducation) {
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

	public class SecondHouseList {

		@Expose
		private Object houseAuthCode;
		@Expose
		private Object houseBarCode;
		@Expose
		private Double salePrice;
		@Expose
		private Integer isShowImage;
		@Expose
		private String surFaceUrl;
		@Expose
		private Integer alId;
		@Expose
		private Double plantAcreage;
		@Expose
		private Double berryGG;
		@Expose
		private Double rental;
		@Expose
		private Object picUrl;
		@Expose
		private Integer isAuth;
		@Expose
		private Integer roomNum;
		@Expose
		private Integer htid;
		@Expose
		private Integer id;
		@Expose
		private String tilte;
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
		 * @return The alId
		 */
		public Integer getAlId() {
			return alId;
		}

		/**
		 * 
		 * @param alId
		 *            The alId
		 */
		public void setAlId(Integer alId) {
			this.alId = alId;
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
		 * @return The htid
		 */
		public Integer getHtid() {
			return htid;
		}

		/**
		 * 
		 * @param htid
		 *            The htid
		 */
		public void setHtid(Integer htid) {
			this.htid = htid;
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
		 * @return The tilte
		 */
		public String getTilte() {
			return tilte;
		}

		/**
		 * 
		 * @param tilte
		 *            The tilte
		 */
		public void setTilte(String tilte) {
			this.tilte = tilte;
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

	public class RentHouseList {

		@Expose
		private Object houseAuthCode;
		@Expose
		private Object houseBarCode;
		@Expose
		private Double salePrice;
		@Expose
		private Integer isShowImage;
		@Expose
		private String surFaceUrl;
		@Expose
		private Integer alId;
		@Expose
		private Double plantAcreage;
		@Expose
		private Double berryGG;
		@Expose
		private Double rental;
		@Expose
		private Object picUrl;
		@Expose
		private Integer isAuth;
		@Expose
		private Integer roomNum;
		@Expose
		private Integer htid;
		@Expose
		private Integer id;
		@Expose
		private String tilte;
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
		 * @return The alId
		 */
		public Integer getAlId() {
			return alId;
		}

		/**
		 * 
		 * @param alId
		 *            The alId
		 */
		public void setAlId(Integer alId) {
			this.alId = alId;
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
		 * @return The htid
		 */
		public Integer getHtid() {
			return htid;
		}

		/**
		 * 
		 * @param htid
		 *            The htid
		 */
		public void setHtid(Integer htid) {
			this.htid = htid;
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
		 * @return The tilte
		 */
		public String getTilte() {
			return tilte;
		}

		/**
		 * 
		 * @param tilte
		 *            The tilte
		 */
		public void setTilte(String tilte) {
			this.tilte = tilte;
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
