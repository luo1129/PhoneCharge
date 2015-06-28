package com.phone.bill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class SettingActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.setting);
		hideSetting();
	}

	public void onClick(View view) {
		super.onClick(view);
		Intent intent = null;
		switch (view.getId()) {
		case R.id.s11:
			intent = new Intent(this, SettingOpenBusinessActivity.class);
			this.startActivityForResult(intent, 101);
			break;
		case R.id.s12:
			intent = new Intent(this, InfoActivity.class);
			this.startActivityForResult(intent, 101);
			break;
		case R.id.s13:
			intent = new Intent(this, ResetActivity.class);
			this.startActivityForResult(intent, 101);
			break;
		case R.id.s14:
			break;
		case R.id.s15:
			intent = new Intent(this, HelpActivity.class);
			this.startActivityForResult(intent, 101);
			break;
		case R.id.s16:
			intent = new Intent(this, AboutActivity.class);
			this.startActivityForResult(intent, 101);
			break;
		case R.id.s17:
			showQuitDialog();
			break;

		}
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
