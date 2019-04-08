package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class HotTagEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -812222637634288042L;
	private Content content;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public class Content {

		private List<BrokerSpecialtyList> brokerSpecialtyList = new ArrayList<>();

		private List<NewHouseSpecialList> newHouseSpecialList = new ArrayList<>();
		private List<HouseResourceSpecialtyList> houseResourceSpecialtyList = new ArrayList<>();

		public List<HouseResourceSpecialtyList> getHouseResourceSpecialtyList() {
			return houseResourceSpecialtyList;
		}

		public void setHouseResourceSpecialtyList(List<HouseResourceSpecialtyList> houseResourceSpecialtyList) {
			this.houseResourceSpecialtyList = houseResourceSpecialtyList;
		}

		public List<NewHouseSpecialList> getNewHouseSpecialList() {
			return newHouseSpecialList;
		}

		public void setNewHouseSpecialList(List<NewHouseSpecialList> newHouseSpecialList) {
			this.newHouseSpecialList = newHouseSpecialList;
		}

		public List<BrokerSpecialtyList> getBrokerSpecialtyList() {
			return brokerSpecialtyList;
		}

		public void setBrokerSpecialtyList(List<BrokerSpecialtyList> brokerSpecialtyList) {
			this.brokerSpecialtyList = brokerSpecialtyList;
		}

	}

	public class BrokerSpecialtyList {
		private int specialskill_id;
		private String special_name;

		public int getSpecialskill_id() {
			return specialskill_id;
		}

		public void setSpecialskill_id(int specialskill_id) {
			this.specialskill_id = specialskill_id;
		}

		public String getSpecial_name() {
			return special_name;
		}

		public void setSpecial_name(String special_name) {
			this.special_name = special_name;
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

	public class HouseResourceSpecialtyList {
		private String specialtyName;
		private int specialtyId;

		public String getSpecialtyName() {
			return specialtyName;
		}

		public void setSpecialtyName(String specialtyName) {
			this.specialtyName = specialtyName;
		}

		public int getSpecialtyId() {
			return specialtyId;
		}

		public void setSpecialtyId(int specialtyId) {
			this.specialtyId = specialtyId;
		}

	}

}
