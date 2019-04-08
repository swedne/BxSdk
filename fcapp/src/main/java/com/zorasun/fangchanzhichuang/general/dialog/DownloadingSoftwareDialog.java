package com.zorasun.fangchanzhichuang.general.dialog;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.util.DownloadingSoftwareUtils;
import com.zorasun.fangchanzhichuang.general.util.DownloadingSoftwareUtils.DownloadCallBack;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;

import android.app.Activity;
import android.view.KeyEvent;

public class DownloadingSoftwareDialog extends ProgressBarDialog {

	String mSoftwareUrl = null;
	String fileName = null;
	DownSucessBack downSucessBack;
	private int type;

	public DownloadingSoftwareDialog(Activity context) {
		super(context);
		setTitle(R.string.version_update_title);
	}

	public void setDownloadUrl(String aSoftwareUrl, String fileName, DownSucessBack downSucessBack) {
		mSoftwareUrl = aSoftwareUrl;
		this.fileName = fileName;
		this.downSucessBack = downSucessBack;
	}

	public void setDownloadUrl(String aSoftwareUrl, int aType) {
		mSoftwareUrl = aSoftwareUrl;
		type = aType;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if (isShowing()) {

				stopDownload();

				dismiss();

				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	public void show() {
		super.show();
		if (mSoftwareUrl != null && mSoftwareUrl.length() > 0) {
			if (type == 1) {
				DownloadingSoftwareUtils.getInstance().startDownload(mSoftwareUrl, mDownloadCallBack);
			} else {
				DownloadingSoftwareUtils.getInstance().startDownload(mSoftwareUrl, fileName, mDownloadCallBack);
			}

		} else {
			ToastUtil.toastShow(mContext, R.string.version_update_no_url);
		}
	}

	public void stopDownload() {
		DownloadingSoftwareUtils.getInstance().stopDownload();
	}

	DownloadCallBack mDownloadCallBack = new DownloadCallBack() {
		@Override
		public void ondownstart() {

			setInfo(getContext().getResources().getString(R.string.version_update_wait), 0);
		}

		@Override
		public void ondownloading(int percent, String tips) {
			setInfo(tips, percent);
			setTitle(getContext().getString(R.string.version_update_title) + "(" + percent + "%)");
		}

		@Override
		public void ondownsucess() {
			if (type == 1) {
				DownloadingSoftwareUtils.getInstance().openDownloadSoftware(getContext());
			} else {
				downSucessBack.sucessBack();
			}

			cancel();
			// downSucessBack.sucessBack();
		}

		@Override
		public void ondownfaile() {
			ToastUtil.toastShow(mContext, R.string.version_update_error);
			cancel();
		}

		@Override
		public void ondownstop() {
			ToastUtil.toastShow(mContext, R.string.version_update_concel);
			cancel();
		}
	};

	public interface DownSucessBack {
		public void sucessBack(); // 下载成功
	}

}
