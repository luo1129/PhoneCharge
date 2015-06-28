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

public class ForgetActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.forget);
	}

	private void loadData(String operatorId, String smsAuthCode) {

		RequestParams params = new RequestParams();

		params.put("termId", "2");
		params.put("operatorId", operatorId);
		params.put("smsAuthCode", smsAuthCode);

		GKHttpWrapper.get("effective_verification.action", params,
				new RestCallback() {

					@Override
					public void onSuccess(JSONObject object) {
						// TODO Auto-generated method stub
						String message = object.optString("message");
						Toast.makeText(ForgetActivity.this, message,
								Toast.LENGTH_SHORT).show();

						KeyValue.mAccount = object.optString("mercAccount");

						Intent intent3 = new Intent(ForgetActivity.this,
								GetPwdActivity.class);
						startActivityForResult(intent3, 100);
					}

					@Override
					public void onFailure(Throwable throwable, JSONObject object) {
						// TODO Auto-generated method stub
						String message = object.optString("message");
						Toast.makeText(ForgetActivity.this, message,
								Toast.LENGTH_SHORT).show();
					}

				});

	}

	public void onClick(View view) {
		super.onClick(view);

		switch (view.getId()) {
		case R.id.forget_next:

			EditText tv = (EditText) this.findViewById(R.id.forget_account);
			String name = tv.getEditableText().toString();
			tv = (EditText) this.findViewById(R.id.forget_code_et);
			String code = tv.getEditableText().toString();

			if (TextUtils.isEmpty(name)) {
				return;
			}

			if (TextUtils.isEmpty(code)) {
				return;
			}

			loadData(name, code);

			break;
		case R.id.forget_code:
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
