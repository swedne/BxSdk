package com.zorasun.fangchanzhichuang.general.base;

import com.zorasun.fangchanzhichuang.FcgzApplaciton;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * 所有的Activity的基类，前期先增加一个全局异常捕获，后续增加响应的接口方法来规范代码编写
 * 
 * @author chenzhifeng
 * @e-mail seven2729@126.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年10月11日 下午2:19:56
 *
 */
@SuppressLint("NewApi")
public class Base2ActivityNoSwipe extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		FcgzApplaciton.getInstance().addActivity(this);
		if (VERSION.SDK_INT > VERSION_CODES.JELLY_BEAN_MR2) // 沉浸式
		{
			// 透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			// getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
		super.onCreate(savedInstanceState);
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
		// StatService.onPause(this);
		CommonUtils.hideKeyboard(this);
	}

	/**
	 * 回退事件
	 * 
	 * @param view
	 */
	public void onClickLeft(View view) {
		finish();
	}
}
