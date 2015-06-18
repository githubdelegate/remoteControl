package com.brainnet.smartremote.device.protocol;

public class ProtocolBase {

	
	public static final int VALUE_COMMAND = 0; 
	private String[] mData;
	
	
	public void setText(String text) {
		
		mData = null;
		
		if(text == null) {
			
			return;
		}
		
		mData = text.split("-");
	}
	
	public String getValue( int index) {
		
		if(mData == null) {
			return null;
		}
		
		if(mData.length > index) {
			return mData[index];
		}
		
		return null;
	}
	

	
}
