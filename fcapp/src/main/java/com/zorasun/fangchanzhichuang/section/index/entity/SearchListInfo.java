package com.zorasun.fangchanzhichuang.section.index.entity;

import java.io.Serializable;

public class SearchListInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6932197756950839262L;
	private int areaId;
	private double areaLongitude;
	private String areaName;
	private int areaListId;
	private int houseNumByArea;
	private int businessListId;
	private String businessListName;
	private double areaLatitude;
	private String areaListName;

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public double getAreaLongitude() {
		return areaLongitude;
	}

	public void setAreaLongitude(double areaLongitude) {
		this.areaLongitude = areaLongitude;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getAreaListId() {
		return areaListId;
	}

	public void setAreaListId(int areaListId) {
		this.areaListId = areaListId;
	}

	public int getHouseNumByArea() {
		return houseNumByArea;
	}

	public void setHouseNumByArea(int houseNumByArea) {
		this.houseNumByArea = houseNumByArea;
	}

	public int getBusinessListId() {
		return businessListId;
	}

	public void setBusinessListId(int businessListId) {
		this.businessListId = businessListId;
	}

	public String getBusinessListName() {
		return businessListName;
	}

	public void setBusinessListName(String businessListName) {
		this.businessListName = businessListName;
	}

	public double getAreaLatitude() {
		return areaLatitude;
	}

	public void setAreaLatitude(double areaLatitude) {
		this.areaLatitude = areaLatitude;
	}

	public String getAreaListName() {
		return areaListName;
	}

	public void setAreaListName(String areaListName) {
		this.areaListName = areaListName;
	}

}
