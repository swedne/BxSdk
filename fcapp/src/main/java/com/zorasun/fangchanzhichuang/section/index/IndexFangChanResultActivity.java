package com.zorasun.fangchanzhichuang.section.index;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class IndexFangChanResultActivity extends BaseActivity {

	private String address;
	private WebView webview;
	private TextView tvCurrentTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index_fang_chan_result);
		address = getIntent().getStringExtra("address");
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		tvTitle.setText("查询结果");
		webview = (WebView) findViewById(R.id.webView1);
		tvCurrentTime = (TextView) findViewById(R.id.tv_currentTime);
		tvCurrentTime.setText(getCurrentTime());
		View noResultView = findViewById(R.id.no_result);
		if (TextUtils.isEmpty(address)) {
			noResultView.setVisibility(View.VISIBLE);
			webview.setVisibility(View.GONE);
			ToastUtil.toastShow(this, "注意核对，若输入信息不完整，可能会导致产权验证结果不准确");

		} else {
			WebSettings webSettings = webview.getSettings();
			// 设置WebView属性，能够执行Javascript脚本
			webSettings.setJavaScriptEnabled(true);
			// 设置可以访问文件
			webSettings.setAllowFileAccess(true);
			// 设置支持缩放
			webSettings.setBuiltInZoomControls(true);
			// 加载需要显示的网页
			webview.loadUrl(address);
			// 设置Web视图
			webview.setWebViewClient(new webViewClient());
		}

	}

	public static String getCurrentTime() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
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
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
			webview.goBack(); // goBack()表示返回WebView的上一页面
			return true;
		}
		finish();// 结束退出程序
		return false;
	}
}
