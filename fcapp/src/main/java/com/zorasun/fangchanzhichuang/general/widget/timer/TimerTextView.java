package com.zorasun.fangchanzhichuang.general.widget.timer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class TimerTextView extends TextView
{
	public final static int LEFT = 2;
	public final static int RIGHT = 1;
	public final static int NONE = 0;
	public final static int TIME_TYPE = 1;
	TimerEventListner mClockListner;
	long between,hour,day,minute,second;
	private int position;
	private int location;
	private String value;;
	private String timeOver;
	int state = 0;// 判断状态
	
	TimerListner mTimerListner = new TimerListner()
	{

		@Override
		public void onClockListner()
		{
			aHandler.sendEmptyMessage(0);
		}

	};

	@SuppressLint("HandlerLeak")
	Handler aHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			updateText();
		}
	};

	public TimerTextView (Context context)
	{
		super(context);
	}

	public TimerTextView (Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	//
	private void updateText()
	{
		between -= 1;
		if (between <= 0)
		{
			TimerListnerImpl.getInstance().removeObserver(mTimerListner);
		}
		if (mClockListner != null && between <= 0)
		{
			mClockListner.onTimeOver(position);
		}
		else if (mClockListner != null)
		{
			mClockListner.onTimeNotice(position, between * 0x3E8);
		}
		if (between <= 0 && !TextUtils.isEmpty(timeOver))
		{
			setText(timeOver);
		}
		else
		{
			if(state != 0)
			{
				setTimeView(between);
			}
			else
			{
				setView(between);
			}
		}
	}

	/**
	 * <p>
	 * Title: setTime<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @param time
	 *            时间戳
	 * @param value
	 *            描述
	 * @param location
	 *            描述的位置
	 * @param position
	 *            在列表的位置
	 */
	public void setTime(long time, String value, int location, int position)
	{
		TimerListnerImpl.getInstance().addObservers(mTimerListner);
		between = time / 0x3E8;
		this.location = location;
		this.position = position;
		this.value = value;
		setView(between);
	}

	/**
	 * 倒计时
	 * @param time
	 * @param state// 状态判断
	 */
	public void set2Time(long time, int state)
	{
		this.state = state;
		TimerListnerImpl.getInstance().addObservers(mTimerListner);
		between = time / 0x3E8;
		setTimeView(between);
	}
	
	// 更新textview的内容
	private void setTimeView(long time)
	{
		day = between / (0x18 * 0xE10);
		hour = between % (0x18 * 0xE10) / 0xE10;
		minute = between % 0xE10 / 0x3C;
		second = between % 0x3C;
		String temp = "";
		if (day == 0)
		{

			if (hour == 0)
			{
				if (minute == 0)
				{
					temp = "00:00:" + second;
				}
				else
				{
					temp = "00:" + minute + ":" + second;
				}
			}
			else
			{
				temp = hour + ":" + minute + ":" + second;
			}
		}
		else
		{
			temp = day + "天" + hour + ":" + minute + ":" + second;
		}
		setText(temp);
	}
	
	// 更新textview的内容
	private void setView(long time)
	{
		day = between / (0x18 * 0xE10);
		hour = between % (0x18 * 0xE10) / 0xE10;
		minute = between % 0xE10 / 0x3C;
		second = between % 0x3C;
		String temp = "";
		if (day == 0)
		{

			if (hour == 0)
			{
				if (minute == 0)
				{
					temp = second + "秒";
				}
				else
				{
					temp = minute + "分" + second + "秒";
				}
			}
			else
			{
				temp = hour + "时" + minute + "分" + second + "秒";
			}
		}
		else
		{
			temp = day + "天" + hour + "时" + minute + "分" + second + "秒";
		}
		if (location == LEFT)
		{
			setText(value + temp);
		}
		else if (location == RIGHT)
		{
			setText(temp + value);
		}
		else
		{
			setText(temp);
		}
	}

	/**
	 * 添加倒计时监听
	 * <p>
	 * Title: addObserve<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @param clockListener
	 * @param position2
	 */
	public void addObserve(TimerEventListner clockListener)
	{
		this.mClockListner = clockListener;
	}
	
	/**
	 * 清掉倒计时监听
	 * <p>
	 * Title: addObserve<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @param clockListener
	 * @param position2
	 */
	public void removeObserve(TimerEventListner clockListener)
	{
		this.mClockListner = clockListener;
		TimerListnerImpl.getInstance().removeObserver(mTimerListner);
	}

	/**
	 * 设置倒计时到0时显示的文字
	 * 
	 * @param timeOver
	 */
	public void setTimeOver(String timeOver)
	{
		this.timeOver = timeOver;
	}
}
