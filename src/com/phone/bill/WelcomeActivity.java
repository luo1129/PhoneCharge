package com.phone.bill;

import Util.KeyValue;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

public class WelcomeActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.welcome);
	}

	public void onClick(View view) {

		Intent intent = null;
		switch (view.getId()) {
		case R.id.welcome_1:
//			if (KeyValue.isOpen) {
				intent = new Intent(this, NetActivity.class);
				this.startActivityForResult(intent, 101);
//			} else {
//				intent = new Intent(this, SettingOpenBusinessActivity.class);
//				this.startActivityForResult(intent, 101);
//			}

			return;
		case R.id.welcome_2:

//			if (KeyValue.isOpen) {
				intent = new Intent(this, SaleActivity.class);
				this.startActivityForResult(intent, 101);
//			} else {
//				intent = new Intent(this, SettingOpenBusinessActivity.class);
//				this.startActivityForResult(intent, 101);
//			}

			return;
		case R.id.welcome_3:
			// intent = new Intent(this, SmsActivity.class);
			// this.startActivityForResult(intent, 101);
			return;
		case R.id.welcome_4:
//			if (KeyValue.isOpen) {
				intent = new Intent(this, QueryActivity.class);
				this.startActivityForResult(intent, 101);
//			} else {
//				intent = new Intent(this, SettingOpenBusinessActivity.class);
//				this.startActivityForResult(intent, 101);
//			}

			return;
		case R.id.welcome_5:
//			if (KeyValue.isOpen) {
				intent = new Intent(this, StatisticsActivity.class);
				this.startActivityForResult(intent, 101);
//			} else {
//				intent = new Intent(this, SettingOpenBusinessActivity.class);
//				this.startActivityForResult(intent, 101);
//			}
			return;
		case R.id.top_back:

			showQuitDialog();

			return;
		}

		super.onClick(view);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			showQuitDialog();

			return true;
		}

		return super.onKeyDown(keyCode, event);
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
