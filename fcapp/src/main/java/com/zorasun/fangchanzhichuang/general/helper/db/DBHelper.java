package com.zorasun.fangchanzhichuang.general.helper.db;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.helper.db.base.BaseDBHelper;

import android.content.Context;

/**
 * 数据库辅助类
 * 
 * @author zhouyujing
 * @e-mail 1032668839@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015-1-6 下午2:33:22
 */
public class DBHelper extends BaseDBHelper
{

	public DBHelper (Context context)
	{
		super(context, context.getString(R.string.db_name), Integer.valueOf(context.getString(R.string.db_version)));
	}

	@Override
	public String[] getDBTables()
	{
		String[] tables = mContext.getResources().getStringArray(R.array.db_tables);
		return tables;
	}

}
