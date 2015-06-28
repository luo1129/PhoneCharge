package com.phone.bill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class OpenSuccessActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.open_business_success);
		init();
	}

	private void init() {
		String vendorName = this.getIntent().getStringExtra("vendorName");
		String crtAccount = this.getIntent().getStringExtra("crtAccount");

		TextView tv = (TextView) this
				.findViewById(R.id.open_business_success_name);
		tv.setText(vendorName);
		tv = (TextView) this.findViewById(R.id.open_business_success_code);
		tv.setText(crtAccount);
	}

	public void gotoLogin(View view) {
		Intent intent1 = new Intent(this, LoginActivity.class);
		this.startActivityForResult(intent1, 100);
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
