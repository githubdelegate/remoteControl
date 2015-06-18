package com.brainnet.smartremote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.brainnet.smartremote.data.LoginCode;
import com.brainnet.smartremote.device.CallBackListener;
import com.brainnet.smartremote.device.CallBackListenerHandle;
import com.brainnet.smartremote.device.DeviceController;
import com.zxing.activity.CaptureActivity;

public class LoginActivity extends ControlActivity {

	public static final int REQUESTCODE = 100;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initTitleBar();
		setTitle(null);
		
		showButtonExit(false);
		showButtonSetting(false);
		
		Button button = (Button) findViewById(R.id.buttonLogin);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent openCameraIntent = new Intent(LoginActivity.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);				
			}
		});	
		
		login("192.168.1.5:2005:1234:DJS574FD");
		loginFinish();
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	
		if (resultCode == RESULT_OK) {
			Bundle tempBundle = data.getExtras();
			String scanResult = tempBundle.getString("result");
			login(scanResult);
		}
	}
	
	
	
	public void login(String scanCode) {
		
		mScanCode = scanCode;
				
		LoginCode code = new LoginCode();
		code.initCode(scanCode);
		DeviceController.getInstance().connect(code,mConnectHandle);
			
	}
	
	public void loginFinish() {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("result", mScanCode);
		setResult(RESULT_OK, resultIntent);			
		finishActivity(REQUESTCODE);
		finish();
	}
	
	String mScanCode;
	int mCode = 0;
	
	CallBackListenerHandle mConnectHandle = new CallBackListenerHandle( new CallBackListener() {
		
		@Override
		public void onEnd(Object sender, int code) {
			mCode = code;
			mConnectHandle.cancel();
			runOnUiThread( new Runnable(){

				@Override
				public void run() {
					if(mCode == CallBackListenerHandle.SUCCESS) {
						loginFinish();
					}
					else {
						Toast.makeText(LoginActivity.this,"ÍøÂç´íÎóµÇÂ½Ê§°Ü\n" + mScanCode, Toast.LENGTH_LONG).show();
					}					
				}				
			} );			
		}
	});
	
	
	
}
