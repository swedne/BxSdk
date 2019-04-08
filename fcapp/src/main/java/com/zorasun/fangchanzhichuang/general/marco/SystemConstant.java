package com.zorasun.fangchanzhichuang.general.marco;

import com.zorasun.fangchanzhichuang.section.account.entity.InfoVersionEntity;

/**
 * 系统常量
 * 
 * @author zhouyujing
 * @e-mail 1032668839@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015-1-8 下午9:10:42
 */
public class SystemConstant {
	/**
	 * 第三方登陆的类型
	 */
	public static final String KEY_SHARE_MEDIA = "key_share_media";
	public static final int RESULT_CODE_BIND_THIRD_ACCOUNT = 200;

	/**
	 * 程序是不是第一次运行判断
	 */
	public static final String KEY_FIRST_RUN = "key_first_run";

	/**
	 * 百度推送前缀
	 */
	public static final String KENENG = "keneng";

	public static final String SP_NAME = "igene";

	public static long GET_CODE_TIME = 59 * 1000;

	public static InfoVersionEntity versionEntity;

	// 服务条款
	public final static String SERVICE_URL = "http://www.jinledou.cn/igene-interface/wx/after/about/service-iterm";

	// 微信积分等级：
	public final static String INTEGRAL_URL = "http://www.jinledou.cn/igene-interface/wx/after/about/integral-level";

	// 社区规范：
	public final static String COMMUNITY = "http://www.jinledou.cn/igene-interface/wx/after/about/community-norms";

	// 隐私政策：
	public final static String PRIVACY = "http://www.jinledou.cn/igene-interface/wx/after/about/privacy-policy";

}
