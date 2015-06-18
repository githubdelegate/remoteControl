package com.brainnet.smartremote.device.packet;

public interface  RequestPacketListener {
	
	public void onResult(RequestPacket sender);
	public void onSendError(RequestPacket sender);
	
}
