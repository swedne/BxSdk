package com.zorasun.fangchanzhichuang.section.my.entiy;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class CollectionListEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2206013512246299366L;

	private Content content;

	public void setContent(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return this.content;
	}

	public class Content {
		private List<HouseList> houseList = new ArrayList<>();

		public List<HouseList> getHouseList() {
			return houseList;
		}

		public void setHouseList(List<HouseList> houseList) {
			this.houseList = houseList;
		}
	}

	public class HouseList {

		private int isAuth;

		public int isList;

		private int newHouseId;

		public int getNewHouseId() {
			return newHouseId;
		}

		public void setNewHouseId(int newHouseId) {
			this.newHouseId = newHouseId;
		}

		public int getIsAuth() {
			return isAuth;
		}

		public void setIsAuth(int isAuth) {
			this.isAuth = isAuth;
		}

		String areaListName;

		public String getAreaListName() {
			return areaListName;
		}

		public void setAreaListName(String areaListName) {
			this.areaListName = areaListName;
		}

		// 新房商圈名字
		private String businessName;
		private Integer salePrice;
		private String average;

		private String newhouseName;

		private List<NewHouseSpecialList> newHouseSpecialList = new ArrayList<>();

		public List<NewHouseSpecialList> getNewHouseSpecialList() {
			return newHouseSpecialList;
		}

		public void setNewHouseSpecialList(List<NewHouseSpecialList> newHouseSpecialList) {
			this.newHouseSpecialList = newHouseSpecialList;
		}

		public String getAverage() {
			return average;
		}

		public void setAverage(String average) {
			this.average = average;
		}

		public String getNewhouseName() {
			return newhouseName;
		}

		public void setNewhouseName(String newHouseName) {
			this.newhouseName = newHouseName;
		}

		public Integer getSalePrice() {
			return salePrice;
		}

		public void setSalePrice(Integer salePrice) {
			this.salePrice = salePrice;
		}

		public String getBusinessName() {
			return businessName;
		}

		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

		public String getBusinessListName() {
			return businessListName;
		}

		public void setBusinessListName(String businessListName) {
			this.businessListName = businessListName;
		}

		private String businessListName;
		@Expose
		private Integer areaListId;
		@Expose
		private HouseTypeName houseTypeName;
		@Expose
		private String title;
		@Expose
		private Double plantAcreage;
		@Expose
		private String berryGG;
		@Expose
		private String url;
		@Expose
		private Integer rental;
		@Expose
		private Object picUrl;
		@Expose
		private Integer roomNum;
		@Expose
		private ResultMap resultMap;
		@Expose
		private Integer size;
		@Expose
		private Integer htid;
		@Expose
		private Integer hrcId;
		@Expose
		private Integer id;
		@Expose
		private String tilte;
		@Expose
		private Integer hallNum;

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
		 * @return The houseTypeName
		 */
		public HouseTypeName getHouseTypeName() {
			return houseTypeName;
		}

		/**
		 * 
		 * @param houseTypeName
		 *            The houseTypeName
		 */
		public void setHouseTypeName(HouseTypeName houseTypeName) {
			this.houseTypeName = houseTypeName;
		}

		/**
		 * 
		 * @return The title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * 
		 * @param title
		 *            The title
		 */
		public void setTitle(String title) {
			this.title = title;
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
		public String getBerryGG() {
			return berryGG;
		}

		/**
		 * 
		 * @param berryGG
		 *            The berryGG
		 */
		public void setBerryGG(String berryGG) {
			this.berryGG = berryGG;
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

		/**
		 * 
		 * @return The rental
		 */
		public Integer getRental() {
			return rental;
		}

		/**
		 * 
		 * @param rental
		 *            The rental
		 */
		public void setRental(Integer rental) {
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
		 * @return The resultMap
		 */
		public ResultMap getResultMap() {
			return resultMap;
		}

		/**
		 * 
		 * @param resultMap
		 *            The resultMap
		 */
		public void setResultMap(ResultMap resultMap) {
			this.resultMap = resultMap;
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
		 * @return The hrcId
		 */
		public Integer getHrcId() {
			return hrcId;
		}

		/**
		 * 
		 * @param hrcId
		 *            The hrcId
		 */
		public void setHrcId(Integer hrcId) {
			this.hrcId = hrcId;
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

	public class ResultMap {

		@Expose
		private Integer pageCount;
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

	public class HouseTypeName {
		private String typeName;
		private int id;

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	}

	public class NewHouseSpecialList {
		private String specialName;
		private int specialId;

		public String getSpecialName() {
			return specialName;
		}

		public void setSpecialName(String specialName) {
			this.specialName = specialName;
		}

		public int getSpecialId() {
			return specialId;
		}

		public void setSpecialId(int specialId) {
			this.specialId = specialId;
		}
	}
}
