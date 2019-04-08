package com.zorasun.fangchanzhichuang.general.widget.timer;

/**
 * 
 * 
 * @author 郑辉煌
 * @e-mail huihuang.nu@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2014年11月29日 上午1:31:00
 * 
 */

/**
 * 倒计时监听接口
 * 
 * @author Administrator
 */

public interface TimerEventListner
{
	public void onTimeNotice(int position, long markTime);

	public void onTimeOver(int position);
}
