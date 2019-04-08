package com.zorasun.fangchanzhichuang.general.widget.timer;

import java.util.ArrayList;
import java.util.List;

public class TimerListnerImpl
{
	// private static final String TAG = "TimerListnerImpl";

	private static TimerListnerImpl mInstance = null;

	private List<TimerListner> mClockListnerList = new ArrayList<TimerListner>();

	public static TimerListnerImpl getInstance()
	{
		if (mInstance == null)
		{
			mInstance = new TimerListnerImpl();
		}
		return mInstance;
	}

	public void addObservers(TimerListner aMainClockListner)
	{
		if (!mClockListnerList.contains(aMainClockListner))
		{
			mClockListnerList.add(aMainClockListner);
		}
	}

	public void removeObserver(TimerListner aMainClockListner)
	{
		if (mClockListnerList.contains(aMainClockListner))
		{
			mClockListnerList.remove(aMainClockListner);
		}
	}

	public void onClockNotice()
	{
		for (int i = 0; i < mClockListnerList.size(); i++)
		{
			mClockListnerList.get(i).onClockListner();
		}
	}
}
