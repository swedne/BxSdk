package com.zorasun.fangchanzhichuang.general.common;

/**
 * 常量类
 * 
 * @author 杨伟锦
 * @e-mail 1147953072@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2014-9-22 下午5:23:27
 */
public class SystemConstant {

	/**
	 * 第三方登录的返回的uid
	 */
	public static final String KEY_THIRD_PART_LOGIN_UID = "key_third_part_login_uid";
	/**
	 * 第三方登陆的类型
	 */
	public static final String KEY_SHARE_MEDIA = "key_share_media";
	/**
	 * 程序是不是第一次运行判断
	 */
	public static final String KEY_FIRST_RUN = "key_first_run";
	/**
	 * 订单取消时间
	 */
	public static String CANCEL_ORDER_TIME = "2";
	/**
	 * 最大库存
	 */
	public static final long STOCK_MAX = 60000;
	/**
	 * 价格最大值
	 */
	public static final double PRICE_MAX = 9999999999.99;
	/**
	 * 商品ID
	 */
	public static final String KEY_GOOD_ID = "key_good_id";
	/**
	 * 二级分类(国家)
	 */
	public static final String KEY_GROUPTWO_TYPE = "key_grouptwo_type";
	/**
	 * 二级分类ID
	 */
	public static final String KEY_GROUPTWO_ID = "key_grouptwo_id";
	public static final String KEY_GROUPONE_ID = "key_groupone_id";
	/**
	 * 二级分类title
	 */
	public static final String KEY_GROUPTWO_TITLE = "key_grouptwo_title";
	/**
	 * 页面跳转状态标志
	 */
	public static final String KEY_STATE_PAGE = "key_state_page";
	/**
	 * 跳转到OtherWebViewActivity传参
	 */
	public static final String KEY_SLIDE_URL = "key_slide_url";
	/**
	 * 跳转到OtherWebViewActivity传参
	 */
	public static final String KEY_SLIDE_TITLE = "key_slide_title";
	/**
	 * 点击对应评论状态保存
	 */
	public static final String KEY_COMMENT_STATUS = "key_comment_status";
	/**
	 * 评价总数量
	 */
	public static final String KEY_COMMENT_TOTAL = "key_comment_total";
	/**
	 * 对应评论id保存
	 */
	public static final String KEY_COMMENT_ID = "key_comment_id";
	/**
	 * 页面跳转状态到预购列表页面 或 预购详情页面
	 */
	public static final int STATE_PAGE_SHOUYE = 1;
	public static final int STATE_PAGE_JINGJIREN = 2;
	public static final int STATE_PAGE_SECONDHOUSE = 3;
	public static final int STATE_PAGE_RENTHOUSE = 4;
	public static final int STATE_PAGE_NEWHOUSE = 5;
	public static final int STATE_PAGE_SHANGYEDICHAN = 6;
	public static final int CHUZU_INTENT = 1;
	public static final int XIUGAI_PSW = 1;
	public static final int FORGETPSW_INTENT = 2;
	public static final int LASTMONTH_INTENT = 2;
	// intent请求码
	public static final int REQUEST_CODE_BIND_THIRD_ACCOUNT = 100;
	public static final int RESULT_CODE_BIND_THIRD_ACCOUNT = 200;
	public static final int RESULT_CODE_LOGIN = 201;

	public static final int REQUEST_MESSAGE = 10;

	/**
	 * 小区详情的跳转
	 */
	public static final int SHEQU_SELLSECONDEHOUSE = 1;
	public static final int SHEQU_RENTHOUSE = 2;
	public static final int SHEQU_ZHUANJIA = 3;
	/**
	 * 机构二手房、机构租房
	 */
	public static final int JIGOU_SECONDHOUSE = 1;
	public static final int JIGOU_RENTHOUSR = 2;
	public static final int JINGJIREN_SECONDHOUSE = 3;
	public static final int JINGJIREN_RENTHOUSE = 4;

	/**
	 * 页面跳转状态到秒杀列表页面 或 秒杀详情页面
	 */
	/**
	 * 
	 * /** 评论等级 -1全部，0好评，1中评，2差评
	 */
	public static final int EVALUATE_ALL = -1;
	public static final int EVALUATE_GOOD = 0;
	public static final int EVALUATE_MID = 1;
	public static final int EVALUATE_BAD = 2;

	/**
	 * 判断是否登录
	 */
	// public static int memberId = -1;
	/**
	 * 注册参数类型type
	 */
	public final static String TYPE_REGIST = "USER_REGISTER";
	public final static String TYPE_FORGET_PWD = "USER_RESET_PASS";
	public final static String TYPE_UPDATE_PWD = "LOGINPASS_RESET_SUCCESS";
	public final static String TYPE_FORGET_PAYPWD = "USER_MODIFY_PAY_PASS";

}
