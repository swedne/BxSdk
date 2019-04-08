package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class AdMangerEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1222653189846577168L;

	private Content content;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public class Content {

		private List<AdListByIndex> adListByIndex = new ArrayList<AdListByIndex>();

		private List<AdListByPlay> adListByPlay = new ArrayList<AdListByPlay>();

		private XiamenSecondHouseQuotationMap xiamenSecondHouseQuotationMap;

		public XiamenSecondHouseQuotationMap getXiamenSecondHouseQuotationMap() {
			return xiamenSecondHouseQuotationMap;
		}

		public void setXiamenSecondHouseQuotationMap(XiamenSecondHouseQuotationMap xiamenSecondHouseQuotationMap) {
			this.xiamenSecondHouseQuotationMap = xiamenSecondHouseQuotationMap;
		}

		/**
		 * 
		 * @return The adListByIndex
		 */
		public List<AdListByIndex> getAdListByIndex() {
			return adListByIndex;
		}

		/**
		 * 
		 * @param adListByIndex
		 *            The adListByIndex
		 */
		public void setAdListByIndex(List<AdListByIndex> adListByIndex) {
			this.adListByIndex = adListByIndex;
		}

		/**
		 * 
		 * @return The adListByPlay
		 */
		public List<AdListByPlay> getAdListByPlay() {
			return adListByPlay;
		}

		/**
		 * 
		 * @param adListByPlay
		 *            The adListByPlay
		 */
		public void setAdListByPlay(List<AdListByPlay> adListByPlay) {
			this.adListByPlay = adListByPlay;
		}
	}

	public class AdListByIndex {

		private int houseResourceListId;

		private Object classify;

		private String adName;

		private Integer adType;

		private Integer brokerId;

		private int newHouseListId;

		private String bigImage;

		private Integer id;

		private Integer isShow;

		/**
		 * 
		 * @return The houseResourceListId
		 */
		public int getHouseResourceListId() {
			return houseResourceListId;
		}

		/**
		 * 
		 * @param houseResourceListId
		 *            The houseResourceListId
		 */
		public void setHouseResourceListId(int houseResourceListId) {
			this.houseResourceListId = houseResourceListId;
		}

		/**
		 * 
		 * @return The classify
		 */
		public Object getClassify() {
			return classify;
		}

		/**
		 * 
		 * @param classify
		 *            The classify
		 */
		public void setClassify(Object classify) {
			this.classify = classify;
		}

		/**
		 * 
		 * @return The adName
		 */
		public String getAdName() {
			return adName;
		}

		/**
		 * 
		 * @param adName
		 *            The adName
		 */
		public void setAdName(String adName) {
			this.adName = adName;
		}

		/**
		 * 
		 * @return The adType
		 */
		public Integer getAdType() {
			return adType;
		}

		/**
		 * 
		 * @param adType
		 *            The adType
		 */
		public void setAdType(Integer adType) {
			this.adType = adType;
		}

		/**
		 * 
		 * @return The brokerId
		 */
		public Integer getBrokerId() {
			return brokerId;
		}

		/**
		 * 
		 * @param brokerId
		 *            The brokerId
		 */
		public void setBrokerId(Integer brokerId) {
			this.brokerId = brokerId;
		}

		/**
		 * 
		 * @return The newHouseListId
		 */
		public int getNewHouseListId() {
			return newHouseListId;
		}

		/**
		 * 
		 * @param newHouseListId
		 *            The newHouseListId
		 */
		public void setNewHouseListId(int newHouseListId) {
			this.newHouseListId = newHouseListId;
		}

		/**
		 * 
		 * @return The bigImage
		 */
		public String getBigImage() {
			return bigImage;
		}

		/**
		 * 
		 * @param bigImage
		 *            The bigImage
		 */
		public void setBigImage(String bigImage) {
			this.bigImage = bigImage;
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
		 * @return The isShow
		 */
		public Integer getIsShow() {
			return isShow;
		}

		/**
		 * 
		 * @param isShow
		 *            The isShow
		 */
		public void setIsShow(Integer isShow) {
			this.isShow = isShow;
		}

	}

	public class AdListByPlay {

		public AdListByPlay() {

		}

		private Integer houseResourceListId;

		private Object classify;

		private String adName;

		private Integer adType;

		private Integer brokerId;

		private Integer newHouseListId;

		private String bigImage;

		private Integer sign;

		private Integer id;

		private Integer isShow;

		/**
		 * 
		 * @return The houseResourceListId
		 */
		public Integer getHouseResourceListId() {
			return houseResourceListId;
		}

		/**
		 * 
		 * @param houseResourceListId
		 *            The houseResourceListId
		 */
		public void setHouseResourceListId(Integer houseResourceListId) {
			this.houseResourceListId = houseResourceListId;
		}

		/**
		 * 
		 * @return The classify
		 */
		public Object getClassify() {
			return classify;
		}

		/**
		 * 
		 * @param classify
		 *            The classify
		 */
		public void setClassify(Object classify) {
			this.classify = classify;
		}

		/**
		 * 
		 * @return The adName
		 */
		public String getAdName() {
			return adName;
		}

		/**
		 * 
		 * @param adName
		 *            The adName
		 */
		public void setAdName(String adName) {
			this.adName = adName;
		}

		/**
		 * 
		 * @return The adType
		 */
		public Integer getAdType() {
			return adType;
		}

		/**
		 * 
		 * @param adType
		 *            The adType
		 */
		public void setAdType(Integer adType) {
			this.adType = adType;
		}

		/**
		 * 
		 * @return The brokerId
		 */
		public Integer getBrokerId() {
			return brokerId;
		}

		/**
		 * 
		 * @param brokerId
		 *            The brokerId
		 */
		public void setBrokerId(Integer brokerId) {
			this.brokerId = brokerId;
		}

		/**
		 * 
		 * @return The newHouseListId
		 */
		public Integer getNewHouseListId() {
			return newHouseListId;
		}

		/**
		 * 
		 * @param newHouseListId
		 *            The newHouseListId
		 */
		public void setNewHouseListId(Integer newHouseListId) {
			this.newHouseListId = newHouseListId;
		}

		/**
		 * 
		 * @return The bigImage
		 */
		public String getBigImage() {
			return bigImage;
		}

		/**
		 * 
		 * @param bigImage
		 *            The bigImage
		 */
		public void setBigImage(String bigImage) {
			this.bigImage = bigImage;
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
		 * @return The isShow
		 */
		public Integer getIsShow() {
			return isShow;
		}

		/**
		 * 
		 * @param isShow
		 *            The isShow
		 */
		public void setIsShow(Integer isShow) {
			this.isShow = isShow;
		}

	}

	public class XiamenSecondHouseQuotationMap {
		private long xiamenAvgPriceByLastMonth;
		private long xiamenSuccessNumByLastMonth;

		public long getXiamenAvgPriceByLastMonth() {
			return xiamenAvgPriceByLastMonth;
		}

		public void setXiamenAvgPriceByLastMonth(long xiamenAvgPriceByLastMonth) {
			this.xiamenAvgPriceByLastMonth = xiamenAvgPriceByLastMonth;
		}

		public long getXiamenSuccessNumByLastMonth() {
			return xiamenSuccessNumByLastMonth;
		}

		public void setXiamenSuccessNumByLastMonth(long xiamenSuccessNumByLastMonth) {
			this.xiamenSuccessNumByLastMonth = xiamenSuccessNumByLastMonth;
		}

	}

}