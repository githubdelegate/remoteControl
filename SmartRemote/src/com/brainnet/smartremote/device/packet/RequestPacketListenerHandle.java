package com.brainnet.smartremote.device.packet;

public class RequestPacketListenerHandle {

	RequestPacketListener mPacketListener;

	public void setListener(RequestPacketListener listener) {
		mPacketListener = listener;
	}

	public void cancel() {
		mPacketListener = null;
	}

	public void onResult(RequestPacket sender) {

		RequestPacketListener listener = mPacketListener;
		if (listener != null) {
			listener.onResult(sender);
		}
	}

	public void onSendError(RequestPacket sender) {

		RequestPacketListener listener = mPacketListener;
		if (listener != null) {
			listener.onSendError(sender);
		}
	}
}
