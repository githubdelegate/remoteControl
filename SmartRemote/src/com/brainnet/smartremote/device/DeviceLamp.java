package com.brainnet.smartremote.device;

public class DeviceLamp extends DeviceBase {

	public DeviceLamp(String name, String id, String pwd, String type,DeviceController controller) {
		super( name,  id, pwd, type,controller);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void on(CallBackListenerHandle handle) {
		
	}
	
	public void off(CallBackListenerHandle handle) {
		
	}
	
	public void reversal(CallBackListenerHandle handle) {
	}
}
