package com.zorasun.fangchanzhichuang.general.widget;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("Recycle")
public class LetterView extends View {

	private Paint mPaint;
	// String[] letters=new String[]
	// {
	// "#","A","B","C","D","E","F",
	// "G","H","I","J","K","L","M",
	// "N","O","P","Q","R","S","T",
	// "U","V","X","Y","Z"
	// };
	private int h;
	private int index;
	private boolean isTouch;
	private Paint mPaint1;
	private RectF rect;
	private List<String> mLetterList = new ArrayList<String>();
	private int letterColror;
	private float letterSize;
	private float pushSize;
	private float loc;
	private OnLetterTouchListener listener;
	private String letter = new String();

	public LetterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LetterViewsshuxing);
		letterColror = array.getColor(R.styleable.LetterViewsshuxing_letterColror, 0xff000000);
		letterSize = array.getDimension(R.styleable.LetterViewsshuxing_letterSizes, 15.0f);
		pushSize = array.getDimension(R.styleable.LetterViewsshuxing_pushSizes, 22.0f);
		loc = array.getDimension(R.styleable.LetterViewsshuxing_locSize, 30.0f);

		initPaint();
	}

	private void initPaint() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// mPaint.setColor(0xFFFF0000);
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setTextAlign(Align.CENTER);
		mPaint.setTextSize(20);

	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		if (mLetterList.size() > 0) {
			h = (canvas.getHeight() - 30) / mLetterList.size();
		}

		canvas.translate(loc, 0);
		for (int i = 0; i < mLetterList.size(); i++) {
			if (letter.equals(mLetterList.get(i))) {
				mPaint.setColor(Color.RED);
				mPaint.setTextSize(pushSize);
				canvas.drawText(mLetterList.get(i), 0, i * h + 30, mPaint);
			} else {
				mPaint.setTextSize(letterSize);
				mPaint.setColor(Color.BLACK);
				canvas.drawText(mLetterList.get(i), 0, i * h + 30, mPaint);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		index = (int) y / h;
		if (index < 0) {
			index = 0;
		}
		if (index > mLetterList.size() - 1) {
			index = mLetterList.size() - 1;
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			listener.onTouchDown(mLetterList.get(index));
			isTouch = true;
			break;
		case MotionEvent.ACTION_MOVE:
			listener.onTouchDown(mLetterList.get(index));
			isTouch = true;
			break;
		case MotionEvent.ACTION_UP:
			listener.onTouchUp();
			postDelayed(new Runnable() {
				@Override
				public void run() {
					isTouch = false;
					invalidate();
				}
			}, 500);
			break;
		default:
			break;
		}
		invalidate();
		return true;
	}

	public interface OnLetterTouchListener {
		void onTouchDown(String letter);

		void onTouchUp();
	}

	public void setOnLetterTouchListener(OnLetterTouchListener l) {
		listener = l;
	}

	public void setList(List<String> list) {
		mLetterList.clear();
		mLetterList.addAll(list);
		invalidate();
	}

	public void selectStyle(String letter) {
		this.letter = letter;
		invalidate();
	}
}
