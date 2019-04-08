package com.zorasun.fangchanzhichuang.general.base;

import com.zorasun.fangchanzhichuang.general.util.CommonUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 所有的Fragment的基类，前期先增加一个全局异常捕获，后续增加响应的接口方法来规范代码编写
 * 
 * @author chenzhifeng
 * @e-mail seven2729@126.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年10月14日 下午5:19:56
 *
 */
public abstract class BaseFragment extends Fragment {
	/**
	 * 每次viewpager切换触发
	 */
	public abstract void initView();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// 注册全局的异常捕获监听
		// GlobalExceptionHanlder.getInstance().register(getActivity());
		// // 初始化参数
		// ApiConfig.init(getActivity());
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		/**
		 * Fragment页面起始 (注意： 如果有继承的父Fragment中已经添加了该调用，那么子Fragment中务必不能添加）
		 */
		// StatService.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		/**
		 * Fragment 页面结束（注意：如果有继承的父Fragment中已经添加了该调用，那么子Fragment中务必不能添加）
		 */
		CommonUtils.hideKeyboard(getActivity());
		// StatService.onPause(this);
	}

}
