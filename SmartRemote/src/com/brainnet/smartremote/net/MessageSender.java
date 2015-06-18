package com.brainnet.smartremote.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.brainnet.smartremote.LogUtil;

public class MessageSender {

	public String mIP = "";
	public int mPort = 2005;

	private Socket mSocket;
	private OutputStream mOutputStream;
	private BufferedReader mReader;

	public boolean connect(String ip, int port) {
		mIP = ip;
		mPort = port;
		reconnect();

		return true;
	}

	public void disconnect() {
		try {
			if (mSocket != null && mSocket.isConnected()) {
				mSocket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mSocket = null;
		mOutputStream = null;
		mReader = null;
	}

	
	
	public boolean writeLine(String text, String encode) {
		
		if(!isConnected()) {
			return false;
		}
		
		try {
			mOutputStream.write(text.getBytes(encode));
			mOutputStream.write("\n".getBytes());
			mOutputStream.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean writeLine(String text) {
		
		if(!isConnected()) {
			return false;
		}
		
		try {
			
			LogUtil.d("MessageSender.writeLine:" + text);
			
			mOutputStream.write(text.getBytes());
//			mOutputStream.write("\n".getBytes());
			mOutputStream.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String readLine() {
		
		if(!isConnected()) {
			return null;
		}
		
		try {
			String line = mReader.readLine();
			LogUtil.d("MessageSender.readLine:" + line);
			return line;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public boolean isConnected() {

		if (mSocket != null && mSocket.isConnected()) {
			return true;
		}

		return false;
	}

	public boolean reconnect() {
		disconnect();

		try {

			mSocket = new Socket(mIP, mPort);
			mSocket.setSoTimeout(60 * 1000);//
			mOutputStream = mSocket.getOutputStream();
			InputStream is = mSocket.getInputStream();
			if(is != null) {
				mReader = new BufferedReader(new InputStreamReader(is));   
			}
			
			if (mReader != null && mSocket.isConnected()) {
				LogUtil.d("SocketThread.reconnect(" + mIP + ":" + mPort
						+ ") success");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogUtil.d("SocketThread.reconnect(" + mIP + ":" + mPort + ") fail");
		disconnect();
		return false;
	}

	public void setServer(String ip, int port) {
		mIP = ip;
		mPort = port;		
	}
}
