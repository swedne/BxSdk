package com.zorasun.fangchanzhichuang.general.widget;

import java.util.List;

import com.zorasun.fangchanzhichuang.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TabsContainer extends LinearLayout
{
	public static final float RATIO_TRIANGLE_WIDTH = 1.0f / 6.0f;

	private ViewPagerCustom mViewPager;

	private OnPageChangeListener listner;

	private Paint mPaint;

	private Paint mPaintDivider;

	private Path mPath;

	private int mTabsItemColorNormal = Color.LTGRAY;

	private int mTabsItemColorHeightlight = Color.DKGRAY;

	private int mTabsItemCount = 2;

	private int mBottomLineWidth;

	private int mBottomLineHight = 3;

	private int mBottomColor = Color.DKGRAY;

	// 等腰三角形的底边长度 和父布局的宽度比为1:6
	private float mTriangleWidth = getScreenWidth() / mTabsItemCount * RATIO_TRIANGLE_WIDTH;

	// 等腰三角形底边和腰之间的角度
	private double mTriangleAngle = 30.0;

	private float mInitTrianglePos;

	private float mScrollPos;

	private Shape mShape = Shape.LINE;

	private final int rate = 1;

	private boolean hasDivider = true;

	private int mDividerColor = getResources().getColor(R.color.line_gray);// Color.LTGRAY;

	public TabsContainer(Context context)
	{
		this(context, null);
	}

	public TabsContainer(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		// 自定义属性
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabsContainer);
		if (a.hasValue(R.styleable.TabsContainer_tabsItemColorNormal))
		{
			mTabsItemColorNormal = a.getColor(R.styleable.TabsContainer_tabsItemColorNormal, Color.LTGRAY);
		}
		if (a.hasValue(R.styleable.TabsContainer_tabsItemColorHeightlight))
		{
			mTabsItemColorHeightlight = a.getColor(R.styleable.TabsContainer_tabsItemColorHeightlight, Color.DKGRAY);
		}
		if (a.hasValue(R.styleable.TabsContainer_tabsItemCount))
		{
			mTabsItemCount = a.getInt(R.styleable.TabsContainer_tabsItemCount, 3);
		}
		if (a.hasValue(R.styleable.TabsContainer_bottomLineHeight))
		{
			mBottomLineHight = a.getInt(R.styleable.TabsContainer_bottomLineHeight, 3);
		}
		if (a.hasValue(R.styleable.TabsContainer_bottomLineColor))
		{
			mBottomColor = a.getColor(R.styleable.TabsContainer_bottomLineColor, Color.DKGRAY);
		}
		if (a.hasValue(R.styleable.TabsContainer_bottomTriangleAngle))
		{
			mTriangleAngle = a.getInt(R.styleable.TabsContainer_bottomTriangleAngle, 30);
		}
		if (a.hasValue(R.styleable.TabsContainer_bottomLineShape))
		{
			mShape = Shape.mapIntToValue(a.getInteger(R.styleable.TabsContainer_bottomLineShape, 0));
		}
		a.recycle();

		// 初始化画笔(bottom line)
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(mBottomColor);
		mPaint.setStyle(Style.FILL);
		mPaint.setPathEffect(new CornerPathEffect(3));

		mPaintDivider = new Paint();
		mPaintDivider.setStrokeWidth(0);
		mPaintDivider.setAntiAlias(true);
		mPaintDivider.setColor(mDividerColor);
		mPaintDivider.setStyle(Style.FILL);
		mPaintDivider.setPathEffect(new CornerPathEffect(3));
	}

	// ViewGroup容器组件的绘制，当它没有背景时直接调用的是dispatchDraw()方法, 而绕过了draw()方法，
	// 当它有背景的时候就调用draw()方法, 再调用dispatchDraw()方法
	@Override
	protected void dispatchDraw(Canvas canvas)
	{
		canvas.save();

		if (mShape == Shape.LINE)
		{
			// 画线
			canvas.translate(0 + mScrollPos, getHeight());
			canvas.drawPath(mPath, mPaint);
		} else if (mShape == Shape.TRIANGLE)
		{
			// 画三角形
			canvas.translate(mInitTrianglePos + mScrollPos, getHeight());
			canvas.drawPath(mPath, mPaint);
		} else if (mShape == Shape.HEART)
		{
			// 画桃心
			canvas.translate(mInitTrianglePos + mScrollPos, getHeight() / 2 + 55);
			canvas.drawPath(mPath, mPaint);
		}

		canvas.restore();

		canvas.save();
		// 底线
//		Log.e("AndroidRuntime", "getHeight" + getHeight());
//		Log.e("AndroidRuntime", "getBottom" + getBottom());
//		Log.e("AndroidRuntime", "getChildAt(0).getBottom" + getChildAt(0).getBottom());
		canvas.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1, mPaintDivider);
		// 分割线
		if (hasDivider)
		{
			for (int i = 0; i < mTabsItemCount - 1; i++)
			{
				View view = getChildAt(i);
				if (view != null)
				{
					canvas.drawLine(view.getRight(), 0, view.getRight(), view.getHeight(), mPaintDivider);
				}
			}
		}
		canvas.restore();
		super.dispatchDraw(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);

		if (mShape == Shape.LINE)
		{
			mBottomLineWidth = w / mTabsItemCount;
			initBottomLine();

		} else if (mShape == Shape.TRIANGLE)
		{
			mInitTrianglePos = w / mTabsItemCount / 2 - mTriangleWidth / 2;
			initBottomTriangle();
		} else if (mShape == Shape.HEART)
		{
			mInitTrianglePos = w / mTabsItemCount / 2;
			initBottomHeart();
		}
	}

	private void initBottomTriangle()
	{
		// 三角形的高
		float height = (float) (Math.tan(mTriangleAngle * Math.PI / 180) * (mTriangleWidth / 2));

		// 三角形指示器的path
		mPath = new Path();
		mPath.moveTo(0, 0);
		mPath.lineTo(mTriangleWidth, 0);
		mPath.lineTo(mTriangleWidth / 2, -height);
		mPath.close();
	}

	private void initBottomLine()
	{
		// 线指示器的path
		mPath = new Path();
		mPath.moveTo(50, 0);
		mPath.lineTo(mBottomLineWidth - 50, 0);
		mPath.lineTo(mBottomLineWidth - 50, -mBottomLineHight);
		mPath.lineTo(50, -mBottomLineHight);
		mPath.close();
	}

	private void initBottomHeart()
	{
		// 路径的起始点
		mPath = new Path();
		mPath.moveTo(0, 0);
		// 根据心形函数画图
		for (double i = 0; i <= 2 * Math.PI; i += 0.001)
		{
			float x = (float) (16 * Math.sin(i) * Math.sin(i) * Math.sin(i));
			float y = (float) (13 * Math.cos(i) - 5 * Math.cos(2 * i) - 2 * Math.cos(3 * i) - Math.cos(4 * i));
			x *= rate;
			y *= rate;
			mPath.lineTo(x, -y);
		}
	}

	/**
	 * 获得屏幕宽度
	 * 
	 * @return pixel
	 */
	private int getScreenWidth()
	{
		int x = getResources().getDisplayMetrics().widthPixels;
		return x;
	}

//	/**
//	 * 获得屏幕高度
//	 * 
//	 * @return pixel
//	 */
//	private int getScreenHeight()
//	{
//		int y = getResources().getDisplayMetrics().heightPixels;
//		return y;
//	}

	/**
	 * 设置TabsContainer中的ViewPager滑动监听
	 * 
	 * @param viewPager
	 * @param pos
	 *            默认显示的页码，从 0 开始
	 * @param noScroll
	 *            是否可以滑动
	 */
	public void setViewPager(ViewPagerCustom viewPager, int pos, boolean noScroll)
	{
		mViewPager = viewPager;
		// 设置是否可滑动
		mViewPager.setNoScroll(noScroll);
		// 设置ViewPager当前页和高亮
		mViewPager.setCurrentItem(pos);
		setHightlight(pos);
		mViewPager.setOnPageChangeListener(new ViewPagerCustom.OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int position)
			{
				if (listner != null)
				{
					listner.onPageSelected(position);
				}
				setHightlight(position);
			}

			@Override
			public void onPageScrolled(int position, float offset, int px)
			{
				if (listner != null)
				{
					listner.onPageScrolled(position, offset, px);
				}
				scroll(position, offset);

			}

			@Override
			public void onPageScrollStateChanged(int state)
			{
				if (listner != null)
				{
					listner.onPageScrollStateChanged(state);
				}
			}
		});
	}

	/**
	 * 设置TabsContainer中的ViewPager滑动监听
	 * 
	 * @param viewPager
	 * @param pos
	 *            默认显示的页码，从 0 开始
	 */
	public void setViewPager(ViewPagerCustom viewPager, int pos)
	{
		this.setViewPager(viewPager, pos, false);
	}

	/**
	 * 设置指示器滚动
	 * 
	 * @param position
	 *            位置
	 * @param offset
	 *            偏移率
	 */
	private void scroll(int position, float offset)
	{
		mScrollPos = getScreenWidth() / mTabsItemCount * (position + offset);
		// 重绘，调用dispatchDraw()方法
		invalidate();
	}

	/**
	 * 添加Tabs标题
	 * 
	 * @param titles
	 *            标题的容器
	 */
	public void setTabTitles(List<String> titles)
	{
		mTabsItemCount = titles.size();
		if (titles != null && !titles.isEmpty())
		{
			removeAllViews();
			for (String title : titles)
			{
				addView(getTextViewInLinearLayout(title));
			}

			setOnItemClickListner();
		}
	}

	/**
	 * 在线性布局中动态添加TextView
	 * 
	 * @param title
	 * @return TextView
	 */
	private TextView getTextViewInLinearLayout(String title)
	{
		TextView textView = new TextView(getContext());
		textView.setText(title);
		textView.setGravity(Gravity.CENTER);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.weight = 1;
		textView.setLayoutParams(params);
		return textView;
	}

	/**
	 * 添加Tabs标题(自定义布局)
	 * 
	 * @param views
	 */
	public void setTabViews(List<View> views)
	{
		if (views != null && !views.isEmpty())
		{
			removeAllViews();
			for (View view : views)
			{
				LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				params.weight = 1;
				view.setLayoutParams(params);
				addView(view);
			}
			setOnItemClickListner();
		}
	}

	/**
	 * 设置选中Tab高亮
	 * 
	 * @param position
	 *            高亮的位置
	 */
	private void setHightlight(int position)
	{
		setDefaultColor();
		View view = getChildAt(position);
//		view.setSelected(true);
		if (view instanceof TextView)
		{
			((TextView) view).setTextColor(mTabsItemColorHeightlight);
//			((TextView) view).setBackgroundColor(mBottomColor);
		}
	}

	/**
	 * 重置Tabs默认颜色
	 */
	private void setDefaultColor()
	{
		for (int i = 0; i < getChildCount(); i++)
		{
			View view = getChildAt(i);
			if (view instanceof TextView)
			{
				((TextView) view).setTextColor(mTabsItemColorNormal);
//				((TextView) view).setBackgroundColor(mTabsItemColorNormal);
			}
		}
	}

	/**
	 * 设置Tabs点击事件
	 */
	private void setOnItemClickListner()
	{
		int count = getChildCount();
		for (int i = 0; i < count; i++)
		{
			final int j = i;
			View view = getChildAt(i);
			view.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					mViewPager.setCurrentItem(j);
				}
			});
		}
	}

	/**
	 * 指示器形状的枚举类型
	 * 
	 * @author lincoln
	 *
	 */
	private enum Shape
	{
		LINE(0x0), TRIANGLE(0x1), HEART(0x2);

		private int intValue;

		Shape(int intValue)
		{
			this.intValue = intValue;
		}

		static Shape mapIntToValue(final int ShapeInt)
		{
			for (Shape value : Shape.values())
			{
				if (ShapeInt == value.getIntValue())
				{
					return value;
				}
			}

			// If not, return default
			return getDefault();
		}

		static Shape getDefault()
		{
			return LINE;
		}

		int getIntValue()
		{
			return intValue;
		}
	}

	public void setNoScroll()
	{

	}

	/**
	 * ViewPager被占用的接口
	 *
	 */
	public interface OnPageChangeListener
	{

		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

		public void onPageSelected(int position);

		public void onPageScrollStateChanged(int state);
	}

	public void setOnPageChangeListener(OnPageChangeListener listner)
	{
		this.listner = listner;
	}
}