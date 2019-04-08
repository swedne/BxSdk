package com.zorasun.fangchanzhichuang.general.base;

import com.zorasun.fangchanzhichuang.FcgzApplaciton;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.widget.SystemBarTintManager;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 所有的Activity的基类，前期先增加一个全局异常捕获，后续增加响应的接口方法来规范代码编写
 * 
 * @author chenzhifeng
 * @e-mail seven2729@126.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年10月14日 下午2:19:56
 *
 */
@SuppressLint("NewApi")
public class BaseFragmentActivityNoSwipe extends FragmentActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		FcgzApplaciton.getInstance().addActivity(this);

		if (VERSION.SDK_INT > VERSION_CODES.JELLY_BEAN_MR2) {// 沉浸式

			Window window = getWindow();
			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

			// window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
			// WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			// // 创建状态栏的管理实例
			SystemBarTintManager tintManager = new SystemBarTintManager(this);
			// // 激活状态栏设置
			tintManager.setStatusBarTintEnabled(true);
			// // 激活导航栏设置
			// tintManager.setNavigationBarTintEnabled(true);
			// // 设置一个颜色给系统栏
			tintManager.setTintColor(getResources().getColor(R.color.transparent));
			tintManager.setStatusBarAlpha(0.0f);

			// Window window = getWindow();
			// window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
			// WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			//
			// window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
			// WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			// // 创建状态栏的管理实例
			// SystemBarTintManager tintManager = new
			// SystemBarTintManager(this);
			// // 激活状态栏设置
			// tintManager.setStatusBarTintEnabled(true);
			// // 激活导航栏设置
			// tintManager.setNavigationBarTintEnabled(true);
			// // 设置一个颜色给系统栏
			// tintManager.setTintColor(getResources().getColor(R.color.title_bg));
		}
		super.onCreate(arg0);
	}

	@Override
	protected void onResume() {
		super.onResume();

		/**
		 * 页面起始（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 不能与StatService.onPageStart一级onPageEnd函数交叉使用
		 */
		// StatService.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();

		/**
		 * 页面结束（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 不能与StatService.onPageStart一级onPageEnd函数交叉使用
		 */
		CommonUtils.hideKeyboard(this);
		// StatService.onPause(this);
	}

	public void onClickLeft(View view) {
		finish();
	}
}
