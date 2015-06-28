package com.phone.bill;

import org.json.JSONObject;

import com.typ.gk.net.GKHttpWrapper;
import com.typ.gk.net.RestCallback;
import com.typ.gk.net.loopj.RequestParams;

import Util.KeyValue;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class GetPwdActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.forget_pwd);
	}
	
	private void loadData(String pwd) {

		RequestParams params = new RequestParams();

		params.put("mercAccount", KeyValue.mAccount);
		params.put("operatorPwd", pwd);

		GKHttpWrapper.get("password_change.action", params,
				new RestCallback() {

					@Override
					public void onSuccess(JSONObject object) {
						// TODO Auto-generated method stub
						String message = object.optString("message");
						Toast.makeText(GetPwdActivity.this, message,
								Toast.LENGTH_SHORT).show();
						findViewById(R.id.forget_success).setVisibility(View.VISIBLE);
//						GetPwdActivity.this.finish();
					}

					@Override
					public void onFailure(Throwable throwable, JSONObject object) {
						// TODO Auto-generated method stub
						String message = object.optString("message");
						Toast.makeText(GetPwdActivity.this, message,
								Toast.LENGTH_SHORT).show();
					}

				});

	}


	public void onClick(View view) {
		
		super.onClick(view);
		switch (view.getId()) {
		case R.id.forget_pwd_submit:
			EditText tv = (EditText) this.findViewById(R.id.forget_pwd_1);
			String pwd1 = tv.getEditableText().toString();
			tv = (EditText) this.findViewById(R.id.forget_pwd_2);
			String pwd2 = tv.getEditableText().toString();

			if (TextUtils.isEmpty(pwd1) && pwd1.length() != 6) {
				return;
			}

			if (TextUtils.isEmpty(pwd2) && pwd1.length() != 6) {
				return;
			}
			
			if (!pwd1.equals(pwd2)) {
				return;
			}

			loadData(pwd1);
			break;
		case R.id.forget_pwd_goto_login:
			this.finish();
			Intent intent1 = new Intent(this, LoginActivity.class);
			this.startActivityForResult(intent1, 100);
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
