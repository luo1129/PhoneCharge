package com.phone.bill;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.typ.gk.net.GKHttpWrapper;
import com.typ.gk.net.RestCallback;
import com.typ.gk.net.loopj.RequestParams;

import Util.KeyValue;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SettingOpenBusinessActivity extends MainActivity {

	int operator = 0;

	ArrayList<String[]> operators = new ArrayList<String[]>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.open_business_setting);
		hideSetting();
		loadData();
	}

	private void loadData() {

		RequestParams params = new RequestParams();

		params.put("mercAccount", KeyValue.mAccount);

		GKHttpWrapper.get("information_query.action", params, new RestCallback() {

			@Override
			public void onSuccess(JSONObject object) {
				// TODO Auto-generated method stub
				parseData(object);

			}

			@Override
			public void onFailure(Throwable throwable, JSONObject object) {
				// TODO Auto-generated method stub

			}

		});

	}
	
	ArrayList<String[]> operatorList;
	

	private void parseData(JSONObject object) {

		JSONArray list = object.optJSONArray("list");

		operatorList = new ArrayList<String[]>();

		if(list == null){
			return;
		}
		
		for (int i = 0; i < list.length(); i++) {
			JSONObject one = list.optJSONObject(i);

			String[] date = new String[4];

			date[0] = one.optString("crtAccount");
			date[1] = one.optString("vendorName");
			date[2] = one.optString("openType");
			date[3] = one.optString("mercBalance");
			operatorList.add(date);
		}

		TextView tv = (TextView) this.findViewById(R.id.value1);
		tv.append(operatorList.get(0)[1]);
		tv = (TextView) this.findViewById(R.id.value2);
		tv.append(operatorList.get(0)[0]);
		tv = (TextView) this.findViewById(R.id.value3);
		tv.append(operatorList.get(0)[3]);
		tv = (TextView) this.findViewById(R.id.textView4);
		if (operatorList.get(0)[2].equals("0")) {
			tv.setText(R.string.open_business_setting_title_5);
			this.findViewById(R.id.open_business_open_1).setVisibility(View.INVISIBLE);
		} else {
			tv.setText(R.string.open_business_setting_title_4);
		}

		if(operatorList.size() < 2){
			return;
		}
		
		tv = (TextView) this.findViewById(R.id.value4);
		tv.append(operatorList.get(1)[1]);
		tv = (TextView) this.findViewById(R.id.value5);
		tv.append(operatorList.get(1)[0]);
		tv = (TextView) this.findViewById(R.id.value6);
		tv.append(operatorList.get(1)[3]);
		tv = (TextView) this.findViewById(R.id.value7);
		if (operatorList.get(1)[2].equals("0")) {
			tv.setText(R.string.open_business_setting_title_5);
			this.findViewById(R.id.open_business_open_2).setVisibility(View.INVISIBLE);
		} else {
			tv.setText(R.string.open_business_setting_title_4);
		}

	}

	public void open(View view) {
		Intent intent2 = new Intent(this, SettingOpenBusinessOpenActivity.class);

		switch (view.getId()) {
		case R.id.open_business_open_1:
			intent2.putExtra("vendorName", operatorList.get(0)[1]);
			intent2.putExtra("crtAccount", operatorList.get(0)[0]);
			break;
		case R.id.open_business_open_2:
			if(operatorList.size() < 2){
				return;
			}
			intent2.putExtra("vendorName", operatorList.get(1)[1]);
			intent2.putExtra("crtAccount", operatorList.get(1)[0]);
			break;
		}

		this.startActivityForResult(intent2, 100);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
