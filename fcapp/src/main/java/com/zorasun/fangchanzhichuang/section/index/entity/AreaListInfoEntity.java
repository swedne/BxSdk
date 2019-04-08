package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class AreaListInfoEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5511126713258140158L;
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
		private AreaListInfoMap areaListInfoMap;

		public AreaListInfoMap getAreaListInfoMap() {
			return areaListInfoMap;
		}

		public void setAreaListInfoMap(AreaListInfoMap areaListInfoMap) {
			this.areaListInfoMap = areaListInfoMap;
		}

	}

	public class AreaListInfoMap {

		@Expose
		private String parking;
		@Expose
		private Integer houseResource;
		@Expose
		private String developers;
		@Expose
		private Integer createYear;
		@Expose
		private String businessName;
		@Expose
		private Object areaImage;
		@Expose
		private List<Object> secondHouseList = new ArrayList<Object>();
		@Expose
		private List<SpecialList> specialList = new ArrayList<SpecialList>();
		@Expose
		private Double areaLatitude;
		@Expose
		private String building;
		@Expose
		private Integer arealistId;
		@Expose
		private Object areaDetail;
		@Expose
		private Double areaLongitude;
		@Expose
		private String managerCompany;
		@Expose
		private Object houseTypeImages;
		@Expose
		private String areaName;
		@Expose
		private Integer managerFee;
		@Expose
		private String address;
		@Expose
		private List<RentHouseList> rentHouseList = new ArrayList<RentHouseList>();
		@Expose
		private String houseType;
		@Expose
		private List<Object> communityList = new ArrayList<Object>();
		@Expose
		private String areaListName;
		@Expose
		private Integer buildingNums;
		@Expose
		private Integer oldSellingHouse;
		@Expose
		private Integer houseNums;
		@Expose
		private String detailAddress;
		@Expose
		private String averagePrice;
		@Expose
		private Integer houseResourseNum;
		@Expose
		private List<ImageList> imageList = new ArrayList<ImageList>();
		@Expose
		private Object age;

		/**
		 * 
		 * @return The parking
		 */
		public String getParking() {
			return parking;
		}

		/**
		 * 
		 * @param parking
		 *            The parking
		 */
		public void setParking(String parking) {
			this.parking = parking;
		}

		/**
		 * 
		 * @return The houseResource
		 */
		public Integer getHouseResource() {
			return houseResource;
		}

		/**
		 * 
		 * @param houseResource
		 *            The houseResource
		 */
		public void setHouseResource(Integer houseResource) {
			this.houseResource = houseResource;
		}

		/**
		 * 
		 * @return The developers
		 */
		public String getDevelopers() {
			return developers;
		}

		/**
		 * 
		 * @param developers
		 *            The developers
		 */
		public void setDevelopers(String developers) {
			this.developers = developers;
		}

		/**
		 * 
		 * @return The createYear
		 */
		public Integer getCreateYear() {
			return createYear;
		}

		/**
		 * 
		 * @param createYear
		 *            The createYear
		 */
		public void setCreateYear(Integer createYear) {
			this.createYear = createYear;
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
		 * @return The areaImage
		 */
		public Object getAreaImage() {
			return areaImage;
		}

		/**
		 * 
		 * @param areaImage
		 *            The areaImage
		 */
		public void setAreaImage(Object areaImage) {
			this.areaImage = areaImage;
		}

		/**
		 * 
		 * @return The secondHouseList
		 */
		public List<Object> getSecondHouseList() {
			return secondHouseList;
		}

		/**
		 * 
		 * @param secondHouseList
		 *            The secondHouseList
		 */
		public void setSecondHouseList(List<Object> secondHouseList) {
			this.secondHouseList = secondHouseList;
		}

		/**
		 * 
		 * @return The specialList
		 */
		public List<SpecialList> getSpecialList() {
			return specialList;
		}

		/**
		 * 
		 * @param specialList
		 *            The specialList
		 */
		public void setSpecialList(List<SpecialList> specialList) {
			this.specialList = specialList;
		}

		/**
		 * 
		 * @return The areaLatitude
		 */
		public Double getAreaLatitude() {
			return areaLatitude;
		}

		/**
		 * 
		 * @param areaLatitude
		 *            The areaLatitude
		 */
		public void setAreaLatitude(Double areaLatitude) {
			this.areaLatitude = areaLatitude;
		}

		/**
		 * 
		 * @return The building
		 */
		public String getBuilding() {
			return building;
		}

		/**
		 * 
		 * @param building
		 *            The building
		 */
		public void setBuilding(String building) {
			this.building = building;
		}

		/**
		 * 
		 * @return The arealistId
		 */
		public Integer getArealistId() {
			return arealistId;
		}

		/**
		 * 
		 * @param arealistId
		 *            The arealistId
		 */
		public void setArealistId(Integer arealistId) {
			this.arealistId = arealistId;
		}

		/**
		 * 
		 * @return The areaDetail
		 */
		public Object getAreaDetail() {
			return areaDetail;
		}

		/**
		 * 
		 * @param areaDetail
		 *            The areaDetail
		 */
		public void setAreaDetail(Object areaDetail) {
			this.areaDetail = areaDetail;
		}

		/**
		 * 
		 * @return The areaLongitude
		 */
		public Double getAreaLongitude() {
			return areaLongitude;
		}

		/**
		 * 
		 * @param areaLongitude
		 *            The areaLongitude
		 */
		public void setAreaLongitude(Double areaLongitude) {
			this.areaLongitude = areaLongitude;
		}

		/**
		 * 
		 * @return The managerCompany
		 */
		public String getManagerCompany() {
			return managerCompany;
		}

		/**
		 * 
		 * @param managerCompany
		 *            The managerCompany
		 */
		public void setManagerCompany(String managerCompany) {
			this.managerCompany = managerCompany;
		}

		/**
		 * 
		 * @return The houseTypeImages
		 */
		public Object getHouseTypeImages() {
			return houseTypeImages;
		}

		/**
		 * 
		 * @param houseTypeImages
		 *            The houseTypeImages
		 */
		public void setHouseTypeImages(Object houseTypeImages) {
			this.houseTypeImages = houseTypeImages;
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
		 * @return The managerFee
		 */
		public Integer getManagerFee() {
			return managerFee;
		}

		/**
		 * 
		 * @param managerFee
		 *            The managerFee
		 */
		public void setManagerFee(Integer managerFee) {
			this.managerFee = managerFee;
		}

		/**
		 * 
		 * @return The address
		 */
		public String getAddress() {
			return address;
		}

		/**
		 * 
		 * @param address
		 *            The address
		 */
		public void setAddress(String address) {
			this.address = address;
		}

		/**
		 * 
		 * @return The rentHouseList
		 */
		public List<RentHouseList> getRentHouseList() {
			return rentHouseList;
		}

		/**
		 * 
		 * @param rentHouseList
		 *            The rentHouseList
		 */
		public void setRentHouseList(List<RentHouseList> rentHouseList) {
			this.rentHouseList = rentHouseList;
		}

		/**
		 * 
		 * @return The houseType
		 */
		public String getHouseType() {
			return houseType;
		}

		/**
		 * 
		 * @param houseType
		 *            The houseType
		 */
		public void setHouseType(String houseType) {
			this.houseType = houseType;
		}

		/**
		 * 
		 * @return The communityList
		 */
		public List<Object> getCommunityList() {
			return communityList;
		}

		/**
		 * 
		 * @param communityList
		 *            The communityList
		 */
		public void setCommunityList(List<Object> communityList) {
			this.communityList = communityList;
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
		 * @return The buildingNums
		 */
		public Integer getBuildingNums() {
			return buildingNums;
		}

		/**
		 * 
		 * @param buildingNums
		 *            The buildingNums
		 */
		public void setBuildingNums(Integer buildingNums) {
			this.buildingNums = buildingNums;
		}

		/**
		 * 
		 * @return The oldSellingHouse
		 */
		public Integer getOldSellingHouse() {
			return oldSellingHouse;
		}

		/**
		 * 
		 * @param oldSellingHouse
		 *            The oldSellingHouse
		 */
		public void setOldSellingHouse(Integer oldSellingHouse) {
			this.oldSellingHouse = oldSellingHouse;
		}

		/**
		 * 
		 * @return The houseNums
		 */
		public Integer getHouseNums() {
			return houseNums;
		}

		/**
		 * 
		 * @param houseNums
		 *            The houseNums
		 */
		public void setHouseNums(Integer houseNums) {
			this.houseNums = houseNums;
		}

		/**
		 * 
		 * @return The detailAddress
		 */
		public String getDetailAddress() {
			return detailAddress;
		}

		/**
		 * 
		 * @param detailAddress
		 *            The detailAddress
		 */
		public void setDetailAddress(String detailAddress) {
			this.detailAddress = detailAddress;
		}

		/**
		 * 
		 * @return The averagePrice
		 */
		public String getAveragePrice() {
			return averagePrice;
		}

		/**
		 * 
		 * @param averagePrice
		 *            The averagePrice
		 */
		public void setAveragePrice(String averagePrice) {
			this.averagePrice = averagePrice;
		}

		/**
		 * 
		 * @return The houseResourseNum
		 */
		public Integer getHouseResourseNum() {
			return houseResourseNum;
		}

		/**
		 * 
		 * @param houseResourseNum
		 *            The houseResourseNum
		 */
		public void setHouseResourseNum(Integer houseResourseNum) {
			this.houseResourseNum = houseResourseNum;
		}

		/**
		 * 
		 * @return The imageList
		 */
		public List<ImageList> getImageList() {
			return imageList;
		}

		/**
		 * 
		 * @param imageList
		 *            The imageList
		 */
		public void setImageList(List<ImageList> imageList) {
			this.imageList = imageList;
		}

		/**
		 * 
		 * @return The age
		 */
		public Object getAge() {
			return age;
		}

		/**
		 * 
		 * @param age
		 *            The age
		 */
		public void setAge(Object age) {
			this.age = age;
		}

	}

	public class ImageList {

		@Expose
		private String fileName;
		@Expose
		private Integer imageId;
		@Expose
		private Object bounds;
		@Expose
		private String houseTypeName;
		@Expose
		private String url;

		/**
		 * 
		 * @return The fileName
		 */
		public String getFileName() {
			return fileName;
		}

		/**
		 * 
		 * @param fileName
		 *            The fileName
		 */
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		/**
		 * 
		 * @return The imageId
		 */
		public Integer getImageId() {
			return imageId;
		}

		/**
		 * 
		 * @param imageId
		 *            The imageId
		 */
		public void setImageId(Integer imageId) {
			this.imageId = imageId;
		}

		/**
		 * 
		 * @return The bounds
		 */
		public Object getBounds() {
			return bounds;
		}

		/**
		 * 
		 * @param bounds
		 *            The bounds
		 */
		public void setBounds(Object bounds) {
			this.bounds = bounds;
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

	}

	public class RentHouseList {
		private String picUrl;
		private int isAuth;
		private int roomNum;
		private int salePrice;
		private int isShowImage;
		private int houseResourceId;
		private int plantAcreage;
		private double berryGG;
		private int hallNum;
		private int rental;

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public int getIsAuth() {
			return isAuth;
		}

		public void setIsAuth(int isAuth) {
			this.isAuth = isAuth;
		}

		public int getRoomNum() {
			return roomNum;
		}

		public void setRoomNum(int roomNum) {
			this.roomNum = roomNum;
		}

		public int getSalePrice() {
			return salePrice;
		}

		public void setSalePrice(int salePrice) {
			this.salePrice = salePrice;
		}

		public int getIsShowImage() {
			return isShowImage;
		}

		public void setIsShowImage(int isShowImage) {
			this.isShowImage = isShowImage;
		}

		public int getHouseResourceId() {
			return houseResourceId;
		}

		public void setHouseResourceId(int houseResourceId) {
			this.houseResourceId = houseResourceId;
		}

		public int getPlantAcreage() {
			return plantAcreage;
		}

		public void setPlantAcreage(int plantAcreage) {
			this.plantAcreage = plantAcreage;
		}

		public double getBerryGG() {
			return berryGG;
		}

		public void setBerryGG(int berryGG) {
			this.berryGG = berryGG;
		}

		public int getHallNum() {
			return hallNum;
		}

		public void setHallNum(int hallNum) {
			this.hallNum = hallNum;
		}

		public int getRental() {
			return rental;
		}

		public void setRental(int rental) {
			this.rental = rental;
		}

	}

	public class SpecialList {
		private String specialName;

		public String getSpecialName() {
			return specialName;
		}

		public void setSpecialName(String specialName) {
			this.specialName = specialName;
		}

	}

}
