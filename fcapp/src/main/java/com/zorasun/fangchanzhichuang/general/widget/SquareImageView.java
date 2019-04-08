package com.zorasun.fangchanzhichuang.general.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageView extends ImageView {

	public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SquareImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SquareImageView(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// For simple implementation, or internal size is always 0.
		// We depend on the container to specify the layout size of
		// our view. We can't really know what it is since we will be
		// adding and removing different arbitrary views and do not
		// want the layout to change as this happens.

		// Children are just made to fill our space.
		// int childWidthSize = getMeasuredWidth();
		// int childHeightSize = getMeasuredHeight();
		// // 高度和宽度一样
		// heightMeasureSpec = widthMeasureSpec =
		// MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
		heightMeasureSpec = widthMeasureSpec;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
