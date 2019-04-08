package com.zorasun.fangchanzhichuang.section.account;

import org.json.JSONException;
import org.json.JSONObject;

import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;
import com.zorasun.fangchanzhichuang.general.util.HttpUtils;
import com.zorasun.fangchanzhichuang.general.util.SharedPreferencesUtil;
import com.zorasun.fangchanzhichuang.section.account.entity.MemberModel;
import com.zorasun.fangchanzhichuang.section.account.entity.MemberModel.Content;
import com.zorasun.fangchanzhichuang.section.account.entity.MemberModel.ThirdLoginMap;

import android.annotation.SuppressLint;

/**
 * 用户信息操作类
 * 
 * @author zhouyujing
 * @e-mail 1032668839@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015-4-22 下午2:51:03
 */
public class AccountConfig {

	protected static final String TAG = "AccountConfig";

	/**
	 * 是否登录
	 * 
	 * @return
	 */
	public static boolean isLogin() {
		return SharedPreferencesUtil.getBoolean(SharedPreferencesUtil.IS_LOGIN, false);
	};

	/**
	 * 
	 * @param isLogin
	 * @param id
	 * @param random
	 * @param haveGeneReportPwdm
	 * @param avatarUrl
	 * @param accountName
	 * @param integral
	 * @param mobile
	 * @param email
	 * @param isOpenRemind
	 * @param remindTime
	 * @param isOpenGeneReportPwd
	 * @param genePwd
	 *            查看报告密码
	 */
	public static void saveLoginData(boolean isLogin, String id, String random, int haveGeneReportPwdm,
			String avatarUrl, String accountName, long integral, String mobile, String email, int isOpenRemind,
			String remindTime, int isOpenGeneReportPwd, String genePwd) {
		SharedPreferencesUtil.saveBoolean(SharedPreferencesUtil.IS_LOGIN, isLogin);
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_ID, id);
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.RANDOM, random);
		SharedPreferencesUtil.saveInt(SharedPreferencesUtil.ACCOUNT_HAVEIGENEPWD, haveGeneReportPwdm);
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_AVATARUL, avatarUrl);
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_NAME, accountName);
		SharedPreferencesUtil.saveLong(SharedPreferencesUtil.ACCOUNT_INTEGRAL, integral);
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_PHONE, mobile);
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_EMAIL, email);
		SharedPreferencesUtil.saveInt(SharedPreferencesUtil.ISOPENREMIND, isOpenRemind);
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.REMINDTIME, remindTime);
		SharedPreferencesUtil.saveInt(SharedPreferencesUtil.ISOPENGENEREPORTPWD, isOpenGeneReportPwd);
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.GENEPWD, genePwd);
	}

	/**
	 * 获取用户名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getAccountName() {
		return SharedPreferencesUtil.getString(SharedPreferencesUtil.ACCOUNT_NAME, "");
	}

	/**
	 * 设置用户名称
	 */
	public static void setAccountName(String accountName) {
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_NAME, accountName);
	}

	/**
	 * 获取用户电话
	 * 
	 * @param context
	 * @return
	 */
	public static String getAccountPhone() {
		return SharedPreferencesUtil.getString(SharedPreferencesUtil.ACCOUNT_PHONE, "");
	}

	/**
	 * 获取用户id
	 * 
	 * @param context
	 * @return
	 */
	public static String getAccountId() {
		return SharedPreferencesUtil.getString(SharedPreferencesUtil.ACCOUNT_ID, null);
	}

	/**
	 * 读取random
	 * 
	 * @param context
	 * @return
	 */
	public static String getAccountRandom() {
		return SharedPreferencesUtil.getString(SharedPreferencesUtil.RANDOM, "");
	}

	public static boolean checkIsOutTime(String content) {
		boolean result = false;
		JSONObject responsejson = null;
		try {
			responsejson = new JSONObject(content);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		int code = HttpUtils.getJSONInt(responsejson, "code");
		if (code == 4) // 检查判断是否是4
		{
			result = true;
		}
		return result;
	}

	/**
	 * 获取头像地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getAccountAvatarul() {
		return SharedPreferencesUtil.getString(SharedPreferencesUtil.ACCOUNT_AVATARUL, "");
	}

	/**
	 * 保存第三方数据
	 */
	@SuppressLint("SimpleDateFormat")
	public static void saveLoginData(MemberModel model) {
		Content userInfo = model.getContent();
		if (userInfo == null) {
			return;
		}
		AppLog.debug(TAG, "保存登录数据");
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_ID, userInfo.getId());
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_PHONE, userInfo.getMobile());
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_NAME, userInfo.getNickName());
		SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_AVATARUL, userInfo.getAddress());
	}

	// /**
	// * 保存登录数据
	// */
	// @SuppressLint("SimpleDateFormat")
	// public static void saveLoginData(MemberModel model) {
	// MemberModel.Content userInfo = model.getContent();
	// if (userInfo == null) {
	// return;
	// }
	// AppLog.debug(TAG, "保存登录数据");
	// SharedPreferencesUtil.saveLong(SharedPreferencesUtil.ACCOUNT_ID,
	// userInfo.id);
	// SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_PHONE,
	// userInfo.phoneNo);
	// SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_NAME,
	// userInfo.realName);
	// SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_NICK_NAME,
	// userInfo.nickName);
	// SharedPreferencesUtil.saveInt(SharedPreferencesUtil.ACCOUNT_GENDAR,
	// userInfo.gender);
	// SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_AREA,
	// userInfo.area);
	// SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_ADDRESS,
	// userInfo.address);
	// SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_AVATAR_URL,
	// userInfo.avatarUrl);
	// SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_PROVINCE,
	// userInfo.province);
	// SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_CITY,
	// userInfo.city);
	// //
	// SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_COUNTRY,
	// // userInfo.co
	// // String转换为java.util.Date
	// String str = userInfo.birthday;
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date date = null; // 初始化date
	// try {
	// if (!TextUtils.isEmpty(str)) {
	// date = sdf.parse(str); // Mon Jan 14 00:00:00 CST 2013
	// String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
	// SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_BIRTHDAY,
	// dateStr);
	// }
	//
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_POINTS,
	// userInfo.points);
	// }

	/**
	 * 保存登录状态
	 * 
	 * @param isLogin
	 */
	public static void setLoginState(boolean isLogin) {
		AppLog.debug(TAG, "保存登录状态");
		SharedPreferencesUtil.saveBoolean(SharedPreferencesUtil.IS_LOGIN, isLogin);
	}

	/**
	 * 
	 */
	public static final void setThirdLoginState(boolean isThirdLogin) {
		SharedPreferencesUtil.saveBoolean(SharedPreferencesUtil.IS_THIRD_LOGIN, isThirdLogin);
	}

}
