package com.phone.bill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.typ.gk.net.GKHttpWrapper;
import com.typ.gk.net.RestCallback;
import com.typ.gk.net.loopj.RequestParams;

import Util.KeyValue;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class QueryResultActivity extends MainActivity {

	boolean isNet;

	ArrayList<String[]> mDatas;

	String mStart;
	String mEnd;

	int selected;

	int time = 10;

	boolean countDown = true;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mDatas = (ArrayList<String[]>) this.getIntent().getSerializableExtra(
				"data");

		isNet = this.getIntent().getBooleanExtra("isLeft", true);

		mStart = this.getIntent().getStringExtra("start");

		mEnd = this.getIntent().getStringExtra("end");

		System.out.println("mStart: " + mStart);

		if (isNet) {
			this.setContentView(R.layout.net_query);
		} else {
			this.setContentView(R.layout.card_query);

		}

		init();

	}

	public void sendSms(View view) {
		this.findViewById(R.id.card_query_detail).setVisibility(View.GONE);

		RequestParams params = new RequestParams();
		params.put("mercAccount", KeyValue.mAccount);
		params.put("termId", "1");
		params.put("operatorId", KeyValue.operatorID);
		params.put("usrPhonenumber", mDatas.get(selected)[1]);
		params.put("tradeTime", mDatas.get(selected)[5]);
		params.put("transactionId", mDatas.get(selected)[6]);
		String Imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
				.getDeviceId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhss");
		String time = sdf.format(new java.util.Date());
		params.put("tradeNo", Imei.concat(time));

		// termId=1&merchantId=1&operatorId=1&usrPhonenumber=15699583954&chargeFee=50
		GKHttpWrapper.get("sms_sending.action", params, new RestCallback() {

			@Override
			public void onSuccess(JSONObject object) {
				// TODO Auto-generated method stub
				System.out.println(object.toString());
				String message = object.optString("message");
				Toast.makeText(QueryResultActivity.this, message,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(Throwable throwable, JSONObject object) {
				// TODO Auto-generated method stub
				System.out.println(object.toString());
				String message = object.optString("message");
				Toast.makeText(QueryResultActivity.this, message,
						Toast.LENGTH_SHORT).show();
			}

		});

	}

	public void close(View view) {
		countDown = false;
		this.findViewById(R.id.card_query_detail).setVisibility(View.GONE);
	}

	private void init() {
		TextView tv = (TextView) this
				.findViewById(R.id.query_result_data_start);
		tv.setText(mStart);
		tv = (TextView) this.findViewById(R.id.query_result_data_end);
		tv.setText(mEnd);

		if (mDatas == null) {
			return;
		}

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (String[] data : mDatas) {
			Map<String, Object> map = new HashMap<String, Object>();

			if (isNet) {
				map.put("phone", data[1]);
				map.put("time", data[3]);
				map.put("detail", data[2]);
			} else {
				map.put("phone", data[1]);
				map.put("time", data[5]);
				map.put("detail", ">>>");
			}

			list.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, list,
				R.layout.query_result_list_item, new String[] { "phone",
						"time", "detail" }, new int[] { R.id.phone, R.id.time,
						R.id.detail });

		ListView lv = (ListView) this.findViewById(R.id.query_result_list);
		lv.setAdapter(adapter);

		if (isNet) {

		} else {
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					QueryResultActivity.this.findViewById(
							R.id.card_query_detail).setVisibility(View.VISIBLE);

					TextView tv = (TextView) QueryResultActivity.this
							.findViewById(R.id.card_query_detail_user_phone);

					tv.setText(mDatas.get(arg2)[1]);
					tv = (TextView) QueryResultActivity.this
							.findViewById(R.id.card_query_detail_num);
					tv.setText(mDatas.get(arg2)[2]);
					tv = (TextView) QueryResultActivity.this
							.findViewById(R.id.card_query_moneny);
					tv.setText(mDatas.get(arg2)[3]);
					tv = (TextView) QueryResultActivity.this
							.findViewById(R.id.card_query_value);
					tv.setText(mDatas.get(arg2)[4]);
					tv = (TextView) QueryResultActivity.this
							.findViewById(R.id.card_query_date);
					tv.setText(mDatas.get(arg2)[5]);
					tv = (TextView) QueryResultActivity.this
							.findViewById(R.id.card_query_used);
					tv.setText(mDatas.get(arg2)[7]);
					selected = arg2;
					// card_query_detail
					countDown = true;
					time = 10;
					Button button = (Button) QueryResultActivity.this
							.findViewById(R.id.card_query_submit);
					button.setText(R.string.card_query_detail_sms);
					button.setEnabled(false);
					button.setClickable(false);
					new Thread() {
						public void run() {

							while (countDown) {
								try {
									sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								QueryResultActivity.this
										.runOnUiThread(new Runnable() {

											@Override
											public void run() {
												// TODO Auto-generated method
												// stub
												Button button = (Button) QueryResultActivity.this
														.findViewById(R.id.card_query_submit);
												String text = (String) button
														.getText();
												text = text.replace(String
														.valueOf(time), String
														.valueOf(time - 1));
												button.setText(text);
												time--;
												if (time == 0) {
													button.setEnabled(true);
													button.setClickable(true);
													countDown = false;
													button.setText(text
															.replace("(0)", ""));
												}
											}

										});
							}

						}
					}.start();

				}

			});
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
