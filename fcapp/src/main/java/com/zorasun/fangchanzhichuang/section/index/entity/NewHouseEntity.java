package com.zorasun.fangchanzhichuang.section.index.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class NewHouseEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1306590757886137144L;

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
		private NewHouseMap newHouseMap;

		public NewHouseMap getNewHouseMap() {
			return newHouseMap;
		}

		public void setNewHouseMap(NewHouseMap newHouseMap) {
			this.newHouseMap = newHouseMap;
		}

	}

	public class NewHouseMap implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 9170884087470929499L;

		private int isCollection;

		public int getIsCollection() {
			return isCollection;
		}

		public void setIsCollection(int isCollection) {
			this.isCollection = isCollection;
		}

		private List<ImageUrlList> imageUrlList = new ArrayList<ImageUrlList>();

		private List<PropertyGalleryList> propertyGalleryList = new ArrayList<PropertyGalleryList>();
		// private List<ImageUrlList> imageUrlList = new
		// ArrayList<ImageUrlList>();

		public List<ImageUrlList> getImageUrlList() {
			return imageUrlList;
		}

		public void setImageUrlList(ArrayList<ImageUrlList> imageUrlList) {
			this.imageUrlList = imageUrlList;
		}

		@Expose
		private int average;
		@Expose
		private String projectUrl;
		@Expose
		private String developers;
		@Expose
		private Double latitude;
		@Expose
		private String businessName;
		@Expose
		private String agentDevelopers;
		@Expose
		private List<SpecialList> specialList = new ArrayList<SpecialList>();
		@Expose
		private String managerCompany;
		@Expose
		private String areaName;
		@Expose
		private long openTime;
		@Expose
		private List<ApartmentList> apartmentList = new ArrayList<ApartmentList>();
		@Expose
		private Double longitude;
		@Expose
		private String sellAddress;
		@Expose
		private int managerFee;
		@Expose
		private String buildTime;
		@Expose
		private String newhouseName;
		@Expose
		private String hostHouseType;
		@Expose
		private String structureName;
		@Expose
		private Integer parkingNums;
		@Expose
		private Integer newhouseId;
		@Expose
		private Integer buildingNums;
		@Expose
		private long volumeTime;
		@Expose
		private Integer houseNums;
		@Expose
		private String projectDetail;
		@Expose
		private String developersMobile;

		/**
		 * 
		 * @return The average
		 */
		public int getAverage() {
			return average;
		}

		/**
		 * 
		 * @param average
		 *            The average
		 */
		public void setAverage(int average) {
			this.average = average;
		}

		/**
		 * 
		 * @return The projectUrl
		 */
		public String getProjectUrl() {
			return projectUrl;
		}

		/**
		 * 
		 * @param projectUrl
		 *            The projectUrl
		 */
		public void setProjectUrl(String projectUrl) {
			this.projectUrl = projectUrl;
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
		 * @return The latitude
		 */
		public Double getLatitude() {
			return latitude;
		}

		/**
		 * 
		 * @param latitude
		 *            The latitude
		 */
		public void setLatitude(Double latitude) {
			this.latitude = latitude;
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
		 * @return The agentDevelopers
		 */
		public String getAgentDevelopers() {
			return agentDevelopers;
		}

		/**
		 * 
		 * @param agentDevelopers
		 *            The agentDevelopers
		 */
		public void setAgentDevelopers(String agentDevelopers) {
			this.agentDevelopers = agentDevelopers;
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
		 * @return The propertyGalleryList
		 */
		public List<PropertyGalleryList> getPropertyGalleryList() {
			return propertyGalleryList;
		}

		/**
		 * 
		 * @param propertyGalleryList
		 *            The propertyGalleryList
		 */
		public void setPropertyGalleryList(List<PropertyGalleryList> propertyGalleryList) {
			this.propertyGalleryList = propertyGalleryList;
		}

		/**
		 * 
		 * @return The openTime
		 */
		public long getOpenTime() {
			return openTime;
		}

		/**
		 * 
		 * @param openTime
		 *            The openTime
		 */
		public void setOpenTime(long openTime) {
			this.openTime = openTime;
		}

		/**
		 * 
		 * @return The apartmentList
		 */
		public List<ApartmentList> getApartmentList() {
			return apartmentList;
		}

		/**
		 * 
		 * @param apartmentList
		 *            The apartmentList
		 */
		public void setApartmentList(List<ApartmentList> apartmentList) {
			this.apartmentList = apartmentList;
		}

		/**
		 * 
		 * @return The longitude
		 */
		public Double getLongitude() {
			return longitude;
		}

		/**
		 * 
		 * @param longitude
		 *            The longitude
		 */
		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}

		/**
		 * 
		 * @return The sellAddress
		 */
		public String getSellAddress() {
			return sellAddress;
		}

		/**
		 * 
		 * @param sellAddress
		 *            The sellAddress
		 */
		public void setSellAddress(String sellAddress) {
			this.sellAddress = sellAddress;
		}

		/**
		 * 
		 * @return The managerFee
		 */
		public int getManagerFee() {
			return managerFee;
		}

		/**
		 * 
		 * @param managerFee
		 *            The managerFee
		 */
		public void setManagerFee(int managerFee) {
			this.managerFee = managerFee;
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
		 * @return The newhouseName
		 */
		public String getNewhouseName() {
			return newhouseName;
		}

		/**
		 * 
		 * @param newhouseName
		 *            The newhouseName
		 */
		public void setNewhouseName(String newhouseName) {
			this.newhouseName = newhouseName;
		}

		/**
		 * 
		 * @return The hostHouseType
		 */
		public String getHostHouseType() {
			return hostHouseType;
		}

		/**
		 * 
		 * @param hostHouseType
		 *            The hostHouseType
		 */
		public void setHostHouseType(String hostHouseType) {
			this.hostHouseType = hostHouseType;
		}

		/**
		 * 
		 * @return The structureName
		 */
		public String getStructureName() {
			return structureName;
		}

		/**
		 * 
		 * @param structureName
		 *            The structureName
		 */
		public void setStructureName(String structureName) {
			this.structureName = structureName;
		}

		/**
		 * 
		 * @return The parkingNums
		 */
		public Integer getParkingNums() {
			return parkingNums;
		}

		/**
		 * 
		 * @param parkingNums
		 *            The parkingNums
		 */
		public void setParkingNums(Integer parkingNums) {
			this.parkingNums = parkingNums;
		}

		/**
		 * 
		 * @return The newhouseId
		 */
		public Integer getNewhouseId() {
			return newhouseId;
		}

		/**
		 * 
		 * @param newhouseId
		 *            The newhouseId
		 */
		public void setNewhouseId(Integer newhouseId) {
			this.newhouseId = newhouseId;
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
		 * @return The volumeTime
		 */
		public long getVolumeTime() {
			return volumeTime;
		}

		/**
		 * 
		 * @param volumeTime
		 *            The volumeTime
		 */
		public void setVolumeTime(long volumeTime) {
			this.volumeTime = volumeTime;
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
		 * @return The projectDetail
		 */
		public String getProjectDetail() {
			return projectDetail;
		}

		/**
		 * 
		 * @param projectDetail
		 *            The projectDetail
		 */
		public void setProjectDetail(String projectDetail) {
			this.projectDetail = projectDetail;
		}

		/**
		 * 
		 * @return The developersMobile
		 */
		public String getDevelopersMobile() {
			return developersMobile;
		}

		/**
		 * 
		 * @param developersMobile
		 *            The developersMobile
		 */
		public void setDevelopersMobile(String developersMobile) {
			this.developersMobile = developersMobile;
		}

	}

	public class SpecialList implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6613658430642291167L;
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

	public class ImageUrlList implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2046816909479966923L;
		private String url;
		private int imageId;
		private String typeName;

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public int getImageId() {
			return imageId;
		}

		public void setImageId(int imageId) {
			this.imageId = imageId;
		}

	}

	public class ApartmentList implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5843189477260894571L;
		private String fileName;
		private int imageId;
		private int bounds;
		private String typeName;
		private String houseTypeName;
		private int category;
		private String url;

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public int getImageId() {
			return imageId;
		}

		public void setImageId(int imageId) {
			this.imageId = imageId;
		}

		public int getBounds() {
			return bounds;
		}

		public void setBounds(int bounds) {
			this.bounds = bounds;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getHouseTypeName() {
			return houseTypeName;
		}

		public void setHouseTypeName(String houseTypeName) {
			this.houseTypeName = houseTypeName;
		}

		public int getCategory() {
			return category;
		}

		public void setCategory(int category) {
			this.category = category;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}

	public class PropertyGalleryList implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3827853654876226704L;
		private String fileName;
		private int imageId;
		private int bounds;
		private String typeName;
		private String houseTypeName;
		private int category;
		private String url;

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public int getImageId() {
			return imageId;
		}

		public void setImageId(int imageId) {
			this.imageId = imageId;
		}

		public int getBounds() {
			return bounds;
		}

		public void setBounds(int bounds) {
			this.bounds = bounds;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getHouseTypeName() {
			return houseTypeName;
		}

		public void setHouseTypeName(String houseTypeName) {
			this.houseTypeName = houseTypeName;
		}

		public int getCategory() {
			return category;
		}

		public void setCategory(int category) {
			this.category = category;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}

}
