package com.zorasun.fangchanzhichuang.section.senddemand.entity;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class AreaListInitEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1258372075197193663L;

	private Content content;

	public void setContent(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return this.content;
	}

	public class Content {

		private List<InitXiamenAreaListByNearby> initXiamenAreaListByNearby = new ArrayList<InitXiamenAreaListByNearby>();

		private List<InitXiamenAreaListByNearbyDiff> initXiamenAreaListByNearbyDiff = new ArrayList<InitXiamenAreaListByNearbyDiff>();

		/**
		 * 
		 * @return The initXiamenAreaListByNearby
		 */
		public List<InitXiamenAreaListByNearby> getInitXiamenAreaListByNearby() {
			return initXiamenAreaListByNearby;
		}

		/**
		 * 
		 * @param initXiamenAreaListByNearby
		 *            The initXiamenAreaListByNearby
		 */
		public void setInitXiamenAreaListByNearby(List<InitXiamenAreaListByNearby> initXiamenAreaListByNearby) {
			this.initXiamenAreaListByNearby = initXiamenAreaListByNearby;
		}

		/**
		 * 
		 * @return The initXiamenAreaListByNearbyDiff
		 */
		public List<InitXiamenAreaListByNearbyDiff> getInitXiamenAreaListByNearbyDiff() {
			return initXiamenAreaListByNearbyDiff;
		}

		/**
		 * 
		 * @param initXiamenAreaListByNearbyDiff
		 *            The initXiamenAreaListByNearbyDiff
		 */
		public void setInitXiamenAreaListByNearbyDiff(
				List<InitXiamenAreaListByNearbyDiff> initXiamenAreaListByNearbyDiff) {
			this.initXiamenAreaListByNearbyDiff = initXiamenAreaListByNearbyDiff;
		}

	}

	public class InitXiamenAreaListByNearby {

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

	public class InitXiamenAreaListByNearbyDiff {

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
