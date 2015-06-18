package com.brainnet.smartremote.device;

public class CallBackListenerHandle {
	
	public static final int SUCCESS = 0;
	
	CallBackListener mListener = null;
	
	public CallBackListenerHandle(CallBackListener listener) {
		mListener = listener;
	}
	
	public void setListener(CallBackListener listener) {
		mListener = listener;
	}
	
	public void cancel() {
		mListener = null;
	}
	
	public boolean isCancel() {
		return mListener == null;
	}
	
	
	public void onEnd(Object sender,int code) {
		CallBackListener listener = mListener;
		if(listener != null) {
			listener.onEnd(sender,code);
		}
	}
}
