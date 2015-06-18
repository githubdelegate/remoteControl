package com.brainnet.smartremote.device;

import com.brainnet.smartremote.device.packet.RequestPacket;
import com.brainnet.smartremote.device.packet.RequestPacketListener;

public class DeviceSwitch extends DeviceBase {

	
	

	
	public DeviceSwitch(String name, String id, String pwd, String type,DeviceController controller) {
		super(name, id, pwd, type,controller);
		// TODO Auto-generated constructor stub
	}

	CallBackListenerHandle mRequesetHandle;
	
	RequestPacketListener mRequestPacketListener = new RequestPacketListener() {
		
		@Override
		public void onSendError(RequestPacket sender) {
			if(mRequesetHandle != null) {
				mRequesetHandle.onEnd(DeviceSwitch.this, -1);
			}			
		}
		
		@Override
		public void onResult(RequestPacket sender) {
			
			if(mRequesetHandle != null) {
				mRequesetHandle.onEnd(DeviceSwitch.this, CallBackListenerHandle.SUCCESS);
			}		
		}
	};
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void post( String cmd ) {
		
		// SPTP-1234-002.018.000.001-1234-3FFF
		RequestPacket requestPacket = new RequestPacket(String.format("SPTP-%s-%s-%s-%s",
				RequestPacket.GPWD,
				getId(), getPassword(), cmd ),
				mRequestPacketListener);
		
		mDeviceController.post(requestPacket);	
	}
	 
	public void on( String name, CallBackListenerHandle handle) {
		
		mRequesetHandle = handle;
		post("0000");
	}
	
	public void off(String name, CallBackListenerHandle handle) {
		mRequesetHandle = handle;
		post("0000");
	}
	
	public void reversal(String name, CallBackListenerHandle handle) {
		mRequesetHandle = handle;
		post("0000");
	}

}
