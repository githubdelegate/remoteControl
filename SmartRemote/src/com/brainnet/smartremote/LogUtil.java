package com.brainnet.smartremote;

import android.util.Log;

public class LogUtil {

	public static boolean DEBUG = BuildConfig.DEBUG;
	public static final String TAG = "SR";
	public static int erron = 0;
	
	public static void v(String tag, String msg){
		if(DEBUG){
			Log.v(tag, msg);
		}
	}
	
	public static void v(Class<?> class_, String msg){
		if(DEBUG){
			v(class_.getCanonicalName() + ":" + msg);
		}
	}
	
	public static void v(String msg){
		if(DEBUG){
			Log.v(TAG, msg);
		}
	}
	
	public static void i(Class<?> class_, String msg){
		if(DEBUG){
			i(class_.getCanonicalName() + ":" + msg);
		}
	}
	
	public static void i(String tag, String msg){
		if(DEBUG){
			Log.i(tag, msg);
		}
	}
	
	public static void i(String msg){
		if(DEBUG){
			Log.i(TAG, msg);
		}
	}
	
	public static void d(Class<?> class_, String msg){
		if(DEBUG){
			d(class_.getCanonicalName() + ":" + msg);
		}
	}
	
	
	public static void d(String tag, String msg){
		if(DEBUG){
			Log.d(tag, msg);
		}
	}
	
	public static void d(String msg){
		if(DEBUG){
			Log.d(TAG, msg);
		}
	}
	
	public static void w(Class<?> class_, String msg){
		if(DEBUG){
			w(class_.getCanonicalName() + ":" + msg);
		}
	}
	
	
	public static void w(String tag, String msg){
		if(DEBUG){
			Log.w(tag, msg);
		}
	}
	
	public static void w(String msg){
		if(DEBUG){
			Log.w(TAG, msg);
		}
	}
	
	public static void e(String tag, String msg){
		if(DEBUG){
			Log.e(tag, msg);
		}
	}
	
	public static void e(String msg){
		if(DEBUG){
			Log.e(TAG, msg);
		}
	}
	
}
