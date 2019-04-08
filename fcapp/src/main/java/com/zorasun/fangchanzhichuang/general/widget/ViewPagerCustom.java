package com.zorasun.fangchanzhichuang.general.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ViewPagerCustom extends ViewPager
{

	private boolean noScroll;

	public boolean isNoScroll()
	{
		return noScroll;
	}

	public void setNoScroll(boolean noScroll)
	{
		this.noScroll = noScroll;
	}

	public ViewPagerCustom(Context context)
	{
		super(context);
	}

	public ViewPagerCustom(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		performClick();
		if (noScroll)
		{
			return false;
		}
		return super.onTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		if (noScroll)
		{
			return false;
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean performClick()
	{
		return super.performClick();
	}
}
