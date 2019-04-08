/**
 * @Title: ChattingLoadingImageDialog.java
 * @Package com.friendou.chatmodel
 * @Description: TODO
 * Copyright: Copyright (c) 2012 
 * Company:厦门极聚网络科技有限公司
 * 
 * @author 白峰
 * @date 2012-7-30 下午5:16:05
 * @version V1.0
 */

package com.zorasun.fangchanzhichuang.general.dialog;

import com.zorasun.fangchanzhichuang.R;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @ClassName: ChattingLoadingImageDialog
 * @Description:载入数据进度页面
 * @date 2012-7-30 下午5:16:05
 */
public class ProgressBarDialog extends Dialog
{
	Activity mContext = null;
	ProgressBar pb = null;
	TextView tv = null;

	protected ProgressBarDialog (Activity context, boolean cancelable, OnCancelListener cancelListener)
	{
		super(context, cancelable, cancelListener);
		mContext = context;
	}

	public ProgressBarDialog (Activity context)
	{
		super(context);
		mContext = context;
	}

	public ProgressBarDialog (Activity context, int theme)
	{
		super(context, theme);
		mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progressbar_dialog);

		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
		dialogWindow.setAttributes(lp);

		pb = (ProgressBar) findViewById(R.id.chatting_loadimage_percent_pb);
		tv = (TextView) findViewById(R.id.chatting_loadimage_tips_tv);
	}

	public void setInfo(String aTips, int aPercent)
	{
		pb.setProgress(aPercent);
		tv.setText(aTips);
	}
}
