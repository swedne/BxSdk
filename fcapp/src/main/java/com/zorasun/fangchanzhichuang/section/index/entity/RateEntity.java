package com.zorasun.fangchanzhichuang.section.index.entity;

public class RateEntity {
	private long changeDate; // 利率时间
	private double oneYear;// 一年
	private double oneToThree;// 一到三年
	private double threeToFive;// 三到五年
	private double fiveYear;// 五年以上
	private double GGJFiveYearDown;// 五年以下（住房公积金）
	private double GGJFiveYearUp;// 五年以上（住房公积金）

	public long getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(long changeDate) {
		this.changeDate = changeDate;
	}

	public double getOneYear() {
		return oneYear;
	}

	public void setOneYear(double oneYear) {
		this.oneYear = oneYear;
	}

	public double getOneToThree() {
		return oneToThree;
	}

	public void setOneToThree(double oneToThree) {
		this.oneToThree = oneToThree;
	}

	public double getThreeToFive() {
		return threeToFive;
	}

	public void setThreeToFive(double threeToFive) {
		this.threeToFive = threeToFive;
	}

	public double getFiveYear() {
		return fiveYear;
	}

	public void setFiveYear(double fiveYear) {
		this.fiveYear = fiveYear;
	}

	public double getGGJFiveYearDown() {
		return GGJFiveYearDown;
	}

	public void setGGJFiveYearDown(double gGJFiveYearDown) {
		GGJFiveYearDown = gGJFiveYearDown;
	}

	public double getGGJFiveYearUp() {
		return GGJFiveYearUp;
	}

	public void setGGJFiveYearUp(double gGJFiveYearUp) {
		GGJFiveYearUp = gGJFiveYearUp;
	}

	@Override
	public String toString() {
		return "RateEntity [changeDate=" + changeDate + ", oneYear=" + oneYear + ", oneToThree=" + oneToThree
				+ ", threeToFive=" + threeToFive + ", fiveYear=" + fiveYear + ", GGJFiveYearDown=" + GGJFiveYearDown
				+ ", GGJFiveYearUp=" + GGJFiveYearUp + "]";
	}

}
