package com.zorasun.fangchanzhichuang.section.my;

import java.io.File;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.dialog.DownloadingSoftwareDialog;
import com.zorasun.fangchanzhichuang.general.dialog.GeneralDialog;
import com.zorasun.fangchanzhichuang.general.dialog.ProgressDialog;
import com.zorasun.fangchanzhichuang.general.dialog.RemindDialog;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.AppHelper;
import com.zorasun.fangchanzhichuang.general.util.DataCleanManager;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.account.LoginActivity;
import com.zorasun.fangchanzhichuang.section.my.MyApi.VersionUpdateCallback;
import com.zorasun.fangchanzhichuang.section.my.entiy.InfoVersionEntity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingActivity extends BaseActivity implements OnClickListener {

	private TextView tvCache;
	private ProgressDialog progressDialog;
	protected static final String TAG = "SetActivity";
	int showLoading = 1; // 是否显示loading
	boolean repeat = false; // 是否可重复提交
	boolean update = true; // 防止重复提交

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		inittitleBar();
		initView();
	}

	private void inittitleBar() {
		// TODO Auto-generated method stub
		TextView title_name = (TextView) findViewById(R.id.title_name);
		title_name.setText("设置");
		findViewById(R.id.rl_adoutme).setOnClickListener(this);
		findViewById(R.id.rl_contactme).setOnClickListener(this);
		findViewById(R.id.rl_cleancache).setOnClickListener(this);
		findViewById(R.id.rl_version).setOnClickListener(this);
	}

	private void initView() {

		RelativeLayout rl_exit = (RelativeLayout) findViewById(R.id.rl_exit);
		rl_exit.setOnClickListener(this);
		if (!AccountConfig.isLogin()) {
			rl_exit.setVisibility(View.GONE);
		}
		tvCache = (TextView) findViewById(R.id.tv_cachenum);
		String cacheSize = DataCleanManager.getCacheSize(getApplicationContext());
		tvCache.setText(cacheSize);
		TextView tvVersion = (TextView) findViewById(R.id.tv_version);
		tvVersion.setText(AppHelper.getVersionName(this));
	}

	/**
	 * 清除缓存
	 */
	private void cleanCache() {

		if (tvCache.getText().toString().equals("0K")) {
			ToastUtil.toastShow(SettingActivity.this, "暂无缓存，无需清除");
			return;
		}
		progressDialog = new ProgressDialog();
		final GeneralDialog dd = new GeneralDialog();
		dd.showDialog(this, "确定要删除缓存吗?");
		dd.sure(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dd.dismiss();
				progressDialog.createLoadingDialog(SettingActivity.this);
				try {

					new Thread() {
						@Override
						public void run() {

							super.run();
							// 清空数据缓存
							DataCleanManager.cleanExternalCache(SettingActivity.this);
							DataCleanManager.cleanInternalCache(SettingActivity.this);
							DataCleanManager.cleanFiles(SettingActivity.this);
							DataCleanManager.cleanLog(SettingActivity.this);
							handler.sendEmptyMessage(1);
						}
					}.start();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				progressDialog.dismissDialog();
				// tvCache.setText(DataCleanManager.getCacheSize(SettingActivity.this));
				tvCache.setText("0K");
				ToastUtil.toastShow(SettingActivity.this, "已清除缓存");

				if (getExternalCacheDir() != null && getExternalCacheDir().exists()
						&& getExternalCacheDir().isDirectory()) {
					for (File item : getExternalCacheDir().listFiles()) {
						if (!item.isDirectory()) {
							AppLog.redLog(TAG, getExternalCacheDir().getAbsolutePath() + "___2__>"
									+ getExternalCacheDir().getName());
						}
					}
				}

				if (getCacheDir() != null && getCacheDir().exists() && getCacheDir().isDirectory()) {
					for (File item : getCacheDir().listFiles()) {
						if (!item.isDirectory()) {
							AppLog.redLog(TAG, getCacheDir().getAbsolutePath() + "____2__>" + getCacheDir().getName());
						}
					}
				}
				if (getFilesDir() != null && getFilesDir().exists() && getFilesDir().isDirectory()) {
					for (File item : getFilesDir().listFiles()) {
						if (!item.isDirectory()) {
							AppLog.redLog(TAG, getFilesDir().getAbsolutePath() + "__3__>" + getFilesDir().getName());
						}
					}
				}
				break;

			default:
				break;
			}
		}
	};
	private GeneralDialog dialog;
	protected DownloadingSoftwareDialog mVersionUpdateDialog;
	private ProgressDialog dialogs;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// 关于我们
		case R.id.rl_adoutme:
			Intent aboutUsintent = new Intent(this, AboutUsActivity.class);
			startActivity(aboutUsintent);
			break;
		// 联系我们
		case R.id.rl_contactme:
			Intent contactUsintent = new Intent(this, ContactUsActivity.class);
			startActivity(contactUsintent);
			break;
		// 退出登录
		case R.id.rl_exit:
			AccountConfig.saveLoginData(false, null, null, 0, null, null, -1, null, null, 0, null, 0, null);
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
			break;
		// 清除缓存
		case R.id.rl_cleancache:
			cleanCache();
			break;
		// 版本更新
		case R.id.rl_version:
			Update();
			break;
		default:
			break;
		}

	}

	/**
	 * 版本更新
	 */
	void Update() {
		dialogs = new ProgressDialog();
		dialogs.createLoadingDialog(SettingActivity.this);
		MyApi.getInstance().checkVersionUpdate(SettingActivity.this, AppHelper.getVersionCode(SettingActivity.this),
				showLoading, repeat, new VersionUpdateCallback() {
					@Override
					public void onUpdate(int code, final InfoVersionEntity infoVersionEntity, String msg) {
						dialogs.dismissDialog();
						if (infoVersionEntity != null) {
							// 相关操作
							final RemindDialog dialog = new RemindDialog(SettingActivity.this,
									getResources().getString(R.string.version_is_update));
							dialog.dialog_remind_sure.setOnClickListener(new OnClickListener() {// 确定
								@Override
								public void onClick(View v) {
									dialog.dismiss();
									mVersionUpdateDialog = new DownloadingSoftwareDialog(SettingActivity.this);
									mVersionUpdateDialog.setDownloadUrl(ApiConfig.updateUrl + infoVersionEntity.url, 1);
									mVersionUpdateDialog.setCancelable(false);
									mVersionUpdateDialog.show();
								}

							});
							dialog.dialog_remind_cancel.setOnClickListener(new OnClickListener() {// 取消
								@Override
								public void onClick(View v) {
									dialog.dismiss();
								}
							});
							dialog.setOutside();
							dialog.show();
						}
					}

					@Override
					public void onNetworkError() {
						dialogs.dismissDialog();
						ToastUtil.toastShow(SettingActivity.this, R.string.net_error);
					}

					@Override
					public void onInUpdate(int code, String msg) {
						dialogs.dismissDialog();
						final GeneralDialog dialog = new GeneralDialog();
						dialog.showDialog(SettingActivity.this, getResources().getString(R.string.version_newest));
						dialog.setOneButton(getResources().getString(R.string.sure));
						dialog.sure(new OnClickListener() {
							@Override
							public void onClick(View v) {// 确定
								dialog.dismiss();
							}
						});
					}
				});
	}

}
