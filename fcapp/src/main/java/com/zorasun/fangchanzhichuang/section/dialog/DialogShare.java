package com.zorasun.fangchanzhichuang.section.dialog;

import com.zorasun.fangchanzhichuang.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author
 * @e-mail
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年3月30日14:54:56
 */
public class DialogShare implements OnClickListener {
	private Dialog dialog;
	private Context context;
	private TextView tv_share_wx, tv_share_wx2, tv_share_qq, pop_share_cancel;
	RelativeLayout info_outter;
	private DialogShareCallBack mCallBack;

	public void setCallBack(DialogShareCallBack mCallBack) {
		this.mCallBack = mCallBack;
	}

	@SuppressWarnings("deprecation")
	public Dialog showDialog(Context mContext) {
		this.context = mContext;

		dialog = new Dialog(context, R.style.general_dialog);
		dialog.setContentView(R.layout.popupwindow_share);
		WindowManager windowManager = ((Activity) mContext).getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.width = (int) (display.getWidth()); // 设置宽度
		dialog.getWindow().setAttributes(lp);

		tv_share_wx = (TextView) dialog.findViewById(R.id.tv_share_wx);
		tv_share_wx2 = (TextView) dialog.findViewById(R.id.tv_share_wx2);
		tv_share_qq = (TextView) dialog.findViewById(R.id.tv_share_qq);
		pop_share_cancel = (TextView) dialog.findViewById(R.id.pop_share_cancel);
		info_outter = (RelativeLayout) dialog.findViewById(R.id.info_outter);

		tv_share_wx.setOnClickListener(this);
		tv_share_wx2.setOnClickListener(this);
		tv_share_qq.setOnClickListener(this);
		pop_share_cancel.setOnClickListener(this);
		info_outter.setOnClickListener(this);
		dialog.show();

		return dialog;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.tv_share_wx:
			dialog.dismiss();
			if (mCallBack != null) {
				mCallBack.handle(0);
			}
			break;
		case R.id.tv_share_wx2:
			dialog.dismiss();
			if (mCallBack != null) {
				mCallBack.handle(1);
			}
			break;
		case R.id.tv_share_qq:
			dialog.dismiss();
			if (mCallBack != null) {
				mCallBack.handle(2);
			}
			break;
		case R.id.info_outter:
		case R.id.pop_share_cancel:
			dialog.cancel();
			break;
		default:
			break;
		}
	}

	public interface DialogShareCallBack {
		public void handle(int type);
	}
}
