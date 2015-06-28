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

public class ModifyActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.info_modify);
		hideSetting();
	}

	private void loadData(String name, String phone, String code) {

		RequestParams params = new RequestParams();

		params.put("mercAccount", KeyValue.mAccount);
		params.put("merchantName", name);
		params.put("merchantPhone", phone);
		params.put("smsAuthCode", code);
		GKHttpWrapper.get("information_change.action", params,
				new RestCallback() {

					@Override
					public void onSuccess(JSONObject object) {
						// TODO Auto-generated method stub
						System.out.println(object.toString());
						ModifyActivity.this.finish();
					}

					@Override
					public void onFailure(Throwable throwable, JSONObject object) {
						// TODO Auto-generated method stub

					}

				});

	}

	public void confirmModify(View view) {

		EditText tv = (EditText) this.findViewById(R.id.modify_name);
		String name = tv.getEditableText().toString();
		tv = (EditText) this.findViewById(R.id.modify_phone);
		String phone = tv.getEditableText().toString();
		tv = (EditText) this.findViewById(R.id.modify_code);
		String code = tv.getEditableText().toString();

		if (TextUtils.isEmpty(name)) {
			return;
		}
		if (TextUtils.isEmpty(phone)) {
			return;
		}
		if (TextUtils.isEmpty(code)) {
			return;
		}

		loadData(name, phone, code);
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
