package com.brainnet.smartremote.data;

import com.brainnet.smartremote.LogUtil;

public class LoginCode {

	public String ip = "";
	public int port = 2005;
	public String pwd = "";
	public String id = "";
	
	public boolean initCode(String value ) {
		
		if(value == null) {
			return false;
		}
		
		LogUtil.d("LoginCode.initCode value=" + value);
		
		String[] array = value.split(":");
		if(array == null) {
			return false;
		}
		
		if(array.length != 4) {
			return false;
		}
		// 192.168.1.5:2005:2583:DJS574FD
		ip = array[0];
		
		try {
			port = Integer.parseInt(array[1]);
		} catch (Exception e) {
			LogUtil.e("LoginCode.initCode prot error:" + array[1]);
		}
		
		
		pwd = array[2];
		id = array[3];
		
		
		return true;
		
	}
}
