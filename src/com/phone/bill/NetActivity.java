package com.phone.bill;

import org.json.JSONObject;

import com.typ.gk.net.GKHttpWrapper;
import com.typ.gk.net.RestCallback;
import com.typ.gk.net.loopj.RequestParams;

import Util.KeyValue;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NetActivity extends MainActivity {

	int selectedMoneny = -1;

	String moneny;

	String phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.net_recharge);
	}

	private void loadData(final String phone, final String moneny) {

		RequestParams params = new RequestParams();

		params.put("mercAccount", KeyValue.mAccount);
		params.put("termId", "1");
		params.put("operatorId", KeyValue.operatorID);
		params.put("usrPhonenumber", phone);
		params.put("chargeFee", moneny);

		GKHttpWrapper.get("Recharge.action", params, new RestCallback() {

			@Override
			public void onSuccess(JSONObject object) {
				// TODO Auto-generated method stub
				findViewById(R.id.net_recharge_confirm)
						.setVisibility(View.GONE);
				findViewById(R.id.net_recharge_success).setVisibility(
						View.VISIBLE);			
				TextView tv = (TextView) findViewById(R.id.net_recharge_success_phone);
				tv.setText(phone);
				tv = (TextView) findViewById(R.id.net_recharge_success_fee);
				tv.setText(moneny);
			}

			@Override
			public void onFailure(Throwable throwable, JSONObject object) {
				// TODO Auto-generated method stub
				findViewById(R.id.net_recharge_confirm)
						.setVisibility(View.GONE);
				findViewById(R.id.net_recharge_failed).setVisibility(
						View.VISIBLE);
				String message = object.optString("message");
				TextView tv = (TextView) findViewById(R.id.net_failed_error);
				tv.setText(message);

			}

		});

	}

	public void onClickMoneny(View view) {

		if (selectedMoneny != -1) {
			Button old = (Button) this.findViewById(R.id.net_moneny_1
					+ selectedMoneny);
			old.setBackgroundResource(R.drawable.recharge_moneny_bg_1);
			old.setTextColor(0xffff0000);
		}

		view.setBackgroundResource(R.drawable.recharge_moneny_bg_2);
		((Button) view).setTextColor(0xffffffff);
		selectedMoneny = view.getId() - R.id.net_moneny_1;
		moneny = (String) ((Button) view).getText();
	}

	public void onClick(View view) {
		super.onClick(view);

		switch (view.getId()) {
		case R.id.net_recharge_submit:
			if (TextUtils.isEmpty(moneny)) {
				return;
			}
			phone = ((EditText) this.findViewById(R.id.net_recharge_phone))
					.getEditableText().toString();
			if (TextUtils.isEmpty(phone)) {
				return;
			}
			this.findViewById(R.id.net_recharge_confirm).setVisibility(
					View.VISIBLE);

			TextView tv = (TextView) this.findViewById(R.id.net_confirm_phone);
			tv.setText(phone);
			tv = (TextView) this.findViewById(R.id.net_confirm_moneny);
			tv.setText(moneny);

			break;
		case R.id.net_recharge_confirm_submit:
			// this.findViewById(R.id.net_recharge_confirm).setVisibility(
			// View.GONE);
			// this.findViewById(R.id.net_recharge_success).setVisibility(
			// View.VISIBLE);
			loadData(phone, moneny);
			break;
		case R.id.net_recharge_confirm_cancel:
			this.findViewById(R.id.net_recharge_confirm).setVisibility(
					View.GONE);
			break;
		case R.id.net_recharge_fail_back:
			this.findViewById(R.id.net_recharge_failed)
					.setVisibility(View.GONE);
			break;
		case R.id.net_recharge_success_print:
			this.findViewById(R.id.net_recharge_success).setVisibility(
					View.GONE);
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
