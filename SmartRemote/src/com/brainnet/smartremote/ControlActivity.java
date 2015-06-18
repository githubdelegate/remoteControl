package com.brainnet.smartremote;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class ControlActivity extends Activity {

	TextView mTextViewTitle;
	ImageButton mBtnExit;
	ImageButton mBtnSetting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	}

	protected void initTitleBar() {
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.title_bar_control);

		mTextViewTitle = (TextView) findViewById(R.id.textViewTitle);
		mTextViewTitle.setText(this.getTitle());

		mBtnExit = (ImageButton) findViewById(R.id.buttonExit);
		mBtnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		mBtnSetting = (ImageButton) findViewById(R.id.buttonSetting);
		mBtnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	protected void showButtonExit(boolean show) {
		if (null != mBtnExit) {
			mBtnExit.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		}
	}

	protected void showButtonSetting(boolean show) {
		if (null != mBtnSetting) {
			mBtnSetting.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		}
	}

	protected void setTitle(String text) {

		if (null != mTextViewTitle) { 
			if(text == null) {
				mTextViewTitle.setText("");
				mTextViewTitle.setBackgroundResource(R.drawable.logo);
			}
			else {
				mTextViewTitle.setText(text);
				mTextViewTitle.setBackgroundResource(0);
			}
		}
	}
}
