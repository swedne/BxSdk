package com.zorasun.fangchanzhichuang.general.dialog;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义提示框
 * 
 * @author chenzhifeng
 * @e-mail 731739299@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年5月14日 下午2:19:56
 *
 */
public class RemindDialog {
	private Dialog mDialog;
	// Message
	public TextView dialog_remind_dates, dialog_remind_title;
	// 确定
	public TextView dialog_remind_cancel, dialog_remind_sure, tv_remind_property, tv_remind_idcard;

	public EditText et_remind_property, et_remind_idcard, et_remind_remark;

	public LinearLayout ll_dialog_cancel;

	public RelativeLayout rl_remind_auth;
	private Activity activity;

	public RemindDialog(Context context, String strdate) {

		activity = (Activity) context;

		mDialog = new Dialog(context, R.style.MyDialog);
		mDialog.setContentView(R.layout.dialog_remind);

		// 获取屏幕大小
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);

		Window dialogWindow = mDialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();

		mDialog.setCanceledOnTouchOutside(true);// 设置点击外面消失
		lp.width = dm.widthPixels - CommonUtils.convertDipToPx(context, 45); // 宽度
		lp.height = LayoutParams.WRAP_CONTENT; // 高度

		// 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
		dialogWindow.setAttributes(lp);

		mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					dismiss();
					return true;
				}
				return false;
			}
		});
		dialog_remind_dates = (TextView) mDialog.findViewById(R.id.dialog_remind_dates);
		dialog_remind_title = (TextView) mDialog.findViewById(R.id.dialog_remind_title);
		dialog_remind_cancel = (TextView) mDialog.findViewById(R.id.dialog_remind_cancel);
		dialog_remind_sure = (TextView) mDialog.findViewById(R.id.dialog_remind_sure);
		tv_remind_property = (TextView) mDialog.findViewById(R.id.tv_remind_property);
		tv_remind_idcard = (TextView) mDialog.findViewById(R.id.tv_remind_idcard);
		et_remind_property = (EditText) mDialog.findViewById(R.id.et_remind_property);
		et_remind_idcard = (EditText) mDialog.findViewById(R.id.et_remind_idcard);
		et_remind_remark = (EditText) mDialog.findViewById(R.id.et_remind_remark);
		ll_dialog_cancel = (LinearLayout) mDialog.findViewById(R.id.ll_dialog_cancel);
		rl_remind_auth = (RelativeLayout) mDialog.findViewById(R.id.rl_remind_auth);
		dialog_remind_dates.setText(strdate);
	}

	public void show() {
		mDialog.show();
	}

	public void dismiss() {

		if (activity != null && !activity.isFinishing()) {
			mDialog.dismiss();
		}

	}

	public void setOutside() {

		if (activity != null && !activity.isFinishing()) {
			mDialog.setCanceledOnTouchOutside(false);
		}

	}

	public void setback() {

		if (activity != null && !activity.isFinishing()) {
			mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
				@Override
				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						return true;
					}
					return false;
				}
			});
		}

	}
}
