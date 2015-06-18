package com.brainnet.smartremote.net;

public abstract class  Packet {
	
	private boolean mIsEnabled = true;

	protected String mResult = null;
	protected String mSend = null;
	
	
	
	public String read() {
		return mSend;
	}
	
	public void write(String data) {
		mResult = data;
	}
	
	public String getResult() {
		return mResult;
	}
	
	public void setEnabled(boolean enabled) {
		mIsEnabled = enabled;
	}
	
	public boolean isEnabled() {
		return mIsEnabled;
	}
	
	
	public abstract void sendError();
}
