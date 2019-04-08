package com.zorasun.fangchanzhichuang.general.marco;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;

import android.content.Context;
import android.text.TextUtils;

/**
 * 接口地址
 * 
 * @author zhouyujing
 * @e-mail 1032668839@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015-10-20 下午2:10:42
 */
public class ApiConfig {
	private static final String TAG = "ApiConfig";

	// 判断第三方帐号是否绑定接口
	public static final String MINE_IS_BIND_THIRD_PARTY = "/app/gz/view/account/isbind-third";
	// 绑定第三方账号
	public static final String MINE_BIND_THIRD_PARTY = "/app/gz/view/account/thirdlogin";
	// 修改昵称 真实姓名 修改头像
	public static final String MINE_PERSONAL_MODIFY = "/mobile/buyer/after/member/updateMemberById?";

	public static final String VERSION_CHECK_UPDATE = "";// 版本更新
//	public static final String VERSION_CHECK_UPDATE = "/app/view/update/checkupdate";// 版本更新

	/**
	 * 
	 * 基础地址
	 */
	public static String BASE_URL; // 服务器
	public static String IMAGE_URL;// 下载图片url
	public static Boolean IS_LOG; // 是否开启Log打印

	public static String IMAGE_URL_HEAD;// 头像、评论（案例帖子的评论）图片

	public static String IMAGE_URL_CASE;// 案例图片

	public static String IMAGE_URL_TOPIC;// 帖子图片

	public static String IMAGE_URL_SHOP;// 商品评价图片
	public static String URL_IMAGE;// 阿里云图片地址

	public static String updateUrl;

	// 图片上传
	public static final String UPLOAD_IMAGE = "/view/memberPicUpload/import-member-avatar?";

	public static void init(Context context) {
		BASE_URL = context.getString(R.string.base_url);
		IMAGE_URL = context.getString(R.string.image_url);
		IS_LOG = Boolean.parseBoolean(context.getString(R.string.log));
		IMAGE_URL_HEAD = context.getString(R.string.image_url_head);
		updateUrl = context.getString(R.string.updateUrl);
	}

	/**
	 * 图片
	 * 
	 * @param url
	 * @return
	 */
	public static String getImageUrl(String url) {
		if (url == null || (url != null && url.length() == 0)) {
			return null;
		}

		if (url != null && (url.indexOf("http://") >= 0 || url.indexOf("https://") >= 0)) {
			return url;
		}
		if (!url.startsWith("/")) {
			return IMAGE_URL + "/" + url.toString();
		}
		return IMAGE_URL + url.toString();
	}

	/**
	 * 图片地址
	 * 
	 * @param url
	 * @return
	 */
	public static String getImageUrl(String url, int type) {
		if (url == null || (url != null && url.length() == 0)) {
			return null;
		}

		if (url != null && (url.indexOf("http://") >= 0 || url.indexOf("https://") >= 0)) {
			return url;
		}
		StringBuilder imageUrl = new StringBuilder(URL_IMAGE);
		imageUrl.append("/");
		imageUrl.append(url);

		// if (BASE_URL.indexOf("192.168.1.201") > 0)
		// switch (type)
		// {
		// case ImageSizeConfig.ACTIVITY:
		// imageUrl.append(ImageSizeConfig.imageActivity);
		// break;
		// case ImageSizeConfig.PRODUCT_LIST:
		// imageUrl.append(ImageSizeConfig.imageProductList);
		// break;
		// case ImageSizeConfig.PRODUCT_DETAILS:
		// imageUrl.append(ImageSizeConfig.imageProduct);
		// break;
		// case ImageSizeConfig.USER_INFO:
		// imageUrl.append(ImageSizeConfig.imageUserInfo);
		// break;
		// default:
		// break;
		// }

		AppLog.redLog(TAG, TAG + imageUrl.toString());
		return imageUrl.toString();
	}

	/**
	 * 裁剪
	 * 
	 * @param url
	 * @return
	 */
	public static String getImageUrlCut(String url, int width, int height) {

		if (url == null || (url != null && url.length() == 0)) {
			return null;
		}

		String imageUrl;

		if (url != null && (url.indexOf("http://") >= 0 || url.indexOf("https://") >= 0)) {
			// return url;
			imageUrl = url;
		} else {
			if (!url.startsWith("/")) {
				url = "/" + url;
			}
			imageUrl = IMAGE_URL + url;
		}
		imageUrl = imageUrl + "@_" + width + "w_" + height + "h";
		AppLog.redLog(TAG, imageUrl.toString());
		return imageUrl;
	}

	/**
	 * @param url
	 *            文件下载
	 * @return
	 */
	public static String getDownUrl(String url) {
		if (url == null || (url != null && url.length() == 0)) {
			return null;
		}

		if (url != null && (url.indexOf("http://") >= 0 || url.indexOf("https://") >= 0)) {
			return url;
		}
		StringBuilder downUrl = new StringBuilder(IMAGE_URL);
		downUrl.append("/");
		downUrl.append(url);
		return downUrl.toString();
	}

	/**
	 * 上传头像
	 */
	public static final String UPLOADAVATAR = "/view/upload/submit-modify-accountlogo";
	/**
	 * 反馈意见
	 */

	public static final String FANKUIYIJIAN = "/app/gz/after/feedback/feedback";

	/**
	 * 经纪人列表
	 */

	public static final String BROKERLIST = "/app/gz/view/broker/search-broker-list";
	/**
	 * 附近的房列表
	 */

	public static final String NEARBYHOUSE = "/app/gz/view/secondhouse/nearby-house";
	/**
	 * 消息列表
	 */

	public static final String MESSAGELIST = "/app/gz/after/advices/advice-list";
	/**
	 * 公告详情
	 */

	public static final String NOTICEINFO = "/app/gz/after/advices/notice-info";
	/**
	 * 土地公
	 */

	public static final String AROUNDBROKERLIST = "/app/gz/view/broker/search-area-broker-list";

	/**
	 * 经纪人详情
	 */
	public static final String BROKERINFO = "/app/gz/view/broker/broker-info";
	/**
	 * 添加关注经纪人
	 */
	public static final String BROKERATTION = "/app/gz/after/attention/add-attention";
	/**
	 * 取消关注经纪人
	 */
	public static final String BROKERATTIONCANCLE = "/app/gz/after/attention/cancel-attention";
	/**
	 * 租房列表
	 */
	public static final String ZUFANGLIST = "/app/gz/view/renthouse/search-renthouse-list";
	/**
	 * 租房详情
	 */
	public static final String ZUFANGINFO = "/app/gz/view/renthouse/renthouse-info";
	/**
	 * 新房列表
	 */
	public static final String NEWHOUSELIST = "/app/gz/view/newhouse/search-newhouse-list";
	/**
	 * 新房详情
	 */
	public static final String NEWHOUSEINFO = "/app/gz/view/newhouse/newhouse-info";
	/**
	 * 二手房列表
	 */
	public static final String SECONDHOUSELIST = "/app/gz/view/secondhouse/search-secondhouse-list";
	/**
	 * 新房详情
	 */
	public static final String SECONDHOUSEINFO = "/app/gz/view/secondhouse/secondhouse-info";

	/**
	 * 小区详情
	 */
	public static final String AREALISTINFO = "/app/gz/view/arealist/get-arealist-info";
	/**
	 * 机构详情
	 */
	public static final String JIGOUINFO = "/app/gz/view/agency/agency-info";

	/**
	 * 获取厦门商圈，楼盘信息
	 * 
	 */
	public static final String XIAMENAREAINFO = "/app/gz/view/xiamen/xiamen-query";

	/**
	 * 发布求购，求租需求
	 */
	public static final String WANTBUYRENT = "/app/gz/view/secondhouse/buy-request";
	/**
	 * 发布出售，出租需求
	 */
	public static final String SELLRENT = "/app/gz/view/secondhouse/sell-request";
	/**
	 * 求购新房
	 */
	public static final String QIUGOUXINFANG = "/app/gz/view/newhouse/newhouse-require";
	/**
	 * 用户需求列表
	 */
	public static final String DEMANDLIST = "/app/gz/after/houserequired/required-list";
	/**
	 * 用户关注列表
	 */
	public static final String ATTENTIONLIST = "/app/gz/after/attention/attention-list";
	/**
	 * 用户需求详情
	 */
	public static final String DEMANDDETAIL = "/app/gz/after/houserequired/required-detail";
	/**
	 * 消息读取状态
	 */
	public static final String READRECORD = "/app/gz/after/advices/advice-readrecord";
	/**
	 * 小区初始化数据
	 */
	public static final String AREALISTQUERY = "/app/gz/view/xiamen/xiamen-arealist-query";
	/**
	 * 用户收藏列表
	 */
	public static final String COLLECTIONLIST = "/app/gz/after/houseresourcecollection/houseresource-collection-list";
	/**
	 * 经纪人评价
	 */
	public static final String EVALUATEDEMAND = "/app/gz/after/houserequired/required-appraise";
	/**
	 * 小区列表
	 */
	public static final String AREALIST = "/app/gz/view/xiamen/xiamen-arealist-query";
	/**
	 * 取消需求
	 */
	public static final String CANCLEDEMAND = "/app/gz/after/houserequired/required-cancel";

	/**
	 * 商业地产列表
	 */
	public static final String SHANGYEDICHANLIST = "/app/gz/view/businessestate/search-business-estate-list";
	/**
	 * 商业地产详情
	 */
	public static final String SHANGYEDICHANDETAIL = "/app/gz/view/businessestate/business-estate-info";
	/**
	 * 商业地产详情
	 */
	public static final String BROKEREVALUATE = "/app/gz/view/broker/broker-appraise";
	/**
	 * 二手房、租房、新房收藏
	 */
	public static final String HOUSECOLLECT = "/app/gz/after/houseresourcecollection/houseresource-collection-save";
	/**
	 * 二手房举报
	 */
	public static final String SECONDHOUSEREPORT = "/app/gz/view/secondhouse/secondhouse-report";
	/**
	 * 二手房、租房、新房取消收藏
	 */
	public static final String CANCLEHOUSECOLLECT = "/app/gz/after/houseresourcecollection/cancel-houseresource-collection";
	/**
	 * 获取经纪人二手房、租房更多列表
	 */
	public static final String BROKERHOUSERESLIST = "/app/gz/view/broker/broker-house-list";
	/**
	 * 获取机构详情二手房、租房更多列表
	 */
	public static final String JIGOUHOUSERESLIST = "/app/gz/view/agency/agency-info-secondhouse";
	/**
	 * 获取机构详情租房更多列表
	 */
	public static final String JIGOURENTHOUSERESLIST = "/app/gz/view/agency/agency-info-renthouse";
	/**
	 * 小区详情二手房更多
	 */
	public static final String COMMUNITYSECONDHOUSE = "/app/gz/view/arealist/get-arealist-info-secondhouse";
	/**
	 * 小区详情租房更多
	 */
	public static final String COMMUNITYRENTDHOUSE = "/app/gz/view/arealist/get-arealist-info-renthouse";
	/**
	 * 小区详情经纪人更多
	 */
	public static final String COMMUNITYBROKER = "/app/gz/view/arealist/get-arealist-info-broker";
	/**
	 * 城市列表
	 */
	public static final String ALLCITYLIST = "/app/gz/view/china/getallcity";
	/**
	 * 二手房行情（均价）
	 */
	public static final String WIDEPRICE = "/app/gz/view/quotation/secondhouse-quotation";
	/**
	 * 新房标签
	 */
	public static final String NEWHOUSETAG = "/app/gz/view/search/newhouse-specialty-label";
	/**
	 * 经纪人标签
	 */
	public static final String BROKERTAG = "/app/gz/view/search/broker-specialty-label";
	/**
	 * 二手房、租房标签
	 */
	public static final String SECONDRENTHOUSETAG = "/app/gz/view/search/house-resource-specialty-label";
	/**
	 * 地图找房列表
	 */
	public static final String MAPINFOLIST = "/app/gz/view/mapsearchhouse/search-maphouse-list";
	/**
	 * 搜索二手房、租房列表
	 */
	public static final String SEARCHLIST = "/app/gz/view/search/house-search";
	/**
	 * 搜索新房列表
	 */
	public static final String SEARCHNEWLIST = "/app/gz/view/search/newhouse-search";
	/**
	 * 地图找商业地产
	 */
	public static final String MAPBUSINESSLIST = "/app/gz/view/mapsearchhouse/search-mapbusinessestate-list";
	/**
	 * 地图找商业地产
	 */
	public static final String ADMANAGER = "/app/gz/view/admanager/get-adlist";
	/**
	 * 产权验证
	 */
	public static final String CHANQUAN = "/app/gz/after/houseresource/house-property-auc";

	/**
	 * 利率
	 */
	public static final String RATE = "/mobile/jjr/after/common/get-ratechange";
	/**
	 * 软件更新
	 */
	public static final String UPDATE_APP = "/mobile/view/updata";
	/**
	 * 登录
	 */
	public static final String LOGIN = "/app/gz/view/account/login";
	/**
	 * 注册
	 */
	public static final String REGISTER = "/app/gz/view/account/reg";
	/**
	 * 绑定第三方账号
	 */
	public static final String BIND_ACCOUNT = "/mobile/view/account/bindThirdlogin";
	/**
	 * 查找密码
	 */
	public static final String FINDPWD = "/app/gz/view/account/forget-password";
	/**
	 * 修改密码
	 */
	public static final String CHANGEPWD = "/app/gz/after/account/modifypsw";
	/**
	 * 社区界面
	 */
	public static final String COMMUNITY = "/mobile/after/topic/topic-list";
	/**
	 * 获取验证码
	 * 
	 */
	public static final String VERFICATION = "/app/gz/view/account/getsmscode";
	/**
	 * 帖子列表
	 */
	public static final String TOPICLIST = "/mobile/after/topic/talks-list";
	/**
	 * 发表帖子
	 */
	public static final String PUBLISHTOPIC = "/mobile/after/topic/publishTalk";
	/**
	 * 话题详情
	 */
	public static final String TOPICINFO = "/mobile/after/topic/talkComment-info";
	/**
	 * 评论帖子
	 */
	public static final String TOPICCOMMENT = "/mobile/after/talkComment/talkComment";

	public static final String TOPIC_COMMENT_LIST = "/mobile/after/talkComment/talkCommentList";

	/**
	 * 发表案例
	 */
	public static final String PUBLISHCASE = "/mobile/after/cases/publishCases";
	/**
	 * 评论案例
	 */
	public static final String CASECOMMENT = "/mobile/after/cases/replyCasesComment-list";
	/**
	 * 获取评论列表
	 */
	public static final String CASECOMMENTLIST = "/mobile/after/cases/casesComment-list";
	/**
	 * 案例详情
	 */
	public static final String CASEDETAIL = "/mobile/after/cases/cases-info";

	/**
	 * 积分商城
	 */
	public static final String MALLLIST = "/mobile/after/goods/index";// 商城列表
	public static final String MALLDETAIL = "/mobile/after/goods/getDetail";// 商城列表详情
	public static final String MALLEVALUATE = "/mobile/after/goods/getEvaluateList";// 商城详情评价列表
	public static final String ISCOLLECT = "/mobile/after/goods/setCollect";// 收藏商品/取消收藏
	public static final String COLLECTLIST = "/mobile/after/goods/getCollectList";// 收藏列表
	public static final String COLLECTDELETE = "/mobile/after/goods/cancleCollect";// 收藏删除
	public static final String ADDRESS = "/mobile/after/receiveAddress/getListPage";// 收货地址
	public static final String ADDRESSDEFAULT = "/mobile/after/receiveAddress/setDefault";// 默认收货地址
	public static final String ADDRESSDETELE = "/mobile/after/receiveAddress/delete";// 删除收货地址
	public static final String ADDRESSEDITORADD = "/mobile/after/receiveAddress/saveAndModify";// 修改或添加收货地址
	public static final String CARTLIST = "/mobile/after/goods/getShopCarList";// 购物车
	public static final String ADDCART = "/mobile/after/goods/putShopCar";// 加入购物车
	public static final String DELETECART = "/mobile/after/goods/deleteShopCar";// 删除购物车
	public static final String DELETEUNSELL = "/mobile/after/goods/deleteUnSell";// 一键清除购物车失效列表
	public static final String UPDATECART = "/mobile/after/goods/updateShopCar";// 修改购物车数量
	public static final String ORDERSURE = "/mobile/after/goods/toSubmitPage";// 确认订单
	public static final String ORDERLIST = "/mobile/after/order/getOrderList";// 我的订单列表
	public static final String SUBMITORDER = "/mobile/after/order/submitOrder";// 提交订单
	public static final String ORDERDETAIL = "/mobile/after/order/getDetail";// 订单详情
	public static final String ORDERCANCEL = "/mobile/after/order/cancleOrder";// 取消订单
	public static final String ORDERDELETE = "/mobile/after/order/deleteOrder";// 删除订单
	public static final String ORDERWULIU = "/mobile/after/order/getWuliu";// 查看物流
	public static final String EVALUATE = "/mobile/after/order/evaluate";// 评价商品
	public static final String EVALUATELIST = "/mobile/after/order/toEvaluateOrder";// 待评价商品
	public static final String PAYMALL = "/mobile/after/order/choosePayType";// 支付选择页面
	public static final String PAYMONEY = "/view/pay/order/payment";// 支付
	public static final String WXPAYMONEY = "/wx/after/wxpay/app-pay-execute";// 微信支付
	public static final String REMIND = "/mobile/after/order/remainDeliver";// 提醒发货
	public static final String RECEIPT = "/mobile/after/order/receipt";// 确认收货

	/**
	 * 个人信息
	 */
	public static final String PERSONINFO = "/mobile/after/account/personCen";
	/**
	 * 热搜词
	 */
	public static final String HOTWORD = "/mobile/after/hotword/list";
	/**
	 * 评论点赞
	 */
	public static final String PRAISECASE = "/mobile/after/cases/casesCommentPraiseNum";

	/**
	 * 健康评估
	 */
	public static final String APPRAISAL = "/mobile/after/question/healthPlanAssess";// 健康评估首页
	public static final String HEALTH = "/mobile/after/question/healthPlanTotal";// 健康总分
	public static final String DETECTION = "/mobile/after/question/checkQuestion";// 后天检测
	public static final String ANSWER = "/mobile/after/question/checkQuestion-answerLog";// 后天检测题目
	/**
	 * 修改昵称
	 */
	public static final String RENAME = "/app/gz/after/account/submit-modify-nickname";
	/**
	 * 上传头像
	 */
	public static final String HEAD = "/upload/submit-modify-accountlogo";
	/**
	 * 积分
	 */
	public static final String INTEGRAL = "/mobile/after/account/getIntegral";
	/**
	 * 我的收藏
	 */
	public static final String MYTOPIC = "/mobile/after/topic/talk-list";
	/**
	 * 删除我的帖子
	 */
	public static final String DELETETOPIC = "/mobile/after/topic/talk-isDelete";
	/**
	 * 我的收藏
	 */
	public static final String MYCOLLECT = "/mobile/after/topic/collect-list";
	/**
	 * 删除我的收藏
	 */
	public static final String DELETECOLLECT = "/mobile/after/talkComment/talkCollect-del";
	/**
	 * 我的健康计划列表
	 */
	public static final String MYHEALTHPLAN = "/mobile/after/healthplan/myPersonalHealthPlan-list";
	public static final String ANSWERRESULT = "/mobile/after/question/postnatalCheckLog";// 后天检测结果
	public static final String FUNDLIST = "/mobile/after/organ/list";// 基因检测列表
	public static final String PLANLIST = "/mobile/after/healthplan/healthplan-list";// 健康计划列表
	public static final String PLANDETAIL = "/mobile/after/healthplan/healthplan-info";// 健康计划详情
	public static final String RANKLIST = "/mobile/after/healthplan/healthPlanAccountRanking";// 排行列表
	public static final String JOINPLAN = "/mobile/after/healthplan/addHealthplanInfo";// 加入健康计划
	public static final String SENDEMAIL = "/mobile/after/report/send/email";// 发送邮箱

	/**
	 * 基因报告列表
	 */
	public static final String DETECTIONLIST = "/mobile/after/report/list";
	/**
	 * 爱家人列表
	 */
	public static final String FRAMILAYLIST = "/mobile/after/family/list";
	/**
	 * 获取地区
	 */
	public static final String PLACE = "/mobile/after/area/list";
	/**
	 * 慢性疾病列表
	 */
	public static final String DISEASE = "/mobile/after/chronicdisease/list";
	/**
	 * 添加家人
	 */
	public static final String ADDFRAMILY = "/mobile/after/family/add";
	/**
	 * 消息详情
	 */
	public static final String MESSAGEINFO = "/mobile/after/message/messageLogInfo-info";
	/**
	 * 设置基因报告密码
	 */
	public static final String CHANGEIGENEPWD = "/mobile/after/report/set/reportpwd";

	/**
	 * 找回基因报告密码
	 */
	public static final String FINDIGENEPWD = "/mobile/after/report/forget/reportpwd";
	/**
	 * 意见反馈
	 */
	public static final String FEEDBACK = "/mobile/after/suggest/save";
	/**
	 * 爱健康
	 */
	public static final String INDEX = "/mobile/after/account/index";
	/**
	 * 更新健康计划提醒时间
	 */
	public static final String UPDATAREMINDTIME = "/mobile/after/healthplan/healthPlan-time";
	/**
	 * 绑定邮箱
	 */
	public static final String REEMAIL = "/mobile/after/account/setEmail";
	/**
	 * 是否打开查看基因报告密码
	 */
	public static final String ISOPENGENEPWD = "/mobile/after/report/set/isopen/reportpwd";
	/**
	 * 检查是否已绑定
	 */
	public static final String CHECKISBIND = "/mobile/view/account/checkIsBind";
	/**
	 * 结束视频
	 */
	public static final String FINISHVIDEO = "/mobile/after/healthplan/personalHealthPlanLog-status";
	/**
	 * 绑定并注册
	 */
	public static final String BIND_AND_REGISTERACCOUNT = "/mobile/view/account/registerAndBindThirdlogin";
	/**
	 * 检查更新
	 */
	public static final String UPDATASOFT = "/mobile/view/update/checkupdate";

	// member(会员头像) , order (订单评论，晒单), dispute (纠纷)
	public static String generateModifyAvatarUrl(String uploadImageType) {
		String url = null;
		try {
			if (TextUtils.isEmpty(uploadImageType)) {
				return null;
			}
			url = new StringBuilder().append(IMAGE_URL_HEAD).append(UPLOAD_IMAGE).append("purpose=")
					.append(uploadImageType).toString();
		} catch (Exception e) {
		}
		return url;
	}
}
