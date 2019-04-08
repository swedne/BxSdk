package com.zorasun.fangchanzhichuang.section.account.entity;

/**
 * 活动专区实体类
 * 
 * @author yanjinhui
 * @e-mail
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015-1-27 上午10:27:48
 */
public class ActivityEntity
{
	/**
	 * 活动专区id
	 */
	public long id;

	/**
	 * 活动专区的标题
	 */
	public String activityName;
	/**
	 * 活动专区对应的链接
	 */
	public String activityAppUrl;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityAppUrl() {
		return activityAppUrl;
	}
	public void setActivityAppUrl(String activityAppUrl) {
		this.activityAppUrl = activityAppUrl;
	}
}
