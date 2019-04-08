package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity.AuMap;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity.BusinessService;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity.SpecialSkillList;

public class BrokerListEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4288678579199460958L;
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
		private List<AllSkillList> AllSkillList = new ArrayList<AllSkillList>();
		@Expose
		private Integer pageCount;
		@Expose
		private List<AreaList> areaList = new ArrayList<AreaList>();
		@Expose
		private List<BrokerList> brokerList = new ArrayList<BrokerList>();

		/**
		 * 
		 * @return The AllSkillList
		 */
		public List<AllSkillList> getAllSkillList() {
			return AllSkillList;
		}

		/**
		 * 
		 * @param AllSkillList
		 *            The AllSkillList
		 */
		public void setAllSkillList(List<AllSkillList> AllSkillList) {
			this.AllSkillList = AllSkillList;
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

		/**
		 * 
		 * @return The brokerList
		 */
		public List<BrokerList> getBrokerList() {
			return brokerList;
		}

		/**
		 * 
		 * @param brokerList
		 *            The brokerList
		 */
		public void setBrokerList(List<BrokerList> brokerList) {
			this.brokerList = brokerList;
		}

	}

	public class AllSkillList {

		@SerializedName("specialskill_id")
		@Expose
		private Integer specialskillId;
		@SerializedName("special_name")
		@Expose
		private String specialName;

		/**
		 * 
		 * @return The specialskillId
		 */
		public Integer getSpecialskillId() {
			return specialskillId;
		}

		/**
		 * 
		 * @param specialskillId
		 *            The specialskill_id
		 */
		public void setSpecialskillId(Integer specialskillId) {
			this.specialskillId = specialskillId;
		}

		/**
		 * 
		 * @return The specialName
		 */
		public String getSpecialName() {
			return specialName;
		}

		/**
		 * 
		 * @param specialName
		 *            The special_name
		 */
		public void setSpecialName(String specialName) {
			this.specialName = specialName;
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

	public class BrokerList {

		@Expose
		private BusinessService businessService;
		@Expose
		private Integer brokerId;
		@Expose
		private Integer IsExpert;
		@Expose
		private AuMap auMap;
		@Expose
		private Object latitude;
		@Expose
		private String headUrl;
		@Expose
		private String mobile;
		@Expose
		private Integer businessId;
		@Expose
		private String businessName;
		@Expose
		private String userName;
		@Expose
		private Object areaListName;
		@Expose
		private Integer uid;
		@Expose
		private String realName;
		@Expose
		private String harkBackHouse;
		@Expose
		private Integer areaid;
		@Expose
		private String areaName;
		@Expose
		private String specialSkillList;
		@Expose
		private String specialName;
		@Expose
		private String brokerName;
		@Expose
		private Integer scId;
		@Expose
		private Integer areaListNameNum;
		@Expose
		private Integer houseNum;
		@Expose
		private Object longitude;

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
		public String getSpecialSkillList() {
			return specialSkillList;
		}

		/**
		 * 
		 * @param specialSkillList
		 *            The specialSkillList
		 */
		public void setSpecialSkillList(String specialSkillList) {
			this.specialSkillList = specialSkillList;
		}

		/**
		 * 
		 * @return The specialName
		 */
		public String getSpecialName() {
			return specialName;
		}

		/**
		 * 
		 * @param specialName
		 *            The specialName
		 */
		public void setSpecialName(String specialName) {
			this.specialName = specialName;
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
		 * @return The scId
		 */
		public Integer getScId() {
			return scId;
		}

		/**
		 * 
		 * @param scId
		 *            The scId
		 */
		public void setScId(Integer scId) {
			this.scId = scId;
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

}
