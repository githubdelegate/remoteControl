package com.brainnet.smartremote;



import com.brainnet.smartremote.data.UserData;
import com.brainnet.smartremote.sound.Sounds;

import android.app.Application;
import android.content.res.Configuration;

public class ZkjApplication extends Application {

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Sounds.getInstance().init(this);
		UserData.getInstance().init(this);
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}

}
