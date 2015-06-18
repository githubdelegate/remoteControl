package com.brainnet.smartremote.net;

import java.util.concurrent.TimeUnit;

import com.brainnet.smartremote.device.packet.RequestPacket;

public class PacketSenderThread extends Thread {

	long mSendBeginTime = 0;
	
	private PacketQueue mPacketQueue = new PacketQueue();
	private MessageSender mMessageSender = new MessageSender();
	private boolean isStoped = false;
	private boolean isStart = false;
		
	public boolean start(String ip, int port) {
		mMessageSender.setServer(ip,port);
		isStart = true;
		return true;
	}

	public void disconnect() {

	}



	public boolean isConnected() {

		return mMessageSender.isConnected();
	}



	@Override
	public void run() {	
		super.run();
		Packet packet = null;
		while(!isStoped) {
			 
			try {
				
				if(isStart == false && !isStoped) {
					Thread.sleep(100);
					continue;
				}
				
				
				if(packet == null) {
					packet = mPacketQueue.poll(100, TimeUnit.MILLISECONDS);
				}
				if( packet != null) {
					
					if(!mMessageSender.isConnected()) {
						mMessageSender.reconnect();
					}
					
					
					if(!packet.isEnabled()) {
						packet = null;
						continue;
					}
					
					mSendBeginTime = System.currentTimeMillis();
					if( mMessageSender.writeLine(packet.read())) {
						packet.write(mMessageSender.readLine());
						packet = null;
					}
					else {
						packet.sendError();
						mMessageSender.disconnect();
						Thread.sleep(100);
					}
					mSendBeginTime = 0;
				}				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public boolean post(RequestPacket pkg) {
		
		
		
		if(mSendBeginTime != 0) {
			long l = System.currentTimeMillis() - mSendBeginTime;
			mSendBeginTime = l;
			if(l > 1000) {
				mMessageSender.disconnect();
			}			
		}
		
		
		mPacketQueue.add(pkg);
		return true;		
	}
}
