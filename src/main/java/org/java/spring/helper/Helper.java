package org.java.spring.helper;

public abstract class Helper {
	public static float getRoundedNum(float num , int places) {
		float scale = (float) Math.pow(10, places);
		return Math.round(scale * num)/scale;
	}
}
