package com.zorasun.fangchanzhichuang;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.GlobalExceptionHanlder;
import com.zorasun.fangchanzhichuang.general.widget.timer.TimerService;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

/**
 * 全局Applaciton
 * 
 * @author chenzhifeng
 * @e-mail seven2729@126.com
 * @version v1.0
 * @copyright 2010-2016
 * @create-time 2016年3月16日11:27:28
 *
 */
public class FcgzApplaciton extends Application {
	private static FcgzApplaciton instance;
	private List<Activity> activityList;
	private IWXAPI msgApi;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		activityList = new ArrayList<Activity>();
		// 全局异常捕获
//		GlobalExceptionHanlder.getInstance().register(this);
		// 初始化参数
		ApiConfig.init(this);
//		this.startService(new Intent(this, TimerService.class));// 开启服务
		initImageLoader(this);
		// 初始化imageLoader

		msgApi = WXAPIFactory.createWXAPI(this, null);
		// 将该app注册到微信
		msgApi.registerApp("wx281cdd9aba85cd37");
		// 微信分享 正式
//		PlatformConfig.setWeixin("wx281cdd9aba85cd37", "109bbe801d30bbe2b167b4e195b9ac3d");
//		// QQ和Qzone 正式
//		PlatformConfig.setQQZone("101254589", "807725f299a25409563eb647410a83b9");
//		// sina分享
//		PlatformConfig.setSinaWeibo("2860083973", "0db65362fb54d2d4a2e6f7ca87780c98");
//		Log.LOG = true;
		// 设置开启日志,发布时请关闭日志
		// JPushInterface.setDebugMode(Boolean.parseBoolean(getResources().getString(R.string.log)));
//		JPushInterface.setDebugMode(false);
//		// 初始化 JPush
//		JPushInterface.init(this);

	}

	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.writeDebugLogs(); // Remove for release app
		ImageLoader.getInstance().init(config.build());
	}

	public static FcgzApplaciton getInstance() {
		return instance;
	}

	public List<Activity> getActivityList() {
		return activityList;
	}

	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	public void exit() {
		ImageLoader.getInstance().clearMemoryCache();
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}

}
