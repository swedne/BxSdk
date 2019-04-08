package com.zorasun.fangchanzhichuang.general.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

//viewPager 中的小圆点
public class ViewPagerIndicate extends View {
	private int circleNum = 1;
	private int radius = 5;
	private Paint paint;
	private Paint mPaintSport;
	private float offset;

	public ViewPagerIndicate(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	public void setCircleNum(int circleNum) {
		this.circleNum = circleNum;
		invalidate();
	}

	public void moveIndicator(int position) {
		offset = position * 3 * radius;
		invalidate();
	}

	public void moveIndicator(int position, float positionOffset) {
		if (-1 + circleNum == position) {
			positionOffset = 0;
		}
		offset = 3 * radius * (position + positionOffset);
		invalidate();
	}

	private void initPaint() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(0xffffffff);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);

		mPaintSport = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintSport.setColor(0xff0089fe);
		mPaintSport.setStyle(Paint.Style.FILL_AND_STROKE);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		int width = canvas.getWidth();
		canvas.translate(width / 2, radius * 2);
		for (int i = 0; i < circleNum; i++) {
			canvas.drawCircle((-1.5f * (circleNum - 1)) * radius + i * 3 * radius, 0, radius, paint);
		}

		canvas.drawCircle((-1.5f * (circleNum - 1)) * radius + offset, 0, radius, mPaintSport);

		super.onDraw(canvas);
	}

}
