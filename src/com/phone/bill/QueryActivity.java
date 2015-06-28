package com.phone.bill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

import com.typ.gk.net.GKHttpWrapper;
import com.typ.gk.net.RestCallback;
import com.typ.gk.net.loopj.RequestParams;

import Util.KeyValue;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QueryActivity extends MainActivity {
	TextView startDate;
	TextView endDate;
	String mStart;
	String mEnd;
	boolean isLeft = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	// http://111.67.206.182:8889/posrecharge/posrechargeAction!prepaid_card_query.action?
	// termId=1&merchantId=4&operatorId=123456&usrPhonenumber=15699583954&tradePeriod=20150613

	private void loadCardData(String phone, String period) {

		RequestParams params = new RequestParams();
		params.put("mercAccount", KeyValue.mAccount);
		params.put("termId", "1");
		params.put("operatorId", KeyValue.operatorID);
		params.put("usrPhonenumber", phone);
		params.put("tradePeriod", period);

		// params.put("mercAccount", "4");
		// params.put("termId", "1");
		// params.put("operatorId", "123456");
		// params.put("usrPhonenumber", "15699583954");
		// params.put("tradePeriod", "20150613");

		GKHttpWrapper.get("prepaid_card_query.action", params,
				new RestCallback() {

					@Override
					public void onSuccess(JSONObject object) {
						// TODO Auto-generated method stub

						System.out.println("object: " + object.toString());

						JSONArray list = object.optJSONArray("list");

						ArrayList<String[]> operatorList = new ArrayList<String[]>();

						// {"tranSeq":1.0,"usrPhonenumber":"15699583954","cardnumber":2,"chargeFee":10000.0,"feetotal":20000.0,
						// "chargeTime":"2015-06-14","transactionId":9},{"
						Intent intent3 = new Intent(QueryActivity.this,
								QueryResultActivity.class);

						if (list != null) {
							for (int i = 0; i < list.length(); i++) {
								JSONObject one = list.optJSONObject(i);

								String[] date = new String[8];

								date[0] = one.optString("tranSeq");
								date[1] = one.optString("usrPhonenumber");
								date[2] = one.optString("cardnumber");
								date[3] = one.optString("chargeFee");
								date[4] = one.optString("feetotal");
								date[5] = one.optString("chargeTime");
								date[6] = one.optString("transactionId");
								date[7] = one.optString("useNumber");

								operatorList.add(date);
							}

							intent3.putExtra("data", operatorList);

						}

						intent3.putExtra("isLeft", false);

						intent3.putExtra("start", mStart);
						intent3.putExtra("end", mEnd);
						startActivityForResult(intent3, 100);
					}

					@Override
					public void onFailure(Throwable throwable, JSONObject object) {
						// TODO Auto-generated method stub
						String message = object.optString("message");
						Toast.makeText(getBaseContext(), message,
								Toast.LENGTH_SHORT).show();
					}

				});

	}

	private void loadNetData(String phone, String period) {

		RequestParams params = new RequestParams();
		params.put("mercAccount", KeyValue.mAccount);
		params.put("termId", "1");
		params.put("operatorId", KeyValue.operatorID);
		params.put("usrPhonenumber", phone);
		params.put("tradePeriod", period);

		// params.put("mercAccount", "4");
		// params.put("termId", "1");
		// params.put("operatorId", "123456");
		// params.put("usrPhonenumber", "15699583954");
		params.put("chargeFee", "50");
		// termId=1&merchantId=1&operatorId=1&usrPhonenumber=15699583954&chargeFee=50
		GKHttpWrapper.get("seach_TradingVolume.action", params,
				new RestCallback() {

					@Override
					public void onSuccess(JSONObject object) {
						// TODO Auto-generated method stub

						System.out.println("object: " + object.toString());

						JSONArray list = object.optJSONArray("list");

						ArrayList<String[]> operatorList = new ArrayList<String[]>();
						Intent intent3 = new Intent(QueryActivity.this,
								QueryResultActivity.class);

						if (list != null) {
							for (int i = 0; i < list.length(); i++) {
								JSONObject one = list.optJSONObject(i);

								String[] date = new String[5];

								date[0] = one.optString("tranSeq");
								date[1] = one.optString("usrPhonenumber");
								date[2] = one.optString("chargeFee");
								date[3] = one.optString("chargeTime");
								date[4] = one.optString("transactionId");

								operatorList.add(date);
							}
							intent3.putExtra("data", operatorList);
						}

						intent3.putExtra("isLeft", true);
						intent3.putExtra("start", mStart);
						intent3.putExtra("end", mEnd);
						startActivityForResult(intent3, 100);
					}

					@Override
					public void onFailure(Throwable throwable, JSONObject object) {
						// TODO Auto-generated method stub
						String message = object.optString("message");
						Toast.makeText(getBaseContext(), message,
								Toast.LENGTH_SHORT).show();
					}

				});

	}

	public void choseQuery(View view) {
		TextView left = (TextView) this.findViewById(R.id.sale_query_left_tv);
		TextView right = (TextView) this.findViewById(R.id.sale_query_right_tv);
		left.setBackgroundColor(0x000000000);
		right.setBackgroundColor(0x000000000);
		switch (view.getId()) {
		case R.id.sale_query_left:
			isLeft = true;
			right.setBackgroundColor(0xfff84848);
			break;
		case R.id.sale_query_right:
			isLeft = false;
			left.setBackgroundColor(0xfff84848);
			break;
		}

	}

	public void gotoQueryResult(View view) {

		EditText tv = (EditText) this.findViewById(R.id.query_phone);
		String phone = tv.getEditableText().toString();

		// if (TextUtils.isEmpty(phone)) {
		// return;
		// }

		if (TextUtils.isEmpty(mStart) || TextUtils.isEmpty(mEnd)) {
			return;
		}

		if (isLeft) {
			loadNetData(phone, mStart.concat(mEnd).replaceAll("-", ""));
		} else {
			loadCardData(phone, mStart.concat(mEnd).replaceAll("-", ""));
		}

	}

	public void pickDate(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View view = View.inflate(this, R.layout.date_picker, null);
		final DatePicker datePicker = (DatePicker) view
				.findViewById(R.id.date_picker);
		builder.setView(view);

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH), null);

		if (v.getId() == R.id.sale_query_start_container) {
			// final int inType = etStartTime.getInputType();
			// etStartTime.setInputType(InputType.TYPE_NULL);
			// etStartTime.onTouchEvent(event);
			// etStartTime.setInputType(inType);
			// etStartTime.setSelection(etStartTime.getText().length());

			builder.setTitle(R.string.query_start_date);
			builder.setPositiveButton(R.string.confirm,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							StringBuffer sb = new StringBuffer();
							sb.append(String.format("%d-%02d-%02d",
									datePicker.getYear(),
									datePicker.getMonth() + 1,
									datePicker.getDayOfMonth()));
							startDate.setText(sb);
							mStart = sb.toString();
							dialog.cancel();
						}
					});

		} else if (v.getId() == R.id.sale_query_end_container) {
			// int inType = etEndTime.getInputType();
			// etEndTime.setInputType(InputType.TYPE_NULL);
			// etEndTime.onTouchEvent(event);
			// etEndTime.setInputType(inType);
			// etEndTime.setSelection(etEndTime.getText().length());

			builder.setTitle(R.string.query_start_date);
			builder.setPositiveButton(R.string.confirm,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							StringBuffer sb = new StringBuffer();
							sb.append(String.format("%d-%02d-%02d",
									datePicker.getYear(),
									datePicker.getMonth() + 1,
									datePicker.getDayOfMonth()));
							endDate.setText(sb);
							mEnd = sb.toString();
							dialog.cancel();
						}
					});
		}

		Dialog dialog = builder.create();
		dialog.show();
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
		this.setContentView(R.layout.sale_query);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new java.util.Date());

		startDate = (TextView) this.findViewById(R.id.sale_query_start);
		endDate = (TextView) this.findViewById(R.id.sale_query_end);

		startDate.setText(date);
		endDate.setText(date);

		mEnd = mStart = date;
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
