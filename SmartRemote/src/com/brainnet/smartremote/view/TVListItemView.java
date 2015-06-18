package com.brainnet.smartremote.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brainnet.smartremote.R;


public class TVListItemView extends LinearLayout {
	
	public TVListItemView(Context context) {
		super(context);
		init(context);
	}


	private TextView mTextView;
	
	private TVProgramme mData;
	
	public void setData(TVProgramme data ){
		
		mData = data;
		mTextView.setText(data.name);		
	}
	
	public TVProgramme getData() {
		return mData;
	}
	
	
	public void init(Context context) {

		// 导入布局
		LayoutInflater.from(context).inflate(R.layout.view_tv_list_item, this, true);
		mTextView 		= (TextView) findViewById(R.id.textViewTVProgramme);
	}
}
