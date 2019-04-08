package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class AroundBrokerListEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3380437541412802543L;

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
		private List<BorkList> borkList = new ArrayList<BorkList>();
		@Expose
		private Integer brokerListSize;

		/**
		 * 
		 * @return The borkList
		 */
		public List<BorkList> getBorkList() {
			return borkList;
		}

		/**
		 * 
		 * @param borkList
		 *            The borkList
		 */
		public void setBorkList(List<BorkList> borkList) {
			this.borkList = borkList;
		}

		/**
		 * 
		 * @return The brokerListSize
		 */
		public Integer getBrokerListSize() {
			return brokerListSize;
		}

		/**
		 * 
		 * @param brokerListSize
		 *            The brokerListSize
		 */
		public void setBrokerListSize(Integer brokerListSize) {
			this.brokerListSize = brokerListSize;
		}

	}

	public class Aroundbrokerlist {

		@Expose
		private Integer code;
		@Expose
		private String msg;
		@Expose
		private Content content;

		/**
		 * 
		 * @return The code
		 */
		public Integer getCode() {
			return code;
		}

		/**
		 * 
		 * @param code
		 *            The code
		 */
		public void setCode(Integer code) {
			this.code = code;
		}

		/**
		 * 
		 * @return The msg
		 */
		public String getMsg() {
			return msg;
		}

		/**
		 * 
		 * @param msg
		 *            The msg
		 */
		public void setMsg(String msg) {
			this.msg = msg;
		}

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

	}

	public class BorkList {

		@Expose
		private Integer brokerId;
		@Expose
		private Integer IsExpert;
		@Expose
		private String headUrl;
		@Expose
		private String mobile;
		@Expose
		private String businessName;
		private String businessListName;

		public String getBusinessListName() {
			return businessListName;
		}

		public void setBusinessListName(String businessListName) {
			this.businessListName = businessListName;
		}

		@Expose
		private String Aname;
		@Expose
		private String userName;
		@Expose
		private String harkBackHouseIds;
		@Expose
		private Integer uid;
		@Expose
		private String realName;
		@Expose
		private String harkBackHouse;
		@Expose
		private Integer integra;
		@Expose
		private List<SpecialSkillList> specialSkillList = new ArrayList<SpecialSkillList>();
		@Expose
		private String brokerName;
		@Expose
		private List<BrokerBuxinessConf> brokerBuxinessConf = new ArrayList<BrokerBuxinessConf>();
		@Expose
		private Object scId;

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
		 * @return The Aname
		 */
		public String getAname() {
			return Aname;
		}

		/**
		 * 
		 * @param Aname
		 *            The Aname
		 */
		public void setAname(String Aname) {
			this.Aname = Aname;
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
		 * @return The harkBackHouseIds
		 */
		public String getHarkBackHouseIds() {
			return harkBackHouseIds;
		}

		/**
		 * 
		 * @param harkBackHouseIds
		 *            The hark_back_house_ids
		 */
		public void setHarkBackHouseIds(String harkBackHouseIds) {
			this.harkBackHouseIds = harkBackHouseIds;
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
		 * @return The integra
		 */
		public Integer getIntegra() {
			return integra;
		}

		/**
		 * 
		 * @param integra
		 *            The integra
		 */
		public void setIntegra(Integer integra) {
			this.integra = integra;
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
		 * @return The brokerBuxinessConf
		 */
		public List<BrokerBuxinessConf> getBrokerBuxinessConf() {
			return brokerBuxinessConf;
		}

		/**
		 * 
		 * @param brokerBuxinessConf
		 *            The brokerBuxinessConf
		 */
		public void setBrokerBuxinessConf(List<BrokerBuxinessConf> brokerBuxinessConf) {
			this.brokerBuxinessConf = brokerBuxinessConf;
		}

		/**
		 * 
		 * @return The scId
		 */
		public Object getScId() {
			return scId;
		}

		/**
		 * 
		 * @param scId
		 *            The scId
		 */
		public void setScId(Object scId) {
			this.scId = scId;
		}

	}

	public class BrokerBuxinessConf {

		@Expose
		private Integer brokerId;
		@Expose
		private String newareaName;
		@Expose
		private String busName;
		@Expose
		private Integer businesslistId;

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
		 * @return The newareaName
		 */
		public String getNewareaName() {
			return newareaName;
		}

		/**
		 * 
		 * @param newareaName
		 *            The newareaName
		 */
		public void setNewareaName(String newareaName) {
			this.newareaName = newareaName;
		}

		/**
		 * 
		 * @return The busName
		 */
		public String getBusName() {
			return busName;
		}

		/**
		 * 
		 * @param busName
		 *            The busName
		 */
		public void setBusName(String busName) {
			this.busName = busName;
		}

		/**
		 * 
		 * @return The businesslistId
		 */
		public Integer getBusinesslistId() {
			return businesslistId;
		}

		/**
		 * 
		 * @param businesslistId
		 *            The businesslist_id
		 */
		public void setBusinesslistId(Integer businesslistId) {
			this.businesslistId = businesslistId;
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
