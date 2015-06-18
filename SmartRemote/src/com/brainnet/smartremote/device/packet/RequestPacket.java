package com.brainnet.smartremote.device.packet;

import com.brainnet.smartremote.net.Packet;

public class RequestPacket extends Packet {

	public static final String GPWD = "-&GPWD-";
	
	RequestPacketListenerHandle mListenerHandle = new RequestPacketListenerHandle();
	
	public RequestPacket(String send, RequestPacketListener listener) {
		mSend = send;
		mListenerHandle.setListener(listener);
	}

	
	public void write(String data) {
		mResult = data;
		mListenerHandle.onResult(this);		
	}
	
	public void setPasswrod( String pwd ) {

		if( mSend != null) {
			mSend = mSend.replace(GPWD, pwd);
		}		
	}
	
	public void cancelSend() {
		mListenerHandle.setListener(null);
		setEnabled(false);
	}

	@Override
	public void sendError() {
		
		mListenerHandle.onSendError(this);		
	}

	
}
