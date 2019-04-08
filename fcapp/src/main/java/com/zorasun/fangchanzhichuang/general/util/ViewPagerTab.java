package com.zorasun.fangchanzhichuang.general.util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 自定义ViewPagerTab
 * 
 * @author chenzhifeng
 * @e-mail 731739299@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月22日09:32:27
 *
 */
public class ViewPagerTab extends ViewGroup {

	// private int cursorColor = Color.RED; // 线的颜色
	// private int counts = 3; // 被分成了几块
	// private float posX = 0f; // 当前X坐标的位置
	// private Paint paint;
	//
	// public ViewPagerTab(Context context, AttributeSet attrs, int
	// defStyleAttr) {
	// super(context, attrs, defStyleAttr);
	// init(context);
	// }
	//
	// public ViewPagerTab(Context context, AttributeSet attrs) {
	// super(context, attrs);
	// init(context);
	// }
	//
	// public ViewPagerTab(Context context) {
	// super(context);
	// init(context);
	// }
	//
	// private void init(Context context) {
	// // 初始化画笔
	// cursorColor = context.getResources().getColor(R.color.deep_pink);
	// paint = new Paint();
	// paint.setAntiAlias(true);
	// paint.setDither(true);
	// paint.setColor(cursorColor);
	// }
	//
	// /**
	// * 设置ViewPager有几块
	// *
	// * @param counts
	// */
	// public void setCounts(int counts) {
	// this.counts = counts;
	// }
	//
	// /**
	// * 设置坐标
	// *
	// * @param pos
	// * 当前的item
	// * @param rate
	// * 变化率
	// */
	// public void setXY(int pos, float rate) {
	// int single = getMeasuredWidth() / counts;
	// posX = pos * single + single * rate;
	// invalidate();
	// }
	//
	// @Override
	// protected void onDraw(Canvas canvas) {
	// super.onDraw(canvas);
	// paint.setStrokeWidth(getMeasuredHeight());
	// int width = getMeasuredWidth() / counts - 2;
	// canvas.drawLine(posX, 0, posX + width, 0, paint);
	// }
	private ViewPager mViewPager;
	private PageListener mPageListener = new PageListener();
	private Context mContext;

	private int mWidth;
	private int mHeight;
	private Scroller mScroller;
	private int tabNum = 3;// tab个数

	public ViewPagerTab(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		mScroller = new Scroller(mContext);
	}

	public void setTabNum(int size) {
		tabNum = size;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (getChildCount() > 0) {
			getChildAt(0).layout(0, 0, mWidth / tabNum, mHeight);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);
	}

	public void setViewPager(ViewPager viewPager) {
		this.mViewPager = viewPager;
		mViewPager.setOnPageChangeListener(mPageListener);
	}

	public void setViewPager(ViewPager viewPager, int tabNum) {
		this.mViewPager = viewPager;
		this.tabNum = tabNum;
		mViewPager.setOnPageChangeListener(mPageListener);
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
	}

	private class PageListener implements OnPageChangeListener {

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			scrollTo(-position * mWidth / tabNum - Math.round(positionOffset * mWidth / tabNum), 0);
		}

		@Override
		public void onPageSelected(int position) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

	}

}