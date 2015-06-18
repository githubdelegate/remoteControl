package com.brainnet.smartremote.data;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
	
	SharedPreferences mPreferences;

	private static Preferences mInstance;
	public static Preferences getInstance() {

		if (mInstance == null) {
			mInstance = new Preferences();
		}

		return mInstance;
	}
	
	
	public void init(Context context) {
		if (mPreferences == null) {
			mPreferences = context.getSharedPreferences("user_history",
					Context.MODE_PRIVATE);
		}
	}

	public String getString(String key, String defValue) {
		return mPreferences.getString(key, defValue);
	}

	public float getFloat(String key, float defValue) {
		return mPreferences.getFloat(key, defValue);
	}

	public int getInt(String key, int defValue) {
		return mPreferences.getInt(key, defValue);
	}

	public boolean getBoolean(String key, boolean defValue) {
		
		return mPreferences.getBoolean(key, defValue);
	}

	public void put(String key, String value) {
		mPreferences.edit().putString(key, value).commit();
	}

	public void put(String key, int value) {
		mPreferences.edit().putInt(key, value).commit();
	}

	public void put(String key, boolean value) {
		mPreferences.edit().putBoolean(key, value).commit();
	}
}
