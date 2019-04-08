package com.zorasun.fangchanzhichuang.general.widget;

import com.zorasun.fangchanzhichuang.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomView extends RelativeLayout {
	public static final int STATE_NONE = 0;// 全部隐藏
	// public static final int STATE_ING = 1;// 显示正在加载进度条
	public static final int STATE_EMPTY = 2;// 显示空的提示页面
	public static final int STATE_ERROR = 3;// 显示错误页面

	private View mLoadView;
	private View mLodingEmpty;
	private RelativeLayout mLodingFailed;
	private Button mBtnRefresh;
	// private View view_loding_ing;
	private OnLoadStateLinstener loadStateLinstener;

	public CustomView(Context context) {
		super(context);
		init(context);
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	@SuppressLint("InflateParams")
	private void init(Context context) {
		mLoadView = LayoutInflater.from(context).inflate(R.layout.customview_loadstatus, null);
		addView(mLoadView);
		if (isInEditMode()) {
			return;
		}
		initView();
	}

	public interface OnLoadStateLinstener {
		void onLoadData();
	}

	private void initView() {
		mLodingEmpty = findViewById(R.id.tv_loding_empty);
		mLodingEmpty.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				loadStateLinstener.onLoadData();
			}
		});
		mLodingFailed = (RelativeLayout) findViewById(R.id.rl_loding_failed);
		mBtnRefresh = (Button) findViewById(R.id.btn_loding_failed);
		mBtnRefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (loadStateLinstener != null) {
					loadStateLinstener.onLoadData();

				}
			}
		});
	}

	public void showLoadStateView(int loadState) {
		switch (loadState) {
		case STATE_NONE:
			mLodingEmpty.setVisibility(View.GONE);
			mLodingFailed.setVisibility(View.GONE);
			// view_loding_ing.setVisibility(View.GONE);
			break;
		// case STATE_ING:
		// mLodingEmpty.setVisibility(View.GONE);
		// mLodingFailed.setVisibility(View.GONE);
		// // view_loding_ing.setVisibility(View.VISIBLE);
		// break;
		case STATE_EMPTY:
			mLodingEmpty.setVisibility(View.VISIBLE);
			mLodingFailed.setVisibility(View.GONE);
			// view_loding_ing.setVisibility(View.GONE);
			break;
		case STATE_ERROR:
			mLodingEmpty.setVisibility(View.GONE);
			mLodingFailed.setVisibility(View.VISIBLE);
			// view_loding_ing.setVisibility(View.GONE);
			break;

		default:
			break;
		}
	}

	public void setFailText(String str) {
		((TextView) findViewById(R.id.tv_loding_failed)).setText(str);
	}

	public void setEmptyText(String str) {
		// mLodingEmpty.setText(str);
	}

	public void setLoadStateLinstener(OnLoadStateLinstener loadStateLinstener) {
		this.loadStateLinstener = loadStateLinstener;
	}

}
