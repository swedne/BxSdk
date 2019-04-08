package com.zorasun.fangchanzhichuang.section.my.entiy;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class MyAttentionListEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6989580155122196789L;
	private Content content;

	public void setContent(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return this.content;
	}

	public class Content {

		@Expose
		private Integer pageCount;
		@Expose
		private Integer size;
		@Expose
		private List<AttentionList_> attentionList = new ArrayList<AttentionList_>();
		@Expose
		private Integer pageNum;

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
		 * @return The size
		 */
		public Integer getSize() {
			return size;
		}

		/**
		 * 
		 * @param size
		 *            The size
		 */
		public void setSize(Integer size) {
			this.size = size;
		}

		/**
		 * 
		 * @return The attentionList
		 */
		public List<AttentionList_> getAttentionList() {
			return attentionList;
		}

		/**
		 * 
		 * @param attentionList
		 *            The attentionList
		 */
		public void setAttentionList(List<AttentionList_> attentionList) {
			this.attentionList = attentionList;
		}

		/**
		 * 
		 * @return The pageNum
		 */
		public Integer getPageNum() {
			return pageNum;
		}

		/**
		 * 
		 * @param pageNum
		 *            The pageNum
		 */
		public void setPageNum(Integer pageNum) {
			this.pageNum = pageNum;
		}

	}

	public class AttentionList_ {

		@Expose
		private Integer isExpert;
		@Expose
		private BrokerInfo brokerInfo;
		@Expose
		private Integer brokerId;
		@Expose
		private Double goodChance;
		@Expose
		private String headUrl;
		@Expose
		private Integer id;
		@Expose
		private String brokerName;
		@Expose
		private Integer Aid;
		@Expose
		private Integer publicUserId;

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
		 * @return The brokerInfo
		 */
		public BrokerInfo getBrokerInfo() {
			return brokerInfo;
		}

		/**
		 * 
		 * @param brokerInfo
		 *            The brokerInfo
		 */
		public void setBrokerInfo(BrokerInfo brokerInfo) {
			this.brokerInfo = brokerInfo;
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
		 *            The broker_id
		 */
		public void setBrokerId(Integer brokerId) {
			this.brokerId = brokerId;
		}

		/**
		 * 
		 * @return The goodChance
		 */
		public Double getGoodChance() {
			return goodChance;
		}

		/**
		 * 
		 * @param goodChance
		 *            The goodChance
		 */
		public void setGoodChance(Double goodChance) {
			this.goodChance = goodChance;
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
		 * @return The Aid
		 */
		public Integer getAid() {
			return Aid;
		}

		/**
		 * 
		 * @param Aid
		 *            The Aid
		 */
		public void setAid(Integer Aid) {
			this.Aid = Aid;
		}

		/**
		 * 
		 * @return The publicUserId
		 */
		public Integer getPublicUserId() {
			return publicUserId;
		}

		/**
		 * 
		 * @param publicUserId
		 *            The public_user_id
		 */
		public void setPublicUserId(Integer publicUserId) {
			this.publicUserId = publicUserId;
		}

	}

	public class BrokerInfo {

		@Expose
		private Integer brokerId;
		@Expose
		private Integer IsExpert;
		@Expose
		private Object latitude;
		@Expose
		private String headUrl;
		@Expose
		private String mobile;
		@Expose
		private String businessName;
		@Expose
		private Integer businessListId;
		@Expose
		private String userName;
		@Expose
		private Object areaListName;
		@Expose
		private Integer uid;
		@Expose
		private String realName;
		@Expose
		private String intermediaryName;
		@Expose
		private String harkBackHouse;
		@Expose
		private Integer areaid;
		@Expose
		private String areaName;
		@Expose
		private List<SpecialSkillList> specialSkillList = new ArrayList<SpecialSkillList>();
		@Expose
		private String brokerName;
		@Expose
		private Integer areaListNameNum;
		@Expose
		private Integer houseNum;
		@Expose
		private Object longitude;

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
		 * @return The latitude
		 */
		public Object getLatitude() {
			return latitude;
		}

		/**
		 * 
		 * @param latitude
		 *            The latitude
		 */
		public void setLatitude(Object latitude) {
			this.latitude = latitude;
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
		 * @return The areaListName
		 */
		public Object getAreaListName() {
			return areaListName;
		}

		/**
		 * 
		 * @param areaListName
		 *            The areaListName
		 */
		public void setAreaListName(Object areaListName) {
			this.areaListName = areaListName;
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
		 * @return The intermediaryName
		 */
		public String getIntermediaryName() {
			return intermediaryName;
		}

		/**
		 * 
		 * @param intermediaryName
		 *            The intermediaryName
		 */
		public void setIntermediaryName(String intermediaryName) {
			this.intermediaryName = intermediaryName;
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
		 * @return The areaid
		 */
		public Integer getAreaid() {
			return areaid;
		}

		/**
		 * 
		 * @param areaid
		 *            The areaid
		 */
		public void setAreaid(Integer areaid) {
			this.areaid = areaid;
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
		 * @return The areaListNameNum
		 */
		public Integer getAreaListNameNum() {
			return areaListNameNum;
		}

		/**
		 * 
		 * @param areaListNameNum
		 *            The areaListNameNum
		 */
		public void setAreaListNameNum(Integer areaListNameNum) {
			this.areaListNameNum = areaListNameNum;
		}

		/**
		 * 
		 * @return The houseNum
		 */
		public Integer getHouseNum() {
			return houseNum;
		}

		/**
		 * 
		 * @param houseNum
		 *            The houseNum
		 */
		public void setHouseNum(Integer houseNum) {
			this.houseNum = houseNum;
		}

		/**
		 * 
		 * @return The longitude
		 */
		public Object getLongitude() {
			return longitude;
		}

		/**
		 * 
		 * @param longitude
		 *            The longitude
		 */
		public void setLongitude(Object longitude) {
			this.longitude = longitude;
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

}
