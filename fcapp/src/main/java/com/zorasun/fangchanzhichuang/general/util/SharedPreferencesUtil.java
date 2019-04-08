package com.zorasun.fangchanzhichuang.general.util;

import com.zorasun.fangchanzhichuang.FcgzApplaciton;
import com.zorasun.fangchanzhichuang.general.marco.SystemConstant;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {
	/**
	 * 第一次启动
	 */
	public static final String IS_FIRST = "is_first_open";
	/**
	 * 
	 */
	public static final String SET_ALIAS_SUCCESS = "alias";
	public static final String IS_LOGIN = "is_login";
	/**
	 * 是否第三方登录
	 */
	public static final String IS_THIRD_LOGIN = "is_third_login";
	/**
	 * 随机数
	 */
	public static final String RANDOM = "random";
	/**
	 * 
	 */
	public static final String ACCOUNT_ID = "account_id";

	// 昵称
	public static final String HEALTH_SCORE = "healthScore";
	// 性别
	public static final String ACCOUNT_GENDAR = "account_sex";
	// 地区
	public static final String ACCOUNT_AREA = "account_area";
	// 地址
	public static final String ACCOUNT_ADDRESS = "account_address";
	// 头像
	// 省份
	public static final String ACCOUNT_PROVINCE = "account_province";
	public static final String ACCOUNT_CITY = "account_city";
	public static final String ACCOUNT_COUNTRY = "account_country";
	public static final String ACCOUNT_BIRTHDAY = "account_birthday";
	public static final String ACCOUNT_POINTS = "account_points";

	/**
	 * 
	 */
	public static final String ACCOUNT_NAME = "account_name";
	/**
	 * 手机号码
	 */
	public static final String ACCOUNT_PHONE = "account_phone";
	/**
	 * 是否设置了基因报告密码
	 */
	public static final String ACCOUNT_HAVEIGENEPWD = "account_haveigenepwd";
	/**
	 * 头像url
	 */
	public static final String ACCOUNT_AVATARUL = "account_avatarul";
	/**
	 * 积分
	 */
	public static final String ACCOUNT_INTEGRAL = "account_integral";
	/**
	 * 
	 */
	public static final String ACCOUNT_EMAIL = "account_email";
	/**
	 * 是否打开提醒
	 */
	public static final String ISOPENREMIND = "isopenremind";
	/**
	 * 提醒
	 */
	public static final String REMINDTIME = "remind";
	/**
	 * 是否打开关闭基因查看报告密码
	 */
	public static final String ISOPENGENEREPORTPWD = "isOpenGeneReportPwd";
	/**
	 * 是否收藏
	 */
	public static final String ISCOLLECT = "iscollect";//
	/**
	 * 点赞
	 */
	public static final String ISPRAISE = "ispraise";//
	/**
	 * 爱基因密码
	 */
	public static final String GENEPWD = "genepwd";
	public static final String IS_CHANGE = "is_change";// 是否退出登录
	public static final String IS_WXPAY = "is_wxpay";// 微信支付

	public static SharedPreferences getSP() {
		return FcgzApplaciton.getInstance().getSharedPreferences(SystemConstant.SP_NAME, Context.MODE_PRIVATE);
	}

	public static void saveBoolean(String key, boolean value) {
		SharedPreferences sp = getSP();
		Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static boolean getBoolean(String key, boolean defValue) {
		SharedPreferences sp = getSP();
		return sp.getBoolean(key, defValue);
	}

	public static void saveString(String key, String value) {
		SharedPreferences sp = getSP();
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static void removeString(String key) {
		SharedPreferences sp = getSP();
		Editor editor = sp.edit();
		editor.remove(key);
		editor.commit();
	}

	public static String getString(String key, String defValue) {
		SharedPreferences sp = getSP();
		return sp.getString(key, defValue);
	}

	public static void saveLong(String key, long value) {
		SharedPreferences sp = getSP();
		Editor editor = sp.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public static long getLong(String key, long defValue) {
		SharedPreferences sp = getSP();
		return sp.getLong(key, defValue);
	}

	public static void saveInt(String key, int value) {
		SharedPreferences sp = getSP();
		Editor editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static int getInt(String key, int defValue) {
		SharedPreferences sp = getSP();
		return sp.getInt(key, defValue);
	}

	public static void saveFloat(String key, Float defValue) {
		SharedPreferences sp = getSP();
		Editor editor = sp.edit();
		editor.putFloat(key, defValue);
		editor.commit();
	}

	public static double getFloat(String key, Float defValue) {
		SharedPreferences sp = getSP();
		return sp.getFloat(key, defValue);
	}

}
