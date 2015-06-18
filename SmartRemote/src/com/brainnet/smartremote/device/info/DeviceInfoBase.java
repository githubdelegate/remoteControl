package com.brainnet.smartremote.device.info;


public class DeviceInfoBase {

	
	private String name;
	private String id;
	private String type;
	private String password;
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	
	public DeviceInfoBase(String name, String id,String pwd, String type) {
		

		this.name = name;
		this.id = id;
		this.password = pwd;
		this.type = type;
	}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "name=" + name + ",id="+id +",pwd="+password;
	}
	
}
