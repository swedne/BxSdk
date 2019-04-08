package com.zorasun.fangchanzhichuang.general.widget.timer;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class TimerService extends Service
{
	// 定时器
	private Timer theTimer;
	// private static final String TAG = "TimerService";
	private IBinder binder = new TimerService.LocalBinder();

	@Override
	public IBinder onBind(Intent intent)
	{

		return binder;
	}

	MediaPlayer mediaPlayer = null;

	@Override
	public void onCreate()
	{
		super.onCreate();
		startTime();
	}

	void startTime()
	{
		if (theTimer == null)
		{
			theTimer = new Timer();
			theTimer.schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					TimerListnerImpl.getInstance().onClockNotice();
				}
			}, 0, 1000);
		}
	}

	public void stopTimer()
	{
		if (theTimer != null)
		{
			theTimer.cancel();
			theTimer = null;
		}
	}

	@Override
	public void onStart(Intent intent, int startId)
	{
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		return START_STICKY;
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		stopTimer();
	}

	// 定义内容类继承Binder
	public class LocalBinder extends Binder
	{
		// 返回本地服务
		public TimerService getService()
		{
			return TimerService.this;
		}
	}
}
