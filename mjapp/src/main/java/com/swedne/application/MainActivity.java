package com.swedne.application;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends AppCompatActivity {

    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

//        //设置成全屏，不然有些游戏进不去
//          /*set it to be no title*/
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        /*set it to be full screen*/
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
////        MyApplication.getInstance().addActivity(this);
////        SystemBarTintManager tintManager = new SystemBarTintManager(this);
////        tintManager.setStatusBarTintEnabled(true);
////        tintManager.setStatusBarTintResource(R.color.home_color);
//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
//        try {
//            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
//                getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
//                        android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
//            }
//        } catch (Exception e) {
//        }

        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.webView);
        initWebView();
    }


    private void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setBackgroundColor(0); // 设置背景色
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //扩大比例的缩放
        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportZoom(true);
        webSettings.setUseWideViewPort(true);//关键点

        webSettings.setLoadWithOverviewMode(true);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        if (mDensity == 240) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 120) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == DisplayMetrics.DENSITY_TV) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }


        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型： 1、LayoutAlgorithm.NARROW_COLUMNS ：
         * 适应内容大小 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
         */
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        //设置不跳转浏览器
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        mWebView.loadUrl("https://m.meishij.net", null);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack(); //返回上一页面
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
