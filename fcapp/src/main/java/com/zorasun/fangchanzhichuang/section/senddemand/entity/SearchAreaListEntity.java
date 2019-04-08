package com.zorasun.fangchanzhichuang.section.senddemand.entity;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class SearchAreaListEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 206266040640325050L;
	private Content content;

	public void setContent(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return this.content;
	}

	public class Content {
		public List<XiamenBusinessList> getXiamenBusinessList() {
			return xiamenBusinessList;
		}

		public void setXiamenBusinessList(List<XiamenBusinessList> xiamenBusinessList) {
			this.xiamenBusinessList = xiamenBusinessList;
		}

		private List<XiamenAreaList> xiamenAreaList = new ArrayList<XiamenAreaList>();
		private List<XiamenBusinessList> xiamenBusinessList = new ArrayList<XiamenBusinessList>();

		public List<XiamenAreaList> getXiamenAreaList() {
			return xiamenAreaList;
		}

		public void setInitXiamenAreaListByNearby(List<XiamenAreaList> xiamenAreaList) {
			this.xiamenAreaList = xiamenAreaList;
		}

		public void setXiamenAreaList(List<XiamenAreaList> xiamenAreaList) {
			this.xiamenAreaList = xiamenAreaList;
		}

	}

	public class XiamenBusinessList {
		private Integer areaId;
		private String newHouseName;
		private String areaName;
		private Integer businessListId;
		private Integer id;
		private String businessListName;

		public Integer getAreaId() {
			return areaId;
		}

		public void setAreaId(Integer areaId) {
			this.areaId = areaId;
		}

		public String getNewHouseName() {
			return newHouseName;
		}

		public void setNewHouseName(String newHouseName) {
			this.newHouseName = newHouseName;
		}

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public Integer getBusinessListId() {
			return businessListId;
		}

		public void setBusinessListId(Integer businessListId) {
			this.businessListId = businessListId;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getBusinessListName() {
			return businessListName;
		}

		public void setBusinessListName(String businessListName) {
			this.businessListName = businessListName;
		}

	}

	public class XiamenAreaList {
		private Integer areaId;

		private String areaName;

		private Integer areaListId;

		private Integer businessListId;

		private String businessListName;

		private String areaListName;

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
