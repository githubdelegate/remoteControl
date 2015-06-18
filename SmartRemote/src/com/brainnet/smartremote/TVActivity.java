package com.brainnet.smartremote;

import com.brainnet.smartremote.view.TVListItemView;
import com.brainnet.smartremote.view.TVListViewAdapter;
import com.brainnet.smartremote.view.TVProgramme;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TVActivity extends ControlActivity {

	private ListView mListViewProgramme;
	TVListViewAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_tv);
		initTitleBar();
		this.setTitle("�� ��");

		initView();
	}

	private void initView() {

		mListViewProgramme = (ListView) findViewById(R.id.listViewProgramme);
		
		TVListViewAdapter adapter = new TVListViewAdapter(this);
		adapter.addData( new TVProgramme("�l��1", 1));
		adapter.addData( new TVProgramme("�l��2", 1));
		adapter.addData( new TVProgramme("�l��3", 1));
		adapter.addData( new TVProgramme("�l��4", 1));
		adapter.addData( new TVProgramme("�l��5", 1));
		adapter.addData( new TVProgramme("�l��6", 1));
		adapter.addData( new TVProgramme("�l��7", 1));
		
		mListViewProgramme.setAdapter(adapter);
		
		mListViewProgramme.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TVListItemView view = (TVListItemView) arg1;
				TVProgramme programme = view.getData();
				Toast.makeText(getBaseContext(), programme.name, Toast.LENGTH_LONG).show();
			}
		});
		
	}


}
