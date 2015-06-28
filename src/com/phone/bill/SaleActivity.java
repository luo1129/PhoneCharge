package com.phone.bill;

import java.text.SimpleDateFormat;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.telephony.TelephonyManager;

public class SaleActivity extends MainActivity {

	int selectedMoneny = -1;

	String mMoneny;

	String mPhone;

	String mNum;

	Spinner gradeSpinner;

	String mSum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.card_recharge);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,

		R.layout.spinner, this.getResources().getStringArray(R.array.nums));

		gradeSpinner = (Spinner) this.findViewById(R.id.card_recharge_spinner);

		gradeSpinner.setAdapter(adapter);

	}

	// http://111.67.206.182:8889/posrecharge/posrechargeAction!prepaid_card_sales.action?
	// termId=1&merchantId=4&operatorId=123456&tradeNo=1&usrPhonenumber=15699583954&cardnumber=2&chargeFee=10000&feetotal=20000&tradeTime=20150613
	private void loadData(String phone, String moneny, String num, String sum) {

		RequestParams params = new RequestParams();

		params.put("mercAccount", KeyValue.mAccount);
		params.put("termId", "1");
		params.put("operatorId", KeyValue.operatorID);
		params.put("usrPhonenumber", phone);
		params.put("chargeFee", moneny);
		params.put("feetotal", sum);
		params.put("cardnumber", num);

		String Imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
				.getDeviceId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhss");
		String time = sdf.format(new java.util.Date());

		params.put("tradeNo", Imei.concat(time));
		params.put("tradeTime", time);
		GKHttpWrapper.get("prepaid_card_sales.action", params, new RestCallback() {

			@Override
			public void onSuccess(JSONObject object) {
				// TODO Auto-generated method stub
				findViewById(R.id.card_recharge_confirm).setVisibility(
						View.GONE);
				findViewById(R.id.card_recharge_success).setVisibility(
						View.VISIBLE);
				TextView tv = (TextView)findViewById(R.id.card_success_phone);
				tv.setText(mPhone);
				tv = (TextView) findViewById(R.id.card_success_num);
				tv.setText(mNum);
				tv = (TextView)findViewById(R.id.card_success_sum);
				mSum = String.valueOf(Integer.parseInt(mNum) * Integer.parseInt(mMoneny));
				tv.setText(mSum);
			}

			@Override
			public void onFailure(Throwable throwable, JSONObject object) {
				// TODO Auto-generated method stub
				findViewById(R.id.card_recharge_confirm).setVisibility(
						View.GONE);
				findViewById(R.id.card_recharge_failed).setVisibility(
						View.VISIBLE);				
				String message = object.optString("message");				
				TextView tv = (TextView) findViewById(R.id.card_failed_error);
				tv.setText(R.string.card_fail_reason);
				tv.append(message);
			}

		});

	}

	public void onClickMoneny(View view) {

		if (selectedMoneny != -1) {
			Button old = (Button) this.findViewById(R.id.card_value1
					+ selectedMoneny);
			old.setBackgroundResource(R.drawable.recharge_moneny_bg_1);
			old.setTextColor(0xffff0000);
		}

		view.setBackgroundResource(R.drawable.recharge_moneny_bg_2);
		((Button) view).setTextColor(0xffffffff);
		selectedMoneny = view.getId() - R.id.card_value1;
		mMoneny = (String) ((Button) view).getText();
	}

	public void onClickRecharge(View view) {
		if (TextUtils.isEmpty(mMoneny)) {
			return;
		}
		mPhone = ((EditText) this.findViewById(R.id.card_recharge_phone))
				.getEditableText().toString();
		if (TextUtils.isEmpty(mPhone)) {
			return;
		}
		mNum = gradeSpinner.getSelectedItem().toString();
		if (TextUtils.isEmpty(mNum)) {
			return;
		}

		this.findViewById(R.id.card_recharge_confirm).setVisibility(
				View.VISIBLE);

		TextView tv = (TextView) this
				.findViewById(R.id.card_confirm_user_phone);
		tv.setText(mPhone);
		tv = (TextView) this.findViewById(R.id.card_confirm_moneny);
		tv.setText(mMoneny);
		tv = (TextView) this.findViewById(R.id.card_confirm_num);
		tv.setText(mNum);
		tv = (TextView) this.findViewById(R.id.card_confirm_sum);
		mSum = String.valueOf(Integer.parseInt(mNum) * Integer.parseInt(mMoneny));
		tv.setText(mSum);

	}

	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.card_recharge_confirm_submit:

			loadData(mPhone, mMoneny, mNum, mSum);
			break;
		case R.id.card_recharge_confirm_cancel:
			this.findViewById(R.id.card_recharge_confirm).setVisibility(
					View.GONE);
			break;
		case R.id.card_recharge_fail_back:
			this.findViewById(R.id.card_recharge_failed).setVisibility(
					View.GONE);
			break;
		case R.id.card_recharge_success_print:
			this.findViewById(R.id.card_recharge_success).setVisibility(
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
