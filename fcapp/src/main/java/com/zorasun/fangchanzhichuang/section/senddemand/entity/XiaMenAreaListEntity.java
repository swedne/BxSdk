package com.zorasun.fangchanzhichuang.section.senddemand.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class XiaMenAreaListEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1466194759054997216L;

	private Content content;

	public void setContent(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return this.content;
	}

	public class Content {

		@Expose
		private List<AreaList> areaList = new ArrayList<AreaList>();
		@Expose
		private List<BusinessList> businessList = new ArrayList<BusinessList>();

		@Expose
		private List<HouseTypeList> houseTypeList = new ArrayList<HouseTypeList>();
		@Expose
		private List<XiamenInitList> xiamenInitList = new ArrayList<XiamenInitList>();

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
		 * @return The businessList
		 */
		public List<BusinessList> getBusinessList() {
			return businessList;
		}

		/**
		 * 
		 * @param businessList
		 *            The businessList
		 */
		public void setBusinessList(List<BusinessList> businessList) {
			this.businessList = businessList;
		}

		/**
		 * 
		 * @return The houseTypeList
		 */
		public List<HouseTypeList> getHouseTypeList() {
			return houseTypeList;
		}

		/**
		 * 
		 * @param houseTypeList
		 *            The houseTypeList
		 */
		public void setHouseTypeList(List<HouseTypeList> houseTypeList) {
			this.houseTypeList = houseTypeList;
		}

		/**
		 * 
		 * @return The xiamenInitList
		 */
		public List<XiamenInitList> getXiamenInitList() {
			return xiamenInitList;
		}

		/**
		 * 
		 * @param xiamenInitList
		 *            The xiamenInitList
		 */
		public void setXiamenInitList(List<XiamenInitList> xiamenInitList) {
			this.xiamenInitList = xiamenInitList;
		}

	}

	public class HouseTypeList {

		@Expose
		private String typeName;
		@Expose
		private Integer typeId;

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
		 * @return The typeId
		 */
		public Integer getTypeId() {
			return typeId;
		}

		/**
		 * 
		 * @param typeId
		 *            The typeId
		 */
		public void setTypeId(Integer typeId) {
			this.typeId = typeId;
		}

	}

	public class XiamenInitList {

		@Expose
		private Integer areaId;
		@Expose
		private String areaName;

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

	}

	public class AreaList {

		@Expose
		private Integer businessListId;
		@Expose
		private String businessListFirstLetter;
		@Expose
		private String businessListName;

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
		 * @return The businessListFirstLetter
		 */
		public String getBusinessListFirstLetter() {
			return businessListFirstLetter;
		}

		/**
		 * 
		 * @param businessListFirstLetter
		 *            The businessListFirstLetter
		 */
		public void setBusinessListFirstLetter(String businessListFirstLetter) {
			this.businessListFirstLetter = businessListFirstLetter;
		}

		/**
		 * 
		 * @return The businessListName
		 */
		public String getBusinessListName() {
			return businessListName;
		}

		/**
		 * 
		 * @param businessListName
		 *            The businessListName
		 */
		public void setBusinessListName(String businessListName) {
			this.businessListName = businessListName;
		}

	}

	public class BusinessList {

		@Expose
		private Integer areaListId;
		@Expose
		private String areaListName;

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

	}

}
