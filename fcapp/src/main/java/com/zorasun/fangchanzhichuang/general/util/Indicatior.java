package com.zorasun.fangchanzhichuang.general.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class Indicatior extends View {

	private static final float RADIUS = 5;
	private Paint mPaint;
	private Paint mPaint2;
	private int mOffset = 0;

	private int num=0;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (num != 0) {
			for (int i = 0; i < num; i++) {
				canvas.drawCircle(17 + i * RADIUS * 5, 8, RADIUS, mPaint);
			}
			canvas.drawCircle(17 + mOffset, 8, RADIUS, mPaint2);
		} else {
			for (int i = 0; i < 3; i++) {
				canvas.drawCircle(17 + i * RADIUS * 3, 8, RADIUS, mPaint);
			}
			canvas.drawCircle(17 + mOffset, 8, RADIUS, mPaint2);

		}
	}

	public void movePoint(int position, float perc) {
		
		if(num!=0){
		if (position == num-1) {
			mOffset = (int) (position * RADIUS * 5);
			invalidate();
		} else {
			mOffset = (int) ((position + perc) * RADIUS * 5);
			invalidate();
		}}else{

			if (position == 2) {
				mOffset = (int) (position * RADIUS * 3);
				invalidate();
			} else {
				mOffset = (int) ((position + perc) * RADIUS * 3);
				invalidate();
			}
		}
	}

	public Indicatior(Context context, AttributeSet attrs) {
		super(context, attrs);
		intiPain();
	}

	private void intiPain() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint2.setColor(0xffEE6911);
		// ������Բ
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(1);
	}

}
