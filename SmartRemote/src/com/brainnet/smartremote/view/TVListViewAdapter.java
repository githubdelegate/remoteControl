package com.brainnet.smartremote.view;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TVListViewAdapter extends BaseAdapter {

	ArrayList<TVProgramme> mItems = new ArrayList<TVProgramme>();
	private Context mContext;

	public TVListViewAdapter(Context context) {
		mContext = context;
	}


	public void addData(TVProgramme data) {
		mItems.add(data);
	}
	
	@Override
	public int getCount() {
		return mItems.size();
	}

	
	@Override
	public Object getItem(int arg0) {

		return mItems.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {

			convertView = new TVListItemView(mContext);
		}

		TVListItemView holder = (TVListItemView) convertView;
		holder.setData(mItems.get(position));

		return convertView;
	}

}
