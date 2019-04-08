package com.zorasun.fangchanzhichuang.general.helper.db.base;

import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author zhouyujing
 * @e-mail 1032668839@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015-1-6 下午2:36:15
 */
public abstract class BaseDBHelper extends OrmLiteSqliteOpenHelper
{

	private final String TAG = "BaseDBHelper";
	public Context mContext;

	public BaseDBHelper (Context context, String databaseName, int databaseVersion)
	{
		super(context, databaseName, null, databaseVersion);
		this.mContext = context;
	}

	/**
	 * 获取数据库表
	 * 
	 * @return
	 */
	public abstract String[] getDBTables();

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1)
	{
		creteTables();
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2, int arg3)
	{
		dropTables();
		creteTables();
	}

	/**
	 * 初始化数据库的所有表
	 */
	private void creteTables()
	{
		try
		{
			String[] tables = getDBTables();
			if (tables != null)
			{
				AppLog.debug(TAG, "---begin creating table---");
				for (int i = 0; i < tables.length; i++)
				{
					Class<?> cls = Class.forName(tables[i]);
					TableUtils.createTable(connectionSource, cls);
					AppLog.debug(TAG, "creat table" + i + ":" + tables[i] + "succeed");
				}
			}
			AppLog.debug(TAG, "---end creating table---");
		}
		catch (ClassNotFoundException e)
		{
			AppLog.redLog(TAG, "table class is not found." + e);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			AppLog.redLog(TAG, "can't create table .\n" + e);
		}

	}

	/**
	 * drop掉所有的表
	 */
	private void dropTables()
	{
		try
		{
			String[] tables = getDBTables();
			if (tables != null)
			{
				AppLog.debug(TAG, "----begin drop table----.");
				for (int i = 0; i < tables.length; i++)
				{
					Class<?> cls = Class.forName(tables[i]);
					TableUtils.dropTable(connectionSource, cls, true);
					AppLog.debug(TAG, "drop table " + i + " : " + tables[i] + " successfully!");
				}
				AppLog.debug(TAG, "----end drop table----");
			}
		}
		catch (ClassNotFoundException e)
		{
			AppLog.redLog(TAG, "table class is not found." + e);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			AppLog.redLog(TAG, "can't drop table .\n" + e);
		}
	}

}
