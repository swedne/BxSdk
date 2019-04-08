package com.zorasun.fangchanzhichuang.section.account.entity;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

/**
 * 第三方登录类
 */
public class MemberModel extends BaseEntity {

	private static final long serialVersionUID = 425621163412413305L;

	@Expose
	private Content content;

	public class Content implements Serializable {

		private static final long serialVersionUID = -3974378868597531084L;

		@Expose
		private ThirdLoginMap thirdLoginMap;

		private String address;
		private String mobile;
		private String nickName;
		private String id;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		/**
		 * 
		 * @return The thirdLoginMap
		 */
		public ThirdLoginMap getThirdLoginMap() {
			return thirdLoginMap;
		}

		/**
		 * 
		 * @param thirdLoginMap
		 *            The thirdLoginMap
		 */
		public void setThirdLoginMap(ThirdLoginMap thirdLoginMap) {
			this.thirdLoginMap = thirdLoginMap;
		}

	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public class ThirdLoginMap {

		@Expose
		private String publicUserId;
		@Expose
		private String password;
		@Expose
		private String nickName;
		@Expose
		private Object thirdIsVerified;
		@Expose
		private String mobile;
		@Expose
		private String headUrl;
		@Expose
		private Integer thirdLoginType;
		@Expose
		private Object thirdAccessToken;
		@Expose
		private String thirdUid;
		@Expose
		private String thirdNickName;
		@Expose
		private String thirdHeadUrl;

		/**
		 * 
		 * @return The publicUserId
		 */
		public String getPublicUserId() {
			return publicUserId;
		}

		/**
		 * 
		 * @param publicUserId
		 *            The publicUserId
		 */
		public void setPublicUserId(String publicUserId) {
			this.publicUserId = publicUserId;
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

		/**
		 * 
		 * @return The thirdIsVerified
		 */
		public Object getThirdIsVerified() {
			return thirdIsVerified;
		}

		/**
		 * 
		 * @param thirdIsVerified
		 *            The thirdIsVerified
		 */
		public void setThirdIsVerified(Object thirdIsVerified) {
			this.thirdIsVerified = thirdIsVerified;
		}

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
		 * @return The headUrl
		 */
		public String getHeadUrl() {
			return headUrl;
		}

		/**
		 * 
		 * @param headUrl
		 *            The headUrl
		 */
		public void setHeadUrl(String headUrl) {
			this.headUrl = headUrl;
		}

		/**
		 * 
		 * @return The thirdLoginType
		 */
		public Integer getThirdLoginType() {
			return thirdLoginType;
		}

		/**
		 * 
		 * @param thirdLoginType
		 *            The thirdLoginType
		 */
		public void setThirdLoginType(Integer thirdLoginType) {
			this.thirdLoginType = thirdLoginType;
		}

		/**
		 * 
		 * @return The thirdAccessToken
		 */
		public Object getThirdAccessToken() {
			return thirdAccessToken;
		}

		/**
		 * 
		 * @param thirdAccessToken
		 *            The thirdAccessToken
		 */
		public void setThirdAccessToken(Object thirdAccessToken) {
			this.thirdAccessToken = thirdAccessToken;
		}

		/**
		 * 
		 * @return The thirdUid
		 */
		public String getThirdUid() {
			return thirdUid;
		}

		/**
		 * 
		 * @param thirdUid
		 *            The thirdUid
		 */
		public void setThirdUid(String thirdUid) {
			this.thirdUid = thirdUid;
		}

		/**
		 * 
		 * @return The thirdNickName
		 */
		public String getThirdNickName() {
			return thirdNickName;
		}

		/**
		 * 
		 * @param thirdNickName
		 *            The thirdNickName
		 */
		public void setThirdNickName(String thirdNickName) {
			this.thirdNickName = thirdNickName;
		}

		/**
		 * 
		 * @return The thirdHeadUrl
		 */
		public String getThirdHeadUrl() {
			return thirdHeadUrl;
		}

		/**
		 * 
		 * @param thirdHeadUrl
		 *            The thirdHeadUrl
		 */
		public void setThirdHeadUrl(String thirdHeadUrl) {
			this.thirdHeadUrl = thirdHeadUrl;
		}

	}

}
