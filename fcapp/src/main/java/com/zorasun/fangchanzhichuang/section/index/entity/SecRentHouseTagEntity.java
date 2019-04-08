package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class SecRentHouseTagEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3976784743962981418L;

	private Content content;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public class Content {

		private List<HouseResourceSpecialtyList> houseResourceSpecialtyList = new ArrayList<HouseResourceSpecialtyList>();

		/**
		 * 
		 * @return The houseResourceSpecialtyList
		 */
		public List<HouseResourceSpecialtyList> getHouseResourceSpecialtyList() {
			return houseResourceSpecialtyList;
		}

		/**
		 * 
		 * @param houseResourceSpecialtyList
		 *            The houseResourceSpecialtyList
		 */
		public void setHouseResourceSpecialtyList(List<HouseResourceSpecialtyList> houseResourceSpecialtyList) {
			this.houseResourceSpecialtyList = houseResourceSpecialtyList;
		}

	}

	public class HouseResourceSpecialtyList {

		private String specialtyName;

		private Integer specialtyId;

		/**
		 * 
		 * @return The specialtyName
		 */
		public String getSpecialtyName() {
			return specialtyName;
		}

		/**
		 * 
		 * @param specialtyName
		 *            The specialtyName
		 */
		public void setSpecialtyName(String specialtyName) {
			this.specialtyName = specialtyName;
		}

		/**
		 * 
		 * @return The specialtyId
		 */
		public Integer getSpecialtyId() {
			return specialtyId;
		}

		/**
		 * 
		 * @param specialtyId
		 *            The specialtyId
		 */
		public void setSpecialtyId(Integer specialtyId) {
			this.specialtyId = specialtyId;
		}

	}

}
