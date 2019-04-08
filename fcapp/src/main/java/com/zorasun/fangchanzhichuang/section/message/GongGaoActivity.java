package com.zorasun.fangchanzhichuang.section.message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.message.entity.MyMessageEntity;
import com.zorasun.fangchanzhichuang.section.senddemand.SendDemandApi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class GongGaoActivity extends BaseActivity implements OnLoadStateLinstener {

	private int advicesId;
	// private TextView tvTitle;
	// private TextView tvContent;
	// private TextView tvTime;
	// private String picUrl;
	// private ImageView imgGongGao;
	private WebView mWeb;
	private CustomView customview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gong_gao);
		initView();
		initData();
	}

	private void initData() {
		advicesId = getIntent().getIntExtra("advicesId", -1);
		// picUrl = getIntent().getStringExtra("picUrl");
		// if (!TextUtils.isEmpty(picUrl)) {
		// AsyncImageLoader.setAsynImages(imgGongGao, picUrl);
		// }
		// tvTitle.setText(title);
		// tvContent.setText(content);
		// tvTime.setText(convertTime(time));
		initRecord();
		MessageApi.getInstance().requestNoticeInfo(this, advicesId, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				MyMessageEntity notice = (MyMessageEntity) object;
				String messageUrl = notice.getContent().getMessageUrl();
				if (TextUtils.isEmpty(messageUrl)) {
					mWeb.setVisibility(View.GONE);
					customview.showLoadStateView(CustomView.STATE_EMPTY);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
					WebSettings webSettings = mWeb.getSettings();
					// 设置WebView属性，能够执行Javascript脚本
					webSettings.setJavaScriptEnabled(true);
					// 设置可以访问文件
					webSettings.setAllowFileAccess(true);
					// 设置支持缩放
					webSettings.setBuiltInZoomControls(true);
					// 加载需要显示的网页
					mWeb.loadUrl(messageUrl);
					// 设置Web视图
					mWeb.setWebViewClient(new webViewClient());
				}
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(GongGaoActivity.this, getResources().getString(R.string.net_error));
				customview.showLoadStateView(CustomView.STATE_ERROR);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				customview.showLoadStateView(CustomView.STATE_EMPTY);
			}
		});
	}

	private void initView() {
		TextView tvTitleName = (TextView) findViewById(R.id.title_name);
		tvTitleName.setText("公告详情");
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		mWeb = (WebView) findViewById(R.id.web_notice);
		findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				GongGaoActivity.super.onBackPressed();
			}
		});
		// tvTitle = (TextView) findViewById(R.id.tv_title);
		// tvContent = (TextView) findViewById(R.id.tv_content);
		// tvTime = (TextView) findViewById(R.id.tv_time);
		// imgGongGao = (ImageView) findViewById(R.id.img_gonggao);
	}

	// Web视图
	private class webViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	// 设置回退
	// 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWeb.canGoBack()) {
			mWeb.goBack(); // goBack()表示返回WebView的上一页面
			return true;
		}
		finish();// 结束退出程序
		return false;
	}

	private void initRecord() {
		SendDemandApi.getInstance().requestReadRecord(this, advicesId, AccountConfig.getAccountId(),
				new RequestCallBack() {

					@Override
					public void onSuccess(int code, String msg, Object object) {

					}

					@Override
					public void onNetworkError() {

					}

					@Override
					public void onFailure(int code, String msg, Object object) {

					}
				});
	}

	@Override
	public void onLoadData() {
		initData();
	}

	private String convertTime(long mills) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(mills);
		return formatter.format(calendar.getTime());
	}
}
