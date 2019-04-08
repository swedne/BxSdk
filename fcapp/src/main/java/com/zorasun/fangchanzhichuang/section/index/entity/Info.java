package com.zorasun.fangchanzhichuang.section.index.entity;

import java.io.Serializable;

public class Info implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double latitude;
	private double longitude;
	private int imgId;
	private String name;
	private String distance;
	private int zan;
	private int id;
	private int num;

	// public static List<Info> infos = new ArrayList<Info>();

	// static {
	// infos.add(new Info(24.45, 118.08, R.drawable.ic_launcher, "思明区"));
	// infos.add(new Info(24.52, 118.08, R.drawable.ic_launcher, "湖里区"));
	// infos.add(new Info(24.57, 118.10, R.drawable.ic_launcher, "集美区"));
	// infos.add(new Info(24.73, 118.15, R.drawable.ic_launcher, "同安区"));
	// infos.add(new Info(24.47, 117.98, R.drawable.ic_launcher, "海沧区"));
	// infos.add(new Info(24.62, 118.23, R.drawable.ic_launcher, "翔安区"));
	// }

	public Info(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Info(double latitude, double longitude, String name, int num, int id) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.num = num;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

}
