package com.brainnet.smartremote.data;



import android.content.Context;



public class UserData {
	
	private static UserData mInstance;
	public Preferences config = Preferences.getInstance();
	
	public static UserData getInstance() {

		if (mInstance == null) {
			mInstance = new UserData();
		}

		return mInstance;
	}
	
	public void init(Context context) {
		config.init(context);		
	}
	
	public boolean isLogin() {
		return config.getBoolean("is_login", false);
	}
	
	public void setLogin(boolean value) {
		config.put("is_login", value);
	}
	
	public void setLoginCode(String value) {
		config.put("login_code", value);
	}
	
	public String getLoginCode() {
		return config.getString("login_code", "");
	}
	
}
