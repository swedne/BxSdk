package com.zorasun.fangchanzhichuang.general.util;

/**
 * 
 * 
 * @author zhouyujing
 * @e-mail 1032668839@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015-1-8 下午9:10:42
 */
public abstract class HttpCallback {
	public abstract void onNetworkError();

	public abstract void onSuccess(String content);

	public abstract void onProgress(int bytesWritten, int totalSize);
}
