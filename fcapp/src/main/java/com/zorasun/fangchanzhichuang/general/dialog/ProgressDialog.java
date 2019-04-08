package com.zorasun.fangchanzhichuang.general.dialog;

import com.zorasun.fangchanzhichuang.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

/**
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015-1-10 上午10:33:44
 */
public class ProgressDialog {
	protected static final String TAG = "ProgressDialog";
	private static ProgressDialog mProgressDialog = null;
	private Dialog dialog;
	private boolean isShow = false; // loading框 是否正在showing

	public static ProgressDialog getInstance() {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog();
		}
		return mProgressDialog;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	/**
	 * 得到自定义的progressDialog
	 * 
	 * @param context
	 * @param msg
	 * @return
	 */
	public void createLoadingDialog(Context context) {
		if (dialog != null && !dialog.isShowing()) {
			isShow = false;
		}

		if (!isShow) {
			isShow = true;
			if (context instanceof Activity) {
				if (!((Activity) context).isFinishing()) {
					dialog = new Dialog(context, R.style.custom_dialog);
					dialog.setCancelable(false);// 不可以用“返回键”取消
					dialog.setContentView(R.layout.dialog_loading);// 设置布局
					dialog.show();
				}
			} else {
				dialog = new Dialog(context, R.style.custom_dialog);
				dialog.setCancelable(false);// 不可以用“返回键”取消
				dialog.setContentView(R.layout.dialog_loading);// 设置布局
				if (context != null) {
					try {
						dialog.show();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}

	}

	public void dismissDialog() {
		try {
			if (dialog != null) {
				dialog.dismiss();
			}
			isShow = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
