package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class WidePriceEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2601802499267852950L;

	private Content content;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public class Content {
		// 小区详情参考均价
		private ArrayList<SecondHouseQuotationListByAreaList> secondHouseQuotationListByAreaList = new ArrayList<>();
		// 小区详情参考均价为空时
		private ArrayList<SecondHouseQuotationListByAreaList_null> secondHouseQuotationListByAreaList_null = new ArrayList<>();

		public ArrayList<SecondHouseQuotationListByAreaList_null> getSecondHouseQuotationListByAreaList_null() {
			return secondHouseQuotationListByAreaList_null;
		}

		public void setSecondHouseQuotationListByAreaList_null(
				ArrayList<SecondHouseQuotationListByAreaList_null> secondHouseQuotationListByAreaList_null) {
			this.secondHouseQuotationListByAreaList_null = secondHouseQuotationListByAreaList_null;
		}

		public ArrayList<SecondHouseQuotationListByAreaList> getSecondHouseQuotationListByAreaList() {
			return secondHouseQuotationListByAreaList;
		}

		public void setSecondHouseQuotationListByAreaList(
				ArrayList<SecondHouseQuotationListByAreaList> secondHouseQuotationListByAreaList) {
			this.secondHouseQuotationListByAreaList = secondHouseQuotationListByAreaList;
		}

		private ArrayList<LastMonthSuccessNumListByArea> lastMonthSuccessNumListByArea = new ArrayList<>();

		private ArrayList<XiamenSecondHouseQuotationListByMonth> xiamenSecondHouseQuotationListByMonth = new ArrayList<>();

		private ArrayList<XiamenSecondHouseQuotationListByArea> xiamenSecondHouseQuotationListByArea = new ArrayList<>();
		@Expose
		private XiamenSecondHouseQuotationMap xiamenSecondHouseQuotationMap;

		// 上月成交量和36个点空值
		private ArrayList<LastMonthSuccessNumListByArea_null> lastMonthSuccessNumListByArea_null = new ArrayList<>();
		private ArrayList<XiamenSecondHouseQuotationListByArea_null> xiamenSecondHouseQuotationListByArea_null = new ArrayList<>();

		// 上月均价和36个0点值
		private ArrayList<LastMonthAvgPriceListByArea_null> lastMonthAvgPriceListByArea_null = new ArrayList<>();
		// private ArrayList<xiamenSecondHouseQuotationListByArea_null>
		// xiamenSecondHouseQuotationListByArea_null = new ArrayList<>();

		public ArrayList<LastMonthSuccessNumListByArea_null> getLastMonthSuccessNumListByArea_null() {
			return lastMonthSuccessNumListByArea_null;
		}

		public ArrayList<LastMonthAvgPriceListByArea_null> getLastMonthAvgPriceListByArea_null() {
			return lastMonthAvgPriceListByArea_null;
		}

		public void setLastMonthAvgPriceListByArea_null(
				ArrayList<LastMonthAvgPriceListByArea_null> lastMonthAvgPriceListByArea_null) {
			this.lastMonthAvgPriceListByArea_null = lastMonthAvgPriceListByArea_null;
		}

		public void setLastMonthSuccessNumListByArea_null(
				ArrayList<LastMonthSuccessNumListByArea_null> lastMonthSuccessNumListByArea_null) {
			this.lastMonthSuccessNumListByArea_null = lastMonthSuccessNumListByArea_null;
		}

		public ArrayList<XiamenSecondHouseQuotationListByArea_null> getXiamenSecondHouseQuotationListByArea_null() {
			return xiamenSecondHouseQuotationListByArea_null;
		}

		public void setXiamenSecondHouseQuotationListByArea_null(
				ArrayList<XiamenSecondHouseQuotationListByArea_null> xiamenSecondHouseQuotationListByArea_null) {
			this.xiamenSecondHouseQuotationListByArea_null = xiamenSecondHouseQuotationListByArea_null;
		}

		public ArrayList<LastMonthSuccessNumListByArea> getLastMonthSuccessNumListByArea() {
			return lastMonthSuccessNumListByArea;
		}

		public void setLastMonthSuccessNumListByArea(
				ArrayList<LastMonthSuccessNumListByArea> lastMonthSuccessNumListByArea) {
			this.lastMonthSuccessNumListByArea = lastMonthSuccessNumListByArea;
		}

		public ArrayList<XiamenSecondHouseQuotationListByMonth> getXiamenSecondHouseQuotationListByMonth() {
			return xiamenSecondHouseQuotationListByMonth;
		}

		public void setXiamenSecondHouseQuotationListByMonth(
				ArrayList<XiamenSecondHouseQuotationListByMonth> xiamenSecondHouseQuotationListByMonth) {
			this.xiamenSecondHouseQuotationListByMonth = xiamenSecondHouseQuotationListByMonth;
		}

		public ArrayList<XiamenSecondHouseQuotationListByArea> getXiamenSecondHouseQuotationListByArea() {
			return xiamenSecondHouseQuotationListByArea;
		}

		public void setXiamenSecondHouseQuotationListByArea(
				ArrayList<XiamenSecondHouseQuotationListByArea> xiamenSecondHouseQuotationListByArea) {
			this.xiamenSecondHouseQuotationListByArea = xiamenSecondHouseQuotationListByArea;
		}

		/**
		 * 
		 * @return The xiamenSecondHouseQuotationMap
		 */
		public XiamenSecondHouseQuotationMap getXiamenSecondHouseQuotationMap() {
			return xiamenSecondHouseQuotationMap;
		}

		/**
		 * 
		 * @param xiamenSecondHouseQuotationMap
		 *            The xiamenSecondHouseQuotationMap
		 */
		public void setXiamenSecondHouseQuotationMap(XiamenSecondHouseQuotationMap xiamenSecondHouseQuotationMap) {
			this.xiamenSecondHouseQuotationMap = xiamenSecondHouseQuotationMap;
		}

	}

	public class XiamenSecondHouseQuotationMap {

		private List<XiamenSecondHouseQuotationListByArea_null> xiamenSecondHouseQuotationListByArea_null = new ArrayList<XiamenSecondHouseQuotationListByArea_null>();
		@Expose
		private List<XiamenSecondHouseQuotationListByArea> xiamenSecondHouseQuotationListByArea = new ArrayList<XiamenSecondHouseQuotationListByArea>();
		@Expose
		// 区域参考均价
		private List<XiamenSecondHouseQuotationListByMonth> xiamenSecondHouseQuotationListByMonth = new ArrayList<XiamenSecondHouseQuotationListByMonth>();
		// 区域参考均价为空时
		private List<XiamenSecondHouseQuotationListByMonth_null> xiamenSecondHouseQuotationListByMonth_null = new ArrayList<XiamenSecondHouseQuotationListByMonth_null>();
		@Expose
		private List<LastMonthAvgPriceListByArea> lastMonthAvgPriceListByArea = new ArrayList<LastMonthAvgPriceListByArea>();

		private List<LastMonthAvgPriceListByArea_null> lastMonthAvgPriceListByArea_null = new ArrayList<LastMonthAvgPriceListByArea_null>();

		public List<XiamenSecondHouseQuotationListByMonth_null> getXiamenSecondHouseQuotationListByMonth_null() {
			return xiamenSecondHouseQuotationListByMonth_null;
		}

		public void setXiamenSecondHouseQuotationListByMonth_null(
				List<XiamenSecondHouseQuotationListByMonth_null> xiamenSecondHouseQuotationListByMonth_null) {
			this.xiamenSecondHouseQuotationListByMonth_null = xiamenSecondHouseQuotationListByMonth_null;
		}

		public List<LastMonthAvgPriceListByArea_null> getLastMonthAvgPriceListByArea_null() {
			return lastMonthAvgPriceListByArea_null;
		}

		public void setLastMonthAvgPriceListByArea_null(
				List<LastMonthAvgPriceListByArea_null> lastMonthAvgPriceListByArea_null) {
			this.lastMonthAvgPriceListByArea_null = lastMonthAvgPriceListByArea_null;
		}

		public List<XiamenSecondHouseQuotationListByArea_null> getXiamenSecondHouseQuotationListByArea_null() {
			return xiamenSecondHouseQuotationListByArea_null;
		}

		public void setXiamenSecondHouseQuotationListByArea_null(
				List<XiamenSecondHouseQuotationListByArea_null> xiamenSecondHouseQuotationListByArea_null) {
			this.xiamenSecondHouseQuotationListByArea_null = xiamenSecondHouseQuotationListByArea_null;
		}

		public List<LastMonthAvgPriceListByArea> getLastMonthAvgPriceListByArea() {
			return lastMonthAvgPriceListByArea;
		}

		public void setLastMonthAvgPriceListByArea(List<LastMonthAvgPriceListByArea> lastMonthAvgPriceListByArea) {
			this.lastMonthAvgPriceListByArea = lastMonthAvgPriceListByArea;
		}

		/**
		 * 
		 * @return The xiamenSecondHouseQuotationListByArea
		 */
		public List<XiamenSecondHouseQuotationListByArea> getXiamenSecondHouseQuotationListByArea() {
			return xiamenSecondHouseQuotationListByArea;
		}

		/**
		 * 
		 * @param xiamenSecondHouseQuotationListByArea
		 *            The xiamenSecondHouseQuotationListByArea
		 */
		public void setXiamenSecondHouseQuotationListByArea(
				List<XiamenSecondHouseQuotationListByArea> xiamenSecondHouseQuotationListByArea) {
			this.xiamenSecondHouseQuotationListByArea = xiamenSecondHouseQuotationListByArea;
		}

		/**
		 * 
		 * @return The xiamenSecondHouseQuotationListByMonth
		 */
		public List<XiamenSecondHouseQuotationListByMonth> getXiamenSecondHouseQuotationListByMonth() {
			return xiamenSecondHouseQuotationListByMonth;
		}

		/**
		 * 
		 * @param xiamenSecondHouseQuotationListByMonth
		 *            The xiamenSecondHouseQuotationListByMonth
		 */
		public void setXiamenSecondHouseQuotationListByMonth(
				List<XiamenSecondHouseQuotationListByMonth> xiamenSecondHouseQuotationListByMonth) {
			this.xiamenSecondHouseQuotationListByMonth = xiamenSecondHouseQuotationListByMonth;
		}

	}

	public class XiamenSecondHouseQuotationListByMonth {

		@Expose
		private Integer month;
		@Expose
		private float allAvgPrice;

		/**
		 * 
		 * @return The month
		 */
		public Integer getMonth() {
			return month;
		}

		/**
		 * 
		 * @param month
		 *            The month
		 */
		public void setMonth(Integer month) {
			this.month = month;
		}

		/**
		 * 
		 * @return The allAvgPrice
		 */
		public float getAllAvgPrice() {
			return allAvgPrice;
		}

		/**
		 * 
		 * @param allAvgPrice
		 *            The allAvgPrice
		 */
		public void setAllAvgPrice(float allAvgPrice) {
			this.allAvgPrice = allAvgPrice;
		}

	}

	public class XiamenSecondHouseQuotationListByMonth_null {

		@Expose
		private Integer month;
		@Expose
		private float allAvgPrice;

		/**
		 * 
		 * @return The month
		 */
		public Integer getMonth() {
			return month;
		}

		/**
		 * 
		 * @param month
		 *            The month
		 */
		public void setMonth(Integer month) {
			this.month = month;
		}

		/**
		 * 
		 * @return The allAvgPrice
		 */
		public float getAllAvgPrice() {
			return allAvgPrice;
		}

		/**
		 * 
		 * @param allAvgPrice
		 *            The allAvgPrice
		 */
		public void setAllAvgPrice(float allAvgPrice) {
			this.allAvgPrice = allAvgPrice;
		}

	}

	public class XiamenSecondHouseQuotationListByArea {

		private float successNum;

		@Expose
		private String month;
		@Expose
		private String areaName;
		@Expose
		private float avgPrice;

		public float getSuccessNum() {
			return successNum;
		}

		public void setSuccessNum(float successNum) {
			this.successNum = successNum;
		}

		/**
		 * 
		 * @return The month
		 */
		public String getMonth() {
			return month;
		}

		/**
		 * 
		 * @param month
		 *            The month
		 */
		public void setMonth(String month) {
			this.month = month;
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
		 * @return The avgPrice
		 */
		public float getAvgPrice() {
			return avgPrice;
		}

		/**
		 * 
		 * @param avgPrice
		 *            The avgPrice
		 */
		public void setAvgPrice(float avgPrice) {
			this.avgPrice = avgPrice;
		}

	}

	public class XiamenSecondHouseQuotationListByArea_null {

		private float successNum;

		@Expose
		private String month;
		@Expose
		private String areaName;
		@Expose
		private float avgPrice;

		public float getSuccessNum() {
			return successNum;
		}

		public void setSuccessNum(float successNum) {
			this.successNum = successNum;
		}

		/**
		 * 
		 * @return The month
		 */
		public String getMonth() {
			return month;
		}

		/**
		 * 
		 * @param month
		 *            The month
		 */
		public void setMonth(String month) {
			this.month = month;
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
		 * @return The avgPrice
		 */
		public float getAvgPrice() {
			return avgPrice;
		}

		/**
		 * 
		 * @param avgPrice
		 *            The avgPrice
		 */
		public void setAvgPrice(float avgPrice) {
			this.avgPrice = avgPrice;
		}

	}

	public class LastMonthAvgPriceListByArea {
		private String areaName;
		private Integer avgPrice;

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public Integer getAvgPrice() {
			return avgPrice;
		}

		public void setAvgPrice(Integer avgPrice) {
			this.avgPrice = avgPrice;
		}

	}

	public class LastMonthAvgPriceListByArea_null {
		private String areaName;
		private Integer avgPrice;

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public Integer getAvgPrice() {
			return avgPrice;
		}

		public void setAvgPrice(Integer avgPrice) {
			this.avgPrice = avgPrice;
		}

	}

	public class LastMonthSuccessNumListByArea {
		private String areaName;
		private Integer successNum;

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public Integer getSuccessNum() {
			return successNum;
		}

		public void setSuccessNum(Integer successNum) {
			this.successNum = successNum;
		}

	}

	public class LastMonthSuccessNumListByArea_null {
		private String areaName;
		private Integer successNum;

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public Integer getSuccessNum() {
			return successNum;
		}

		public void setSuccessNum(Integer successNum) {
			this.successNum = successNum;
		}

	}

	public class SecondHouseQuotationListByAreaList {
		private String areaName;
		private float avgPrice;
		private Integer month;

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public float getAvgPrice() {
			return avgPrice;
		}

		public void setAvgPrice(float avgPrice) {
			this.avgPrice = avgPrice;
		}

		public Integer getMonth() {
			return month;
		}

		public void setMonth(Integer month) {
			this.month = month;
		}

	}

	public class SecondHouseQuotationListByAreaList_null {
		private String areaName;
		private float avgPrice;
		private Integer month;

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public float getAvgPrice() {
			return avgPrice;
		}

		public void setAvgPrice(float avgPrice) {
			this.avgPrice = avgPrice;
		}

		public Integer getMonth() {
			return month;
		}

		public void setMonth(Integer month) {
			this.month = month;
		}

	}

}
