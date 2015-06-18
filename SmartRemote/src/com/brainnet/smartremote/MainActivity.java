package com.brainnet.smartremote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import com.brainnet.smartremote.data.UserData;
import com.brainnet.smartremote.device.DeviceBase;
import com.brainnet.smartremote.device.DeviceController;
import com.brainnet.smartremote.device.DeviceSwitch;

public class MainActivity extends ControlActivity {

	DeviceController mDeviceController = DeviceController.getInstance();
	
	private UserData mUserData = UserData.getInstance();
	ToggleButton mButtonLamp1;
	ToggleButton mButtonLamp2;
	ToggleButton mButtonLamp3;
	ToggleButton mButtonLamp4;
	
	ToggleButton mButtonTV;
	ToggleButton mButtonAircondition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initTitleBar();
		setTitle(null);
		
		showButtonExit(false);
		showButtonSetting( BuildConfig.DEBUG);
		mBtnSetting.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mUserData.setLogin(false);
				mUserData.setLoginCode("");
			}
		});
		
		if(!mUserData.isLogin()) {
			
			Intent intent = new Intent(this,LoginActivity.class);
			startActivityForResult(intent, LoginActivity.REQUESTCODE);			
		}
		
		initView();
		
		mDeviceController.connect();
	}
	
	
	public void removeToggleButtonText(ToggleButton btn) {
		btn.setTextOff("");
		btn.setTextOn("");	
	}
	
	private void initView() {
		mButtonLamp1 = (ToggleButton)findViewById(R.id.buttonLamp1);
		removeToggleButtonText(mButtonLamp1);
		
		mButtonLamp1.setOnCheckedChangeListener( new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				DeviceBase device = (DeviceBase) mDeviceController.findDevice("00");
				if(device != null) {
					if(device instanceof DeviceSwitch ) {
					
						DeviceSwitch s = (DeviceSwitch) device;
						s.on(" ", null);
					}
				}				
			}
		});
		
		mButtonLamp2 = (ToggleButton)findViewById(R.id.buttonLamp2);
		removeToggleButtonText(mButtonLamp2);
		mButtonLamp2.setOnCheckedChangeListener( new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				DeviceBase device = (DeviceBase) mDeviceController.findDevice("02");
				if(device != null) {
					if(device instanceof DeviceSwitch ) {
					
						DeviceSwitch s = (DeviceSwitch) device;
						s.on(" ", null);
					}
				}	
			}
		});
		
		mButtonLamp3 = (ToggleButton)findViewById(R.id.buttonLamp3);
		removeToggleButtonText(mButtonLamp3);
		mButtonLamp3.setOnCheckedChangeListener( new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				
			}
		});
		
		mButtonLamp4 = (ToggleButton)findViewById(R.id.buttonLamp4);
		removeToggleButtonText(mButtonLamp4);
		mButtonLamp4.setOnCheckedChangeListener( new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				
			}
		});
		
		mButtonTV = (ToggleButton)findViewById(R.id.buttonTV);
		removeToggleButtonText(mButtonLamp1);
		mButtonTV.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(mDeviceController.isConnected()) {
					Intent intent = new Intent(MainActivity.this,TVActivity.class);
					startActivity(intent);
				}				
			}
		});
		
		mButtonAircondition = (ToggleButton)findViewById(R.id.buttonAircondition);
		removeToggleButtonText(mButtonAircondition);
		mButtonAircondition.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(mDeviceController.isConnected()) {
					Intent intent = new Intent(MainActivity.this,AirconditionActivity.class);
					startActivity(intent);
				}				
			}
		});

		
		
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode == RESULT_OK && requestCode == LoginActivity.REQUESTCODE) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			mUserData.setLoginCode(scanResult);
			updateDeviceControllerInfo();
		}
		else {
			finish();
		}
	}


	private void updateDeviceControllerInfo() {
		
		
	}
}
