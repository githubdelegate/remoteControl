package com.brainnet.smartremote.device;

import com.brainnet.smartremote.device.info.DeviceInfoBase;

public abstract class DeviceBase extends DeviceInfoBase{
	
	protected DeviceController mDeviceController;


	public DeviceBase(String name, String id, String pwd, String type,DeviceController controller) {
		super(name, id, pwd, type);
		mDeviceController = controller;
	}

	public abstract void update();

	
}
