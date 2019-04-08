package com.zorasun.fangchanzhichuang.section.account.entity;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class LoginEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Content content;

	public void setContent(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return this.content;
	}

	public class Content {

		private PublicUser publicUser;

		public PublicUser getPublicUser() {
			return publicUser;
		}

		public void setPublicUser(PublicUser publicUser) {
			this.publicUser = publicUser;
		}

		@Expose
		private String mobile;
		@Expose
		private String accountId;
		@Expose
		private String password;
		@Expose
		private String address;
		@Expose
		private Object id;
		@Expose
		private String nickName;

		/**
		 * 
		 * @return The mobile
		 */
		public String getMobile() {
			return mobile;
		}

		/**
		 * 
		 * @param mobile
		 *            The mobile
		 */
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		/**
		 * 
		 * @return The accountId
		 */
		public String getAccountId() {
			return accountId;
		}

		/**
		 * 
		 * @param accountId
		 *            The accountId
		 */
		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}

		/**
		 * 
		 * @return The password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * 
		 * @param password
		 *            The password
		 */
		public void setPassword(String password) {
			this.password = password;
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
		 * @return The id
		 */
		public Object getId() {
			return id;
		}

		/**
		 * 
		 * @param id
		 *            The id
		 */
		public void setId(Object id) {
			this.id = id;
		}

		/**
		 * 
		 * @return The nickName
		 */
		public String getNickName() {
			return nickName;
		}

		/**
		 * 
		 * @param nickName
		 *            The nickName
		 */
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
	}

	public class PublicUser {
		@Expose
		private String mobile;
		@Expose
		private String accountId;
		@Expose
		private String password;
		@Expose
		private String address;
		@Expose
		private Object id;
		@Expose
		private String nickName;

		/**
		 * 
		 * @return The mobile
		 */
		public String getMobile() {
			return mobile;
		}

		/**
		 * 
		 * @param mobile
		 *            The mobile
		 */
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		/**
		 * 
		 * @return The accountId
		 */
		public String getAccountId() {
			return accountId;
		}

		/**
		 * 
		 * @param accountId
		 *            The accountId
		 */
		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}

		/**
		 * 
		 * @return The password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * 
		 * @param password
		 *            The password
		 */
		public void setPassword(String password) {
			this.password = password;
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
		 * @return The id
		 */
		public Object getId() {
			return id;
		}

		/**
		 * 
		 * @param id
		 *            The id
		 */
		public void setId(Object id) {
			this.id = id;
		}

		/**
		 * 
		 * @return The nickName
		 */
		public String getNickName() {
			return nickName;
		}

		/**
		 * 
		 * @param nickName
		 *            The nickName
		 */
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
	}
}
