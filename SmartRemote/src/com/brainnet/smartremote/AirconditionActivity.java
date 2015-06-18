package com.brainnet.smartremote;

import android.os.Bundle;

public class AirconditionActivity extends ControlActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aircondition);
		initTitleBar();
		
		this.setTitle("Пе Еї");
	}

}
