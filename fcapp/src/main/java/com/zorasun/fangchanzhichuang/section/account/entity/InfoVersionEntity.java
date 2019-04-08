package com.zorasun.fangchanzhichuang.section.account.entity;

/**
 * 版本更新实体
 * 
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2014年9月27日 下午3:37:31
 */

public class InfoVersionEntity
{
	public String url; // apk下载链接

	public int code;// 2最新版本，0有更新版本 1强制更新(isMustUpdate?)

	@Override
	public String toString()
	{
		return "InfoVersionEntity [url=" + url + "  ]";
	}

}
