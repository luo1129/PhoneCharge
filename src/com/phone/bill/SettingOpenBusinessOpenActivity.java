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
import android.widget.TextView;
import android.widget.Toast;

public class SettingOpenBusinessOpenActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// intent2.putExtra("vendorName", operatorList.get(1)[1]);
		// intent2.putExtra("crtAccount", operatorList.get(1)[0]);

		this.setContentView(R.layout.open_business_setting_open);
		hideSetting();
		TextView tv = (TextView) this.findViewById(R.id.will_open_name);
		tv.append(this.getIntent().getStringExtra("vendorName"));

	}

	public void open(View view) {
		String code = ((EditText) this.findViewById(R.id.will_open_code)).getEditableText().toString();

		if (TextUtils.isEmpty(code)) {
			return;
		}

		RequestParams params = new RequestParams();

		params.put("termId", "1");
		params.put("mercAccount", KeyValue.mAccount);
		params.put("vendorCode", this.getIntent().getStringExtra("crtAccount"));
		params.put("verFlag", "0");
		params.put("smsAuthCode", code);

		GKHttpWrapper.get("service_opening.action", params, new RestCallback() {

			@Override
			public void onSuccess(JSONObject object) {
				// TODO Auto-generated method stub
				System.out.println(object.toString());
				String message = object.optString("message");
				Toast.makeText(SettingOpenBusinessOpenActivity.this, message, Toast.LENGTH_SHORT).show();

				if(TextUtils.isEmpty(object.optString("vendorName"))){
					return;
				}
				if(TextUtils.isEmpty(object.optString("crtAccount"))){
					return;
				}
				
				Intent intent1 = new Intent(SettingOpenBusinessOpenActivity.this, OpenSuccessActivity.class);
				intent1.putExtra("vendorName", object.optString("vendorName"));
				intent1.putExtra("crtAccount", object.optString("crtAccount"));
				startActivityForResult(intent1, 100);

			}

			@Override
			public void onFailure(Throwable throwable, JSONObject object) {
				// TODO Auto-generated method stub
				System.out.println(object.toString());
			}

		});
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
