package com.zorasun.fangchanzhichuang.section.index.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class MapSearchEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3379083584859653820L;

	private Content content;
	private int pageCount;

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public class Content {

		private List<NewHouseNameList> newHouseNameList = new ArrayList<>();
		private List<AreaListNameList> areaListNameList = new ArrayList<>();
		private List<BusinessListNameList> businessListNameList = new ArrayList<>();
		private List<MapSearchHouseAreaInfoList> mapSearchHouseAreaInfoList = new ArrayList<>();
		private List<MapSearchNewHouseAreaInfoList> mapSearchNewHouseAreaInfoList = new ArrayList<>();
		private List<XiamenInitList> xiamenInitList = new ArrayList<>();

		public List<NewHouseNameList> getNewHouseNameList() {
			return newHouseNameList;
		}

		public void setNewHouseNameList(List<NewHouseNameList> newHouseNameList) {
			this.newHouseNameList = newHouseNameList;
		}

		public List<MapSearchNewHouseAreaInfoList> getMapSearchNewHouseAreaInfoList() {
			return mapSearchNewHouseAreaInfoList;
		}

		public void setMapSearchNewHouseAreaInfoList(
				List<MapSearchNewHouseAreaInfoList> mapSearchNewHouseAreaInfoList) {
			this.mapSearchNewHouseAreaInfoList = mapSearchNewHouseAreaInfoList;
		}

		private List<AreaList> areaList = new ArrayList<>();

		public List<AreaListNameList> getAreaListNameList() {
			return areaListNameList;
		}

		public void setAreaListNameList(List<AreaListNameList> areaListNameList) {
			this.areaListNameList = areaListNameList;
		}

		public List<BusinessListNameList> getBusinessListNameList() {
			return businessListNameList;
		}

		public void setBusinessListNameList(List<BusinessListNameList> businessListNameList) {
			this.businessListNameList = businessListNameList;
		}

		private int pageCount;

		public int getPageCount() {
			return pageCount;
		}

		public void setPageCount(int pageCount) {
			this.pageCount = pageCount;
		}

		public List<AreaList> getAreaList() {
			return areaList;
		}

		public List<MapSearchHouseAreaInfoList> getMapSearchHouseAreaInfoList() {
			return mapSearchHouseAreaInfoList;
		}

		public void setMapSearchHouseAreaInfoList(List<MapSearchHouseAreaInfoList> mapSearchHouseAreaInfoList) {
			this.mapSearchHouseAreaInfoList = mapSearchHouseAreaInfoList;
		}

		public void setAreaList(List<AreaList> areaList) {
			this.areaList = areaList;
		}

		public List<XiamenInitList> getXiamenInitList() {
			return xiamenInitList;
		}

		public void setXiamenInitList(List<XiamenInitList> xiamenInitList) {
			this.xiamenInitList = xiamenInitList;
		}

		private List<MapSearchList> mapSearchList = new ArrayList<>();

		public List<MapSearchList> getMapSearchList() {
			return mapSearchList;
		}

		public void setMapSearchList(List<MapSearchList> mapSearchList) {
			this.mapSearchList = mapSearchList;
		}

	}

	public class MapSearchList {
		private String plantAcreage;
		private double businessListLatitude;
		private double businessListLongitude;
		private int houseNumByBusinessList;
		private int businessListId;
		private String businessListName;
		private int areaId;
		private String areaName;
		private double latitude;
		private double longitude;
		private int areaListId;
		private int houseNumByAreaList;
		private int parkingNumByAreaList;
		private String areaListName;
		private int isAuth;
		private int rental;
		private int salePrice;
		private String title;
		private String surFaceUrl;
		private String berryGG;
		private String roomNum;
		private int price;
		private int id;
		private String hallNum;
		private int newHouseNumByBusinessList;
		private int buildingNumByBusinessList;
		private int parkingNumByBusinessList;
		private String newHouseName;
		private int averagePrice;
		private int newHouseId;
		private String url;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getSurFaceUrl() {
			return surFaceUrl;
		}

		public void setSurFaceUrl(String surFaceUrl) {
			this.surFaceUrl = surFaceUrl;
		}

		public String getPlantAcreage() {
			return plantAcreage;
		}

		public void setPlantAcreage(String plantAcreage) {
			this.plantAcreage = plantAcreage;
		}

		public int getParkingNumByAreaList() {
			return parkingNumByAreaList;
		}

		public void setParkingNumByAreaList(int parkingNumByAreaList) {
			this.parkingNumByAreaList = parkingNumByAreaList;
		}

		public int getParkingNumByBusinessList() {
			return parkingNumByBusinessList;
		}

		public void setParkingNumByBusinessList(int parkingNumByBusinessList) {
			this.parkingNumByBusinessList = parkingNumByBusinessList;
		}

		public int getNewHouseId() {
			return newHouseId;
		}

		public void setNewHouseId(int newHouseId) {
			this.newHouseId = newHouseId;
		}

		private List<SpecialtyList> specialtyList = new ArrayList<>();
		private List<SpecialsList> specialsList = new ArrayList<>();

		public List<SpecialsList> getSpecialsList() {
			return specialsList;
		}

		public void setSpecialsList(List<SpecialsList> specialsList) {
			this.specialsList = specialsList;
		}

		public int getAveragePrice() {
			return averagePrice;
		}

		public void setAveragePrice(int averagePrice) {
			this.averagePrice = averagePrice;
		}

		public String getNewHouseName() {
			return newHouseName;
		}

		public void setNewHouseName(String newHouseName) {
			this.newHouseName = newHouseName;
		}

		public int getNewHouseNumByBusinessList() {
			return newHouseNumByBusinessList;
		}

		public void setNewHouseNumByBusinessList(int newHouseNumByBusinessList) {
			this.newHouseNumByBusinessList = newHouseNumByBusinessList;
		}

		public int getBuildingNumByBusinessList() {
			return buildingNumByBusinessList;
		}

		public void setBuildingNumByBusinessList(int buildingNumByBusinessList) {
			this.buildingNumByBusinessList = buildingNumByBusinessList;
		}

		public Integer getRental() {
			return rental;
		}

		public void setRental(int rental) {
			this.rental = rental;
		}

		public int getIsAuth() {
			return isAuth;
		}

		public void setIsAuth(int isAuth) {
			this.isAuth = isAuth;
		}

		public Integer getSalePrice() {
			return salePrice;
		}

		public List<SpecialtyList> getSpecialtyList() {
			return specialtyList;
		}

		public void setSpecialtyList(List<SpecialtyList> specialtyList) {
			this.specialtyList = specialtyList;
		}

		public void setSalePrice(int salePrice) {
			this.salePrice = salePrice;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getBerryGG() {
			return berryGG;
		}

		public void setBerryGG(String berryGG) {
			this.berryGG = berryGG;
		}

		public String getRoomNum() {
			return roomNum;
		}

		public void setRoomNum(String roomNum) {
			this.roomNum = roomNum;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getHallNum() {
			return hallNum;
		}

		public void setHallNum(String hallNum) {
			this.hallNum = hallNum;
		}

		public double getBusinessListLatitude() {
			return businessListLatitude;
		}

		public int getAreaId() {
			return areaId;
		}

		public void setAreaId(int areaId) {
			this.areaId = areaId;
		}

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}

		public int getAreaListId() {
			return areaListId;
		}

		public void setAreaListId(int areaListId) {
			this.areaListId = areaListId;
		}

		public int getHouseNumByAreaList() {
			return houseNumByAreaList;
		}

		public void setHouseNumByAreaList(int houseNumByAreaList) {
			this.houseNumByAreaList = houseNumByAreaList;
		}

		public String getAreaListName() {
			return areaListName;
		}

		public void setAreaListName(String areaListName) {
			this.areaListName = areaListName;
		}

		public void setBusinessListLatitude(double businesssListLatitude) {
			this.businessListLatitude = businesssListLatitude;
		}

		public double getBusinessListLongitude() {
			return businessListLongitude;
		}

		public void setBusinessListLongitude(double businessListLongitude) {
			this.businessListLongitude = businessListLongitude;
		}

		public int getHouseNumByBusinessList() {
			return houseNumByBusinessList;
		}

		public void setHouseNumByBusinessList(int houseNumByBusinessList) {
			this.houseNumByBusinessList = houseNumByBusinessList;
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

	}

	public class XiamenInitList {
		private int areaId;
		private String areaName;
		private double latitude;
		private double longitude;
		private int houseNumByArea;
		private int newHouseNumByArea;
		private int parkingNumByArea;

		public int getParkingNumByArea() {
			return parkingNumByArea;
		}

		public void setParkingNumByArea(int parkingNumByArea) {
			this.parkingNumByArea = parkingNumByArea;
		}

		private ArrayList<MapSearchHouseAreaInfoList> mapSearchHouseAreaInfoList = new ArrayList<>();
		private ArrayList<MapSearchNewHouseAreaInfoList> mapSearchNewHouseAreaInfoList = new ArrayList<>();
		private ArrayList<MapSearchParkingAreaInfoList> mapSearchParkingAreaInfoList = new ArrayList<>();

		
		
		public ArrayList<MapSearchNewHouseAreaInfoList> getMapSearchNewHouseAreaInfoList() {
			return mapSearchNewHouseAreaInfoList;
		}

		public void setMapSearchNewHouseAreaInfoList(ArrayList<MapSearchNewHouseAreaInfoList> mapSearchNewHouseAreaInfoList) {
			this.mapSearchNewHouseAreaInfoList = mapSearchNewHouseAreaInfoList;
		}

		public int getAreaId() {
			return areaId;
		}

		public ArrayList<MapSearchParkingAreaInfoList> getMapSearchParkingAreaInfoList() {
			return mapSearchParkingAreaInfoList;
		}

		public void setMapSearchParkingAreaInfoList(
				ArrayList<MapSearchParkingAreaInfoList> mapSearchParkingAreaInfoList) {
			this.mapSearchParkingAreaInfoList = mapSearchParkingAreaInfoList;
		}

		public ArrayList<MapSearchHouseAreaInfoList> getMapSearchHouseAreaInfoList() {
			return mapSearchHouseAreaInfoList;
		}

		public void setMapSearchHouseAreaInfoList(ArrayList<MapSearchHouseAreaInfoList> mapSearchHouseAreaInfoList) {
			this.mapSearchHouseAreaInfoList = mapSearchHouseAreaInfoList;
		}

		public int getNewHouseNumByArea() {
			return newHouseNumByArea;
		}

		public void setNewHouseNumByArea(int newHouseNumByArea) {
			this.newHouseNumByArea = newHouseNumByArea;
		}

		public void setAreaId(int areaId) {
			this.areaId = areaId;
		}

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}

		public int getHouseNumByArea() {
			return houseNumByArea;
		}

		public void setHouseNumByArea(int houseNumByArea) {
			this.houseNumByArea = houseNumByArea;
		}

		public class MapSearchHouseAreaInfoList {
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

	}

	public class SpecialtyList {
		private String specialtyName;

		public String getSpecialtyName() {
			return specialtyName;
		}

		public void setSpecialtyName(String specialtyName) {
			this.specialtyName = specialtyName;
		}
	}

	public class SpecialsList {

		@Expose
		private String specialName;
		@Expose
		private Integer specialId;

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
		 * @return The specialId
		 */
		public Integer getSpecialId() {
			return specialId;
		}

		/**
		 * 
		 * @param specialId
		 *            The specialId
		 */
		public void setSpecialId(Integer specialId) {
			this.specialId = specialId;
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

		private Double businessListLatitude;

		private Double businessListLongitude;

		public Double getBusinessListLatitude() {
			return businessListLatitude;
		}

		public void setBusinessListLatitude(Double businessListLatitude) {
			this.businessListLatitude = businessListLatitude;
		}

		public Double getBusinessListLongitude() {
			return businessListLongitude;
		}

		public void setBusinessListLongitude(Double businessListLongitude) {
			this.businessListLongitude = businessListLongitude;
		}

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

	public class MapSearchHouseAreaInfoList {
		private int areaId;
		private double areaLongitude;
		private String areaName;
		private int areaListId;
		private Integer houseNumByArea;
		private Integer newHouseNumByArea;
		private int businessListId;
		private String businessListName;
		private double areaLatitude;
		private String areaListName;

		public Integer getNewHouseNumByArea() {
			return newHouseNumByArea;
		}

		public void setNewHouseNumByArea(Integer getNewHouseNumByArea) {
			this.newHouseNumByArea = getNewHouseNumByArea;
		}

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

		public Integer getHouseNumByArea() {
			return houseNumByArea;
		}

		public void setHouseNumByArea(Integer houseNumByArea) {
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

	public class AreaListNameList implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3713126610921084088L;
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

	public class MapSearchParkingAreaInfoList implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4023596542236185083L;
		private int areaId;
		private double areaLongitude;
		private String areaName;
		private int areaListId;
		private int parkingNumByArea;
		private int businessListId;
		private String businessListName;
		private double areaLatitude;
		private String areaListName;

		public int getParkingNumByArea() {
			return parkingNumByArea;
		}

		public void setParkingNumByArea(int parkingNumByArea) {
			this.parkingNumByArea = parkingNumByArea;
		}

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

	public class MapSearchNewHouseAreaInfoList implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 9170197958648195726L;
		private Integer newHouseId;
		private double areaId;
		private String newHouseName;
		private String areaName;
		private int newHouseNumByArea;
		private int businessListId;
		private String businessListName;
		private Double newHouseLatitude;
		private Double newHouseLongitude;

		public Integer getNewHouseId() {
			return newHouseId;
		}

		public void setNewHouseId(Integer newHouseId) {
			this.newHouseId = newHouseId;
		}

		public double getAreaId() {
			return areaId;
		}

		public void setAreaId(double areaId) {
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

		public int getNewHouseNumByArea() {
			return newHouseNumByArea;
		}

		public void setNewHouseNumByArea(int newHouseNumByArea) {
			this.newHouseNumByArea = newHouseNumByArea;
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

		public Double getNewHouseLatitude() {
			return newHouseLatitude;
		}

		public void setNewHouseLatitude(Double newHouseLatitude) {
			this.newHouseLatitude = newHouseLatitude;
		}

		public Double getNewHouseLongitude() {
			return newHouseLongitude;
		}

		public void setNewHouseLongitude(Double newHouseLongitude) {
			this.newHouseLongitude = newHouseLongitude;
		}

	}

	public class NewHouseNameList implements Serializable {
		private static final long serialVersionUID = -3420551788695840330L;

		private int newHouseId;
		private double areaId;
		private String newHouseName;
		private String areaName;

		private int newHouseNumByArea;
		private int businessListId;
		private String businessListName;
		private double newHouseLatitude;
		private String newHouseLongitude;

		public int getNewHouseId() {
			return newHouseId;
		}

		public void setNewHouseId(int newHouseId) {
			this.newHouseId = newHouseId;
		}

		public double getAreaId() {
			return areaId;
		}

		public void setAreaId(double areaId) {
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

		public int getNewHouseNumByArea() {
			return newHouseNumByArea;
		}

		public void setNewHouseNumByArea(int newHouseNumByArea) {
			this.newHouseNumByArea = newHouseNumByArea;
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

		public double getNewHouseLatitude() {
			return newHouseLatitude;
		}

		public void setNewHouseLatitude(double newHouseLatitude) {
			this.newHouseLatitude = newHouseLatitude;
		}

		public String getNewHouseLongitude() {
			return newHouseLongitude;
		}

		public void setNewHouseLongitude(String newHouseLongitude) {
			this.newHouseLongitude = newHouseLongitude;
		}

	}

	/**
		 * 
		 */

	public class BusinessListNameList implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6171729465898148337L;
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

}
