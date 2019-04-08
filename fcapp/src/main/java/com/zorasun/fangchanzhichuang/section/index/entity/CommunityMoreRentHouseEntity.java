package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class CommunityMoreRentHouseEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2068536362710298720L;

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
		private Integer areaListId;
		@Expose
		private Integer houseTypeId;
		@Expose
		private Integer pageCount;
		@Expose
		private List<RentHouseListMore> rentHouseListMore = new ArrayList<RentHouseListMore>();
		private List<NearbyHouseResourceList> nearbyHouseResourceList = new ArrayList<NearbyHouseResourceList>();

		/**
		 * 
		 * @return The areaListId
		 */
		public Integer getAreaListId() {
			return areaListId;
		}

		public List<NearbyHouseResourceList> getNearbyHouseResourceList() {
			return nearbyHouseResourceList;
		}

		public void setNearbyHouseResourceList(List<NearbyHouseResourceList> nearbyHouseResourceList) {
			this.nearbyHouseResourceList = nearbyHouseResourceList;
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
		 * @return The houseTypeId
		 */
		public Integer getHouseTypeId() {
			return houseTypeId;
		}

		/**
		 * 
		 * @param houseTypeId
		 *            The houseTypeId
		 */
		public void setHouseTypeId(Integer houseTypeId) {
			this.houseTypeId = houseTypeId;
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
		 * @return The rentHouseListMore
		 */
		public List<RentHouseListMore> getRentHouseListMore() {
			return rentHouseListMore;
		}

		/**
		 * 
		 * @param rentHouseListMore
		 *            The rentHouseListMore
		 */
		public void setRentHouseListMore(List<RentHouseListMore> rentHouseListMore) {
			this.rentHouseListMore = rentHouseListMore;
		}

	}

	public class RentHouseListMore {

		private String surFaceUrl;

		public String getSurFaceUrl() {
			return surFaceUrl;
		}

		public void setSurFaceUrl(String surFaceUrl) {
			this.surFaceUrl = surFaceUrl;
		}

		@Expose
		private List<String> specials = new ArrayList<String>();
		@Expose
		private Integer isShowImage;
		@Expose
		private String businessName;
		@Expose
		private Object offBuildTypeName;
		@Expose
		private Integer floorSum;
		@Expose
		private String title;
		public String plantArea;
		@Expose
		private String plantAcreage;
		@Expose
		private Integer floorNum;
		@Expose
		private String berryGG;
		@Expose
		private String picUrl;
		@Expose
		private Integer isAuth;
		@Expose
		private Integer roomNum;
		@Expose
		private String areaName;
		@Expose
		private Integer houseResourceId;
		@Expose
		private Object salePrice;
		@Expose
		private String buildTime;
		@Expose
		private String houseTypeName;
		@Expose
		private String areaListName;
		@Expose
		private String rental;
		@Expose
		private String payTypeName;
		@Expose
		private Object offBuildLevelName;
		@Expose
		private String orientationName;
		@Expose
		private String location;
		@Expose
		private Object shopTypeName;
		@Expose
		private Integer hallNum;

		/**
		 * 
		 * @return The specials
		 */
		public List<String> getSpecials() {
			return specials;
		}

		/**
		 * 
		 * @param specials
		 *            The specials
		 */
		public void setSpecials(List<String> specials) {
			this.specials = specials;
		}

		/**
		 * 
		 * @return The isShowImage
		 */
		public Integer getIsShowImage() {
			return isShowImage;
		}

		/**
		 * 
		 * @param isShowImage
		 *            The isShowImage
		 */
		public void setIsShowImage(Integer isShowImage) {
			this.isShowImage = isShowImage;
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
		 * @return The offBuildTypeName
		 */
		public Object getOffBuildTypeName() {
			return offBuildTypeName;
		}

		/**
		 * 
		 * @param offBuildTypeName
		 *            The offBuildTypeName
		 */
		public void setOffBuildTypeName(Object offBuildTypeName) {
			this.offBuildTypeName = offBuildTypeName;
		}

		/**
		 * 
		 * @return The floorSum
		 */
		public Integer getFloorSum() {
			return floorSum;
		}

		/**
		 * 
		 * @param floorSum
		 *            The floorSum
		 */
		public void setFloorSum(Integer floorSum) {
			this.floorSum = floorSum;
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
		public String getPlantAcreage() {
			return plantAcreage;
		}

		/**
		 * 
		 * @param plantAcreage
		 *            The plantAcreage
		 */
		public void setPlantAcreage(String plantAcreage) {
			this.plantAcreage = plantAcreage;
		}

		/**
		 * 
		 * @return The floorNum
		 */
		public Integer getFloorNum() {
			return floorNum;
		}

		/**
		 * 
		 * @param floorNum
		 *            The floorNum
		 */
		public void setFloorNum(Integer floorNum) {
			this.floorNum = floorNum;
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
		 * @return The picUrl
		 */
		public String getPicUrl() {
			return picUrl;
		}

		/**
		 * 
		 * @param picUrl
		 *            The picUrl
		 */
		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		/**
		 * 
		 * @return The isAuth
		 */
		public Integer getIsAuth() {
			return isAuth;
		}

		/**
		 * 
		 * @param isAuth
		 *            The isAuth
		 */
		public void setIsAuth(Integer isAuth) {
			this.isAuth = isAuth;
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
		 * @return The houseResourceId
		 */
		public Integer getHouseResourceId() {
			return houseResourceId;
		}

		/**
		 * 
		 * @param houseResourceId
		 *            The houseResourceId
		 */
		public void setHouseResourceId(Integer houseResourceId) {
			this.houseResourceId = houseResourceId;
		}

		/**
		 * 
		 * @return The salePrice
		 */
		public Object getSalePrice() {
			return salePrice;
		}

		/**
		 * 
		 * @param salePrice
		 *            The salePrice
		 */
		public void setSalePrice(Object salePrice) {
			this.salePrice = salePrice;
		}

		/**
		 * 
		 * @return The buildTime
		 */
		public String getBuildTime() {
			return buildTime;
		}

		/**
		 * 
		 * @param buildTime
		 *            The buildTime
		 */
		public void setBuildTime(String buildTime) {
			this.buildTime = buildTime;
		}

		/**
		 * 
		 * @return The houseTypeName
		 */
		public String getHouseTypeName() {
			return houseTypeName;
		}

		/**
		 * 
		 * @param houseTypeName
		 *            The houseTypeName
		 */
		public void setHouseTypeName(String houseTypeName) {
			this.houseTypeName = houseTypeName;
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

		/**
		 * 
		 * @return The rental
		 */
		public String getRental() {
			return rental;
		}

		/**
		 * 
		 * @param rental
		 *            The rental
		 */
		public void setRental(String rental) {
			this.rental = rental;
		}

		/**
		 * 
		 * @return The payTypeName
		 */
		public String getPayTypeName() {
			return payTypeName;
		}

		/**
		 * 
		 * @param payTypeName
		 *            The payTypeName
		 */
		public void setPayTypeName(String payTypeName) {
			this.payTypeName = payTypeName;
		}

		/**
		 * 
		 * @return The offBuildLevelName
		 */
		public Object getOffBuildLevelName() {
			return offBuildLevelName;
		}

		/**
		 * 
		 * @param offBuildLevelName
		 *            The offBuildLevelName
		 */
		public void setOffBuildLevelName(Object offBuildLevelName) {
			this.offBuildLevelName = offBuildLevelName;
		}

		/**
		 * 
		 * @return The orientationName
		 */
		public String getOrientationName() {
			return orientationName;
		}

		/**
		 * 
		 * @param orientationName
		 *            The orientationName
		 */
		public void setOrientationName(String orientationName) {
			this.orientationName = orientationName;
		}

		/**
		 * 
		 * @return The location
		 */
		public String getLocation() {
			return location;
		}

		/**
		 * 
		 * @param location
		 *            The location
		 */
		public void setLocation(String location) {
			this.location = location;
		}

		/**
		 * 
		 * @return The shopTypeName
		 */
		public Object getShopTypeName() {
			return shopTypeName;
		}

		/**
		 * 
		 * @param shopTypeName
		 *            The shopTypeName
		 */
		public void setShopTypeName(Object shopTypeName) {
			this.shopTypeName = shopTypeName;
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

	public class NearbyHouseResourceList {
		private int id;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		@Expose
		private List<String> specials = new ArrayList<String>();
		@Expose
		private Integer isShowImage;
		@Expose
		private String businessName;
		@Expose
		private Object offBuildTypeName;
		@Expose
		private Integer floorSum;
		@Expose
		private String title;
		@Expose
		private Object plantAcreage;
		@Expose
		private Integer floorNum;
		@Expose
		private String berryGG;
		@Expose
		private String surfaceUrl;
		@Expose
		private Integer isAuth;
		@Expose
		private String roomNum;
		@Expose
		private String areaName;
		@Expose
		private Integer houseResourceId;
		@Expose
		private Object salePrice;
		@Expose
		private String buildTime;
		@Expose
		private String houseTypeName;
		@Expose
		private String areaListName;
		@Expose
		private String rental;
		@Expose
		private String payTypeName;
		@Expose
		private Object offBuildLevelName;
		@Expose
		private String orientationName;
		@Expose
		private String location;
		@Expose
		private Object shopTypeName;
		@Expose
		private String hallNum;
		private String surFaceUrl;

		/**
		 * 
		 * @return The specials
		 */
		public List<String> getSpecials() {
			return specials;
		}

		/**
		 * 
		 * @param specials
		 *            The specials
		 */
		public void setSpecials(List<String> specials) {
			this.specials = specials;
		}

		/**
		 * 
		 * @return The isShowImage
		 */
		public Integer getIsShowImage() {
			return isShowImage;
		}

		/**
		 * 
		 * @param isShowImage
		 *            The isShowImage
		 */
		public void setIsShowImage(Integer isShowImage) {
			this.isShowImage = isShowImage;
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
		 * @return The offBuildTypeName
		 */
		public Object getOffBuildTypeName() {
			return offBuildTypeName;
		}

		/**
		 * 
		 * @param offBuildTypeName
		 *            The offBuildTypeName
		 */
		public void setOffBuildTypeName(Object offBuildTypeName) {
			this.offBuildTypeName = offBuildTypeName;
		}

		/**
		 * 
		 * @return The floorSum
		 */
		public Integer getFloorSum() {
			return floorSum;
		}

		/**
		 * 
		 * @param floorSum
		 *            The floorSum
		 */
		public void setFloorSum(Integer floorSum) {
			this.floorSum = floorSum;
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
		public Object getPlantAcreage() {
			return plantAcreage;
		}

		/**
		 * 
		 * @param plantAcreage
		 *            The plantAcreage
		 */
		public void setPlantAcreage(Object plantAcreage) {
			this.plantAcreage = plantAcreage;
		}

		/**
		 * 
		 * @return The floorNum
		 */
		public Integer getFloorNum() {
			return floorNum;
		}

		/**
		 * 
		 * @param floorNum
		 *            The floorNum
		 */
		public void setFloorNum(Integer floorNum) {
			this.floorNum = floorNum;
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
		 * @return The picUrl
		 */
		public String getSurFaceUrl() {
			return surFaceUrl;
		}

		/**
		 * 
		 * @param picUrl
		 *            The picUrl
		 */
		public void setSurFaceUrl(String surFaceUrl) {
			this.surFaceUrl = surFaceUrl;
		}

		/**
		 * 
		 * @return The isAuth
		 */
		public Integer getIsAuth() {
			return isAuth;
		}

		/**
		 * 
		 * @param isAuth
		 *            The isAuth
		 */
		public void setIsAuth(Integer isAuth) {
			this.isAuth = isAuth;
		}

		/**
		 * 
		 * @return The roomNum
		 */
		public String getRoomNum() {
			return roomNum;
		}

		/**
		 * 
		 * @param roomNum
		 *            The roomNum
		 */
		public void setRoomNum(String roomNum) {
			this.roomNum = roomNum;
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
		 * @return The houseResourceId
		 */
		public Integer getHouseResourceId() {
			return houseResourceId;
		}

		/**
		 * 
		 * @param houseResourceId
		 *            The houseResourceId
		 */
		public void setHouseResourceId(Integer houseResourceId) {
			this.houseResourceId = houseResourceId;
		}

		/**
		 * 
		 * @return The salePrice
		 */
		public Object getSalePrice() {
			return salePrice;
		}

		/**
		 * 
		 * @param salePrice
		 *            The salePrice
		 */
		public void setSalePrice(Object salePrice) {
			this.salePrice = salePrice;
		}

		/**
		 * 
		 * @return The buildTime
		 */
		public String getBuildTime() {
			return buildTime;
		}

		/**
		 * 
		 * @param buildTime
		 *            The buildTime
		 */
		public void setBuildTime(String buildTime) {
			this.buildTime = buildTime;
		}

		/**
		 * 
		 * @return The houseTypeName
		 */
		public String getHouseTypeName() {
			return houseTypeName;
		}

		/**
		 * 
		 * @param houseTypeName
		 *            The houseTypeName
		 */
		public void setHouseTypeName(String houseTypeName) {
			this.houseTypeName = houseTypeName;
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

		/**
		 * 
		 * @return The rental
		 */
		public String getRental() {
			return rental;
		}

		/**
		 * 
		 * @param rental
		 *            The rental
		 */
		public void setRental(String rental) {
			this.rental = rental;
		}

		/**
		 * 
		 * @return The payTypeName
		 */
		public String getPayTypeName() {
			return payTypeName;
		}

		/**
		 * 
		 * @param payTypeName
		 *            The payTypeName
		 */
		public void setPayTypeName(String payTypeName) {
			this.payTypeName = payTypeName;
		}

		/**
		 * 
		 * @return The offBuildLevelName
		 */
		public Object getOffBuildLevelName() {
			return offBuildLevelName;
		}

		/**
		 * 
		 * @param offBuildLevelName
		 *            The offBuildLevelName
		 */
		public void setOffBuildLevelName(Object offBuildLevelName) {
			this.offBuildLevelName = offBuildLevelName;
		}

		/**
		 * 
		 * @return The orientationName
		 */
		public String getOrientationName() {
			return orientationName;
		}

		/**
		 * 
		 * @param orientationName
		 *            The orientationName
		 */
		public void setOrientationName(String orientationName) {
			this.orientationName = orientationName;
		}

		/**
		 * 
		 * @return The location
		 */
		public String getLocation() {
			return location;
		}

		/**
		 * 
		 * @param location
		 *            The location
		 */
		public void setLocation(String location) {
			this.location = location;
		}

		/**
		 * 
		 * @return The shopTypeName
		 */
		public Object getShopTypeName() {
			return shopTypeName;
		}

		/**
		 * 
		 * @param shopTypeName
		 *            The shopTypeName
		 */
		public void setShopTypeName(Object shopTypeName) {
			this.shopTypeName = shopTypeName;
		}

		/**
		 * 
		 * @return The hallNum
		 */
		public String getHallNum() {
			return hallNum;
		}

		/**
		 * 
		 * @param hallNum
		 *            The hallNum
		 */
		private List<RentHouseSpecialtyList> rentHouseSpecialtyList = new ArrayList<>();

		public List<RentHouseSpecialtyList> getRentHouseSpecialtyList() {
			return rentHouseSpecialtyList;
		}

		public void setRentHouseSpecialtyList(List<RentHouseSpecialtyList> rentHouseSpecialtyList) {
			this.rentHouseSpecialtyList = rentHouseSpecialtyList;
		}
	}

	public class RentHouseSpecialtyList {
		private String specialtyName;

		public String getSpecialtyName() {
			return specialtyName;
		}

		public void setSpecialtyName(String specialtyName) {
			this.specialtyName = specialtyName;
		}
	}

}
