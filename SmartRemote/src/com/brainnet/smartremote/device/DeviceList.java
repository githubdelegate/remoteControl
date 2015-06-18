package com.brainnet.smartremote.device;

import java.util.ArrayList;

import com.brainnet.smartremote.LogUtil;
import com.brainnet.smartremote.device.packet.RequestPacket;
import com.brainnet.smartremote.device.packet.RequestPacketListener;
import com.brainnet.smartremote.device.protocol.DeviceEnumProtocol;
import com.brainnet.smartremote.device.protocol.DeviceEnumProtocol.EnumData;

public class DeviceList extends ArrayList<DeviceBase> {

	DeviceEnumProtocol mProtocol = new DeviceEnumProtocol();
	
	private CallBackListenerHandle mFindDeviceCallBack;
	private DeviceController mController;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4198139429713066013L;
	
	RequestPacketListener mRequestPacketListener = new RequestPacketListener() {
		
		@Override
		public void onSendError(RequestPacket sender) {
			if(mFindDeviceCallBack != null) {
				mFindDeviceCallBack.onEnd(sender, -1);
			}
			
		}
		
		@Override
		public void onResult(RequestPacket sender) {
			
			int index = addDeviceByPotocol(sender.getResult());
			if(index == -1) {
				mFindDeviceCallBack.onEnd(sender, CallBackListenerHandle.SUCCESS);
			}
			else {
				getDevice(mController, ++index);
			}			
		}
	};
	

	private void getDevice(DeviceController controller,int index) {

		RequestPacket requestPacket = new RequestPacket(String.format("RDAT-%s-%02d:%02d", RequestPacket.GPWD,index,index),
				mRequestPacketListener);
		controller.post(requestPacket);
	}
	
	
	public boolean getDevices(DeviceController controller, CallBackListenerHandle callback) {
		this.clear();
		mController = controller; 
		mFindDeviceCallBack = callback;
		getDevice(mController,0);
		
//		addDeviceByPotocol("RDAT-00&0123456789ABCDEF0123456789ABCDEF12345678-01-002.003.003.053-1234-70-00800500");		
//		addDeviceByPotocol("RDAT-01&0123456789ABCDEF0123456789ABCDEF12345678-01-002.018.000.001-1234-70-80810100");
//		addDeviceByPotocol("RDAT-02&0123456789ABCDEF0123456789ABCDEF12345678-01-002.018.003.019-1234-70-80810100");
		
		return true;
	}


	public int addDeviceByPotocol(String rdata) {
		
		mProtocol.setText(rdata);
		
		if(!mProtocol.isValid()) {
			return -1;
		}
		
		String type = mProtocol.getValue( DeviceEnumProtocol.VALUE_TYPE);
		if(type == null) {
			return -1;
		}
		
		DeviceBase device = null;
		
		EnumData data = mProtocol.getData();
		String id = mProtocol.getValue( DeviceEnumProtocol.VALUE_ID);
		String pwd = mProtocol.getValue( DeviceEnumProtocol.VALUE_PWD);
		
		
		if(DeviceEnumProtocol.VALUE_TYPE_SWITCH.equals(type)) {
			device = new DeviceSwitch(
					data.name, 
					id,
					pwd,
					type,
					mController);
		}
		else if(DeviceEnumProtocol.VALUE_TYPE_C.equals(type)) {
			device = new DeviceTemperature(
					data.name, 
					id,
					pwd,
					type,
					mController);
		}
		else if(DeviceEnumProtocol.VALUE_TYPE_IRDA.equals(type)) {
			device = new DeviceIRDA(
					data.name, 
					id,
					pwd,
					type,
					mController);
		}
		
		if(device != null) {
			
			LogUtil.d("DeviceList.add" + device.toString());
			this.add(device);
			return data.index;
		}
		
		return -1;
	}


	public DeviceBase findDevice(String name) {
		
		for(DeviceBase device : this ) {
			if(device.getName().equals(name)) {
				return device;
			}
		}
		
		return null;
	}
}
