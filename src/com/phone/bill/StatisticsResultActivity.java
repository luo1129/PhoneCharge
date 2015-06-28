package com.phone.bill;

import Util.KeyValue;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class StatisticsResultActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.balance_result);

		TextView tv = (TextView) this.findViewById(R.id.balance_account);
		tv.append(KeyValue.mAccount);
		tv = (TextView) this.findViewById(R.id.balance_name);
		tv.append(KeyValue.mName);
//		tv = (TextView) this.findViewById(R.id.balance_left);
//		tv.append(this.getIntent().getStringExtra("left"));
		tv = (TextView) this.findViewById(R.id.balance_time_1);
		tv.append(this.getIntent().getStringExtra("time1"));
		tv = (TextView) this.findViewById(R.id.balance_time_2);
		tv.append(this.getIntent().getStringExtra("time2"));
		tv = (TextView) this.findViewById(R.id.balance_value_1);
		tv.setText(this.getIntent().getStringExtra("value1"));
		tv = (TextView) this.findViewById(R.id.balance_value_2);
		tv.setText(this.getIntent().getStringExtra("value2"));
		tv = (TextView) this.findViewById(R.id.balance_value_3);
		tv.setText(this.getIntent().getStringExtra("value3"));
		tv = (TextView) this.findViewById(R.id.balance_value_4);
		tv.setText(this.getIntent().getStringExtra("value4"));
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
