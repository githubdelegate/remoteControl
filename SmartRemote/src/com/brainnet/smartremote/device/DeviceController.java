package com.brainnet.smartremote.device;

import com.brainnet.smartremote.data.LoginCode;
import com.brainnet.smartremote.device.packet.RequestPacket;
import com.brainnet.smartremote.net.PacketSenderThread;

public class DeviceController {


	private LoginCode mLoginCode;
	private static DeviceController mInstance;
	private boolean mIsConnected = false;
	private PacketSenderThread mPacketSenderThread = new PacketSenderThread();
	private DeviceList mDeviceList = new DeviceList();
	
	public static DeviceController getInstance() {

		if (mInstance == null) {
			mInstance = new DeviceController();
		}

		return mInstance;
	}
	
	
	public DeviceController() {
		mPacketSenderThread.start();
	}
	
	public void setLoginCode(LoginCode code) {
		mLoginCode = code;
	}
	
	public boolean getDevices(CallBackListenerHandle handle) {
		if(false == mDeviceList.getDevices(this, handle)) {
			return false;
		}
		
		return true;
	}
	
	public boolean loadDevices() {
		
		return false;
	}
	
	public boolean connect(LoginCode code, CallBackListenerHandle handle) {
		
		disconnect();
		setLoginCode(code);
		
		mPacketSenderThread.start(code.ip, code.port);

		mIsConnected = getDevices(handle);
		return true;
	}
	
	public boolean connect() {
		if(false == mIsConnected) {
			mIsConnected = true;
		}
		return true;
	}
	
	public void disconnect() {
		
	}

	public boolean isConnected() {

		return mIsConnected;
	}

	public void reconnect(CallBackListenerHandle handle) {
		connect(mLoginCode,handle);		
	}

	
	public boolean post(RequestPacket pkg) {
		
		pkg.setPasswrod( mLoginCode.pwd);
		mPacketSenderThread.post(pkg);
		return true;
	}
	
	public DeviceBase findDevice(String name) {
		
		return mDeviceList.findDevice(name);
	}
}
