package com.zorasun.fangchanzhichuang.section.account;

import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.general.base.BaseApi;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.section.account.entity.LoginEntity;
import com.zorasun.fangchanzhichuang.section.account.entity.MemberModel;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * 账户模块请求
 * 
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年8月4日 下午4:08:17
 */
public class AccountApi extends BaseApi {
	protected static final String TAG = "AccountApi";

	static AccountApi mInstance = null;

	public static AccountApi getInstance() {
		if (mInstance == null) {
			mInstance = new AccountApi();
		}
		return mInstance;
	}

	public static final String MEMBER_THIRD_BIND = "MEMBER_THIRD_BIND";

	private String password;

	/**
	 * 登陆
	 * 
	 * @param aContext
	 * @param account
	 * @param pwd
	 * @param aCallBack
	 */
	@SuppressLint("DefaultLocale")
	public void login(Context mContext, String account, String pwd, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("mobile", account);
		params.put("password", pwd);
		post(mContext, ApiConfig.LOGIN, params, 1, aCallBack, LoginEntity.class);
	}

	/**
	 * 获取验证码
	 * 
	 * @param mContext
	 * @param account
	 * @param type
	 *            0注册操作、1、忘记密码 2、修改密码 3、绑定账号
	 * @param aCallBack
	 */
	public void getVerfication(Context mContext, String account, String type, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("mobile", account);
		params.put("type", type);
		post(mContext, ApiConfig.VERFICATION, params, 1, aCallBack, BaseEntity.class);
	}

	/**
	 * 获取第三方验证码
	 * 
	 * @param mContext
	 * @param account
	 * @param isHasBindType
	 *            1绑定账号 0注册并绑定
	 * @param aCallBack
	 */
	public void getThirdVerfication(Context mContext, int isHasBindType, String account,
			final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("isHasBindType", isHasBindType);
		params.put("mobile", account);
		params.put("type", "thirdbind");
		post(mContext, ApiConfig.VERFICATION, params, 1, aCallBack, BaseEntity.class);
	}

	/**
	 * 注册
	 * 
	 * @param mContext
	 * @param account
	 * @param aCallBack
	 */
	public void register(Context mContext, String account, String code, String password,
			final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("mobile", account);
		params.put("code", code);
		params.put("password", password);
		post(mContext, ApiConfig.REGISTER, params, 1, aCallBack, BaseEntity.class);
	}

	/**
	 * 找回密码
	 * 
	 * @param mContext
	 * @param account
	 * @param code
	 * @param aCallBack
	 */
	public void sureFindPwd(Context mContext, String account, String code, String password,
			final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("mobile", account);
		params.put("code", code);
		params.put("newpassword", password);
		post(mContext, ApiConfig.FINDPWD, params, 1, aCallBack, BaseEntity.class);
	}

	/**
	 * 修改昵称
	 * 
	 * @param mContext
	 * @param account
	 * @param code
	 * @param aCallBack
	 */
	public void changeNickName(Context mContext, String nickName, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("nickName", nickName);
		post(mContext, ApiConfig.RENAME, params, 1, aCallBack, BaseEntity.class);
	}

	/**
	 * 修改头像
	 *
	 * @param mContext
	 * @param avatarUrl
	 * @param requestCallBack
	 */
	public void reHead(Context mContext, String avatarUrl, RequestCallBack requestCallBack) {
		RequestParams params = new RequestParams();
		params.put("avatarUrl", avatarUrl);
		post(mContext, ApiConfig.getImageUrl(avatarUrl), params, 1, requestCallBack, BaseEntity.class);
	}

	// /**
	// * 修改头像
	// *
	// * @param mContext
	// * @param avatarUrl
	// * @param requestCallBack
	// */
	// public void reHead(Context mContext, String avatarUrl, RequestCallBack
	// requestCallBack) {
	// RequestParams params = new RequestParams();
	// post(mContext, ApiConfig.getImageUrl(avatarUrl), params, 1,
	// requestCallBack, BaseEntity.class);
	// }

	/**
	 * 第三方绑定
	 * 
	 * @param mContext
	 * @param account
	 * @param aCallBack
	 */
	public void bindAccount(Context mContext, String thirdAccount, int thirdType, String thirdContent, String account,
			String verification, String password, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("thirdAccount", thirdAccount);
		params.put("thirdType", thirdType);
		params.put("thirdContent", thirdContent);
		params.put("mobile", account);
		params.put("code", verification);
		params.put("password", password);
		post(mContext, ApiConfig.BIND_ACCOUNT, params, 1, aCallBack, LoginEntity.class);
	}

	// 绑定第三方账号
	/**
	 * 第三方绑定
	 * 
	 * @param isHasBindType
	 *            1绑定已有账号 0注册并绑定
	 * @param phoneNo
	 * @param password
	 * @param type
	 *            0 QQ 1微信
	 * @param uniqueNo
	 *            QQ 微信id
	 * @param code
	 * @param nickName
	 * @param headImageUrl
	 * @param context
	 * @param callBack
	 * 
	 *
	 */
	// public void requestBindThridParty(Context context, int isHasBindType,
	// String phoneNo, String password, int type,
	// String uniqueNo, String code, String nickName, String headImageUrl,
	// RequestCallBack callBack) {
	// RequestParams params = new RequestParams();
	// post(context, ApiConfig.MINE_BIND_THIRD_PARTY, params, 1, callBack,
	// MemberModel.class);
	// }

	public void requestBindThridParty(Context context, String uniqueNo, int type, String phoneNo, int isHasBindType,
			String code, String nickName, String headImageUrl, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("uid", uniqueNo);
		params.put("loginType", type);
		params.put("mobile", phoneNo);
		params.put("isHasBindType", isHasBindType);
		params.put("code", code);
		params.put("nickName", nickName);
		params.put("password", password);
		params.put("headImageUrl", headImageUrl);
		post(context, ApiConfig.MINE_BIND_THIRD_PARTY, params, 1, callBack, MemberModel.class);
	}

	public void requestBindThridParty1(Context context, RequestParams params, RequestCallBack callBack) {
		post(context, ApiConfig.MINE_BIND_THIRD_PARTY, params, 1, callBack, MemberModel.class);
	}

	public void requestBindThridPartyNoPsw(Context context, int isHasBindType, String phoneNo, int type,
			String uniqueNo, String code, String nickName, String headImageUrl, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("isHasBindType", isHasBindType);
		params.put("mobile", phoneNo);
		params.put("loginType", type);
		params.put("uid", uniqueNo);
		params.put("code", code);
		params.put("nickName", nickName);
		params.put("headImageUrl", headImageUrl);
		post(context, ApiConfig.MINE_BIND_THIRD_PARTY, params, 1, callBack, MemberModel.class);
	}

	/**
	 * 修改密码
	 * 
	 * @param mContext
	 * @param account
	 * @param aCallBack
	 */
	public void changePwd(Context mContext, String verification, String password, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("code", verification);
		params.put("newpassword", password);
		post(mContext, ApiConfig.CHANGEPWD, params, 1, aCallBack, BaseEntity.class);
	}

	/**
	 * 修改基因报告密码
	 * 
	 * @param mContext
	 * @param account
	 * @param aCallBack
	 */
	public void findIgenePwd(Context mContext, String account, String verification, String password,
			final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("mobile", account);
		params.put("code", verification);
		params.put("password", password);
		post(mContext, ApiConfig.FINDIGENEPWD, params, 1, aCallBack, BaseEntity.class);
	}

	/**
	 * 查看是否已绑定
	 * 
	 * @param mContext
	 * @param account
	 * @param aCallBack
	 */
	public void checkIsBind(Context mContext, String thirdAccount, int thirdType, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("thirdAccount", thirdAccount);
		params.put("thirdType", thirdType);
		post(mContext, ApiConfig.CHECKISBIND, params, 1, aCallBack, LoginEntity.class);
	}
	/*
	 * 判断第三方帐号是否绑定接口第
	 * 
	 * @param uniqueNo 三方授权返回openId, 第三方授权成功后返回的openId
	 * 如579ED24E02BE2B9034944131E58412D2
	 * 
	 * @param type 第三方账号类型 0：qq 1：微信
	 */

	public void requestThridPartyIsBind(Context context, String uniqueNo, int type, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("uid", uniqueNo);
		params.put("loginType", type);
		post(context, ApiConfig.MINE_IS_BIND_THIRD_PARTY, params, 1, callBack, MemberModel.class);
	}

	// 修改个人资料start
	public void requestModifyNickName(Context context, String memberId, String nickName, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("id", memberId);
		params.put("nickName", nickName);
		post(context, ApiConfig.MINE_PERSONAL_MODIFY, params, 1, callBack, BaseEntity.class);
	}

	// 修改头像
	public void requestModifyAvatarUrl(Context context, String memberId, String avatarUrl, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("id", memberId);
		params.put("avatarUrl", avatarUrl);
		post(context, ApiConfig.MINE_PERSONAL_MODIFY, params, 1, callBack, BaseEntity.class);
	}

}
