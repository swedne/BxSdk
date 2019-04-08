package com.zorasun.fangchanzhichuang.general.dialog;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.widget.wheelview.ScreenInfo;
import com.zorasun.fangchanzhichuang.general.widget.wheelview.WheelMain;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 时间控件dialog类
 * 
 * @author 连松青
 * @e-mail 34208535@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015-3-23 上午9:30:52
 */
public class DialogTime implements OnClickListener {
	private Dialog dialog;
	private Date defaultTime;// 默认时间
	private TimeCallBack timeCallBack;
	@SuppressWarnings("unused")
	private Context context;
	WheelMain wheelMain;
	@SuppressWarnings("unused")
	private boolean selectTime;
	@SuppressLint("SimpleDateFormat")
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public Dialog showDialog(Context context, boolean selectTime) {
		this.context = context;
		this.selectTime = selectTime;
		dialog = new Dialog(context, R.style.time_select_dialog);

		dialog.setContentView(R.layout.datepicker);

		final View timepickerview = dialog.findViewById(R.id.timePicker1);
		ScreenInfo screenInfo = new ScreenInfo((Activity) context);
		wheelMain = new WheelMain(timepickerview, selectTime);
		wheelMain.screenheight = screenInfo.getHeight();
		if (defaultTime != null) {
			wheelMain.setDefaultCalendar(defaultTime.getTime());
		} else {
			wheelMain.setDefaultCalendar(new Date().getTime());
		}
		wheelMain.initDateTimePicker();
		dialog.findViewById(R.id.btConfirm).setOnClickListener(this);
		dialog.findViewById(R.id.btCancel).setOnClickListener(this);
		dialog.findViewById(R.id.tv_blank).setOnClickListener(this);
		dialog.show();
		return dialog;
	}

	public void setTimeCallBack(TimeCallBack timeCallBack) {
		this.timeCallBack = timeCallBack;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btConfirm:
			if (timeCallBack != null) {
				timeCallBack.handle(wheelMain.getTime());
			}
			dialog.dismiss();
			break;
		case R.id.btCancel:
			// if (selectTime) {
			// if (timeCallBack != null) {
			// timeCallBack.handle("00:00:00");
			// }
			// }

			dialog.dismiss();
			break;
		case R.id.tv_blank:
			dialog.dismiss();
			break;

		default:
			break;
		}
	}

	/*
	 * 设置默认时间
	 */
	@SuppressLint("SimpleDateFormat")
	public void setTime(String timeStr) {
		if (!TextUtils.isEmpty(timeStr)) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			ParsePosition pos = new ParsePosition(0);
			Date date = formatter.parse(timeStr, pos);
			defaultTime = date;
		}
	}

	public interface TimeCallBack {
		public void handle(String timeStr);
	}
}
