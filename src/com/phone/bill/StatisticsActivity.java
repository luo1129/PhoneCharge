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
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class StatisticsActivity extends MainActivity {
	TextView startDate;
	TextView endDate;
	String mStart;
	String mEnd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.balance);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new java.util.Date());

		startDate = (TextView) this.findViewById(R.id.balance_date_start);
		endDate = (TextView) this.findViewById(R.id.balance_date_end);

		startDate.setText(date);
		endDate.setText(date);

		mStart = date;
		mEnd = date;

		TextView tv = (TextView) this.findViewById(R.id.balance_account);
		tv.append(KeyValue.mAccount);
		tv = (TextView) this.findViewById(R.id.balance_name);
		tv.append(KeyValue.mName);

	}

	// http://111.67.206.182:8889/posrecharge/posrechargeAction!merchantPosTotal.action?
	// termId=1&merchantId=4&operatorId=123456sumTime=20150613

	private void loadData(String period) {

		RequestParams params = new RequestParams();

		params.put("termId", "1");
		params.put("operatorId", KeyValue.operatorID);
		params.put("mercAccount", KeyValue.mAccount);
		params.put("sumTime", period);

		GKHttpWrapper.get("merchantPosTotal.action", params, new RestCallback() {

			@Override
			public void onSuccess(JSONObject object) {
				// TODO Auto-generated method stub

				Intent intent3 = new Intent(StatisticsActivity.this, StatisticsResultActivity.class);
//				intent3.putExtra("left", object.optString("balanceamt"));
				intent3.putExtra("time1", mStart.concat("~").concat(mEnd));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM");
				String date = sdf.format(new java.util.Date());
				intent3.putExtra("time2", date);

				JSONArray list = object.optJSONArray("list");

				if (list.length() > 0) {
					JSONObject one = list.optJSONObject(0);

					if (TextUtils.isEmpty(one.optString("chargeSum"))) {
						intent3.putExtra("value1", "0");
					} else {
						intent3.putExtra("value1", one.optString("chargeSum"));
					}

					if (TextUtils.isEmpty(one.optString("chargeTotal"))) {
						intent3.putExtra("value2", "0");
					} else {
						intent3.putExtra("value2", one.optString("chargeTotal"));
					}

					if (TextUtils.isEmpty(one.optString("chargeTotal1"))) {
						intent3.putExtra("value3", "0");
					} else {
						intent3.putExtra("value3", one.optString("chargeTotal1"));
					}

					if (TextUtils.isEmpty(one.optString("chargeSum1"))) {
						intent3.putExtra("value4", "0");
					} else {
						intent3.putExtra("value4", one.optString("chargeSum1"));
					}

				}
				startActivityForResult(intent3, 100);
			}

			@Override
			public void onFailure(Throwable throwable, JSONObject object) {
				// TODO Auto-generated method stub
				String message = object.optString("message");
				Toast.makeText(StatisticsActivity.this, message, Toast.LENGTH_SHORT).show();
			}

		});

	}

	public void submit(View v) {

		if (TextUtils.isEmpty(mStart) || TextUtils.isEmpty(mEnd)) {
			return;
		}

		loadData(mStart.concat(mEnd).replaceAll("-", ""));
	}

	public void pickDate(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View view = View.inflate(this, R.layout.date_picker, null);
		final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
		builder.setView(view);

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);

		if (v.getId() == R.id.balance_date_start_container) {
			// final int inType = etStartTime.getInputType();
			// etStartTime.setInputType(InputType.TYPE_NULL);
			// etStartTime.onTouchEvent(event);
			// etStartTime.setInputType(inType);
			// etStartTime.setSelection(etStartTime.getText().length());

			builder.setTitle(R.string.query_start_date);
			builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

					StringBuffer sb = new StringBuffer();
					sb.append(String.format("%d-%02d-%02d", datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth()));
					startDate.setText(sb);
					mStart = sb.toString();
					dialog.cancel();
				}
			});

		} else if (v.getId() == R.id.balance_date_end_container) {
			// int inType = etEndTime.getInputType();
			// etEndTime.setInputType(InputType.TYPE_NULL);
			// etEndTime.onTouchEvent(event);
			// etEndTime.setInputType(inType);
			// etEndTime.setSelection(etEndTime.getText().length());

			builder.setTitle(R.string.query_start_date);
			builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

					StringBuffer sb = new StringBuffer();
					sb.append(String.format("%d-%02d-%02d", datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth()));
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
