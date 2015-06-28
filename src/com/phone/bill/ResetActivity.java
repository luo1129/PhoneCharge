package com.phone.bill;

import org.json.JSONObject;

import com.typ.gk.net.GKHttpWrapper;
import com.typ.gk.net.RestCallback;
import com.typ.gk.net.loopj.RequestParams;

import Util.KeyValue;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class ResetActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.reset_pwd);
	}

	private void loadData(String pwd, String code) {

		RequestParams params = new RequestParams();

		params.put("mercAccount", KeyValue.mAccount);
		params.put("operatorPwd", pwd);
		params.put("smsAuthCode", code);
		params.put("VerFlag", KeyValue.verFlag);
		GKHttpWrapper.get("password_change.action", params,
				new RestCallback() {

					@Override
					public void onSuccess(JSONObject object) {
						// TODO Auto-generated method stub
						System.out.println(object.toString());
						String message = object.optString("message");
						Toast.makeText(ResetActivity.this, message,
								Toast.LENGTH_SHORT).show();
						ResetActivity.this.finish();
					}

					@Override
					public void onFailure(Throwable throwable, JSONObject object) {
						// TODO Auto-generated method stub

					}

				});

	}

	public void reset(View view) {
		EditText tv = (EditText) this.findViewById(R.id.reset_pwd_1);
		String pwd1 = tv.getEditableText().toString();
		tv = (EditText) this.findViewById(R.id.reset_pwd_2);
		String pwd2 = tv.getEditableText().toString();
		tv = (EditText) this.findViewById(R.id.reset_pwd_code);
		String code = tv.getEditableText().toString();

		if (TextUtils.isEmpty(pwd1)) {
			return;
		}
		if (TextUtils.isEmpty(pwd2)) {
			return;
		}

		if (!pwd1.equals(pwd2)) {
			return;
		}

		if (TextUtils.isEmpty(code)) {
			return;
		}

		loadData(pwd1, code);
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
