package com.zorasun.fangchanzhichuang.general.helper.db;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import android.content.Context;

/**
 * <h1>数据库管理类</h1> <h3>当没有采用OrmLiteBaseActivity等继承的方法时，采用DBManager来获取DBHelper类</h3>
 * 
 * @author zhouyujing
 * @e-mail 1032668839@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015-1-6 下午4:33:46
 */
public class DBManager
{
	private static DBHelper databaseHelper = null;
	static final Object sInstanceSync = new Object();

	/**
	 * 获取DBHelper操作对象
	 * 
	 * @param context
	 * @return
	 */
	public static DBHelper getHelper(Context context)
	{
		synchronized (sInstanceSync)
		{
			if (databaseHelper == null)
			{
				databaseHelper = OpenHelperManager.getHelper(context, DBHelper.class);
			}
		}
		return databaseHelper;
	}

	/**
	 * 释放DBHelper操作对象
	 */
	public static void release()
	{
		if (databaseHelper != null)
		{
			OpenHelperManager.releaseHelper();
		}
	}

}
