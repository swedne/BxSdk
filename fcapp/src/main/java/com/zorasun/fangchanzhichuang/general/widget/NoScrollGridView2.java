package com.zorasun.fangchanzhichuang.general.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 没有滚动的gridview
 * 
 * @author zyj
 */
public class NoScrollGridView2 extends GridView {

	private boolean haveScrollbar;

	public NoScrollGridView2(Context context) {
		super(context);
	}

	public NoScrollGridView2(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollGridView2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 设置是否有ScrollBar，当要在ScollView中显示时，应当设置为false。 默认为 true
	 * 
	 * @param haveScrollbars
	 */
	public void setHaveScrollbar(boolean haveScrollbar) {
		this.haveScrollbar = haveScrollbar;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (haveScrollbar == false) {
			int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
			super.onMeasure(widthMeasureSpec, expandSpec);
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	// @Override
	// public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	// int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
	// MeasureSpec.AT_MOST);
	// super.onMeasure(widthMeasureSpec, expandSpec);
	// }

}
