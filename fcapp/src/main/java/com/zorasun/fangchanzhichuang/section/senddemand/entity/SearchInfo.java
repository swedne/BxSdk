package com.zorasun.fangchanzhichuang.section.senddemand.entity;

public class SearchInfo {

	public SearchInfo(int areaId, String areaName, int areaListId, int businessListId, String businessListName,
			String areaListName) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.areaListId = areaListId;
		this.businessListId = businessListId;
		this.businessListName = businessListName;
		this.areaListName = areaListName;
	}

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
