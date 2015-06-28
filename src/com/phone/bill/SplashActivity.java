package com.phone.bill;

import java.util.Locale;

import Util.ShareData;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class SplashActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.RGBA_8888);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
		ShareData.init(this.getApplicationContext());
		if (LoginActivity.mainAct != null) {
			Intent mainIntent = new Intent(SplashActivity.this,
					LoginActivity.class);
			SplashActivity.this.startActivity(mainIntent);
			SplashActivity.this.finish();
			return;
		}

		
		
		


		setContentView(R.layout.splash);

		new Handler().postDelayed(new Runnable() {
			public void run() {
				/* Create an Intent that will start the Main WordPress Activity. */

				if (ShareData.getBoolean("skipGuide")) {
					Intent mainIntent = new Intent(SplashActivity.this,
							LoginActivity.class);
					SplashActivity.this.startActivity(mainIntent);
					SplashActivity.this.finish();
				} else {
					setContentView(R.layout.guide);

					viewFlipper = (ViewSwitcher) SplashActivity.this
							.findViewById(R.id.guide_container);
					ShareData.putBoolean("skipGuide", true);
				}

			}
		}, 2000); // 2900 for release

	}

	public void gotoLogin(View view) {
		Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
		SplashActivity.this.startActivity(mainIntent);
		SplashActivity.this.finish();
	}

	ViewSwitcher viewFlipper = null;

	float startX;

	public boolean onTouchEvent(MotionEvent event) {

		if (viewFlipper == null) {
			return true;
		}

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			break;
		case MotionEvent.ACTION_UP:

			if (event.getX() > startX) { // ���һ���
				// viewFlipper.setInAnimation(this, R.anim.in_leftright);
				// viewFlipper.setOutAnimation(this, R.anim.out_leftright);
				viewFlipper.showNext();
				((ImageView)this.findViewById(R.id.guide_circle)).setImageResource(R.drawable.circle2);
				
			} else if (event.getX() < startX) { // ���󻬶�
				// viewFlipper.setInAnimation(this, R.anim.in_rightleft);
				// viewFlipper.setOutAnimation(this, R.anim.out_rightleft);
				viewFlipper.showPrevious();
				((ImageView)this.findViewById(R.id.guide_circle)).setImageResource(R.drawable.circle1);
			}
			
			
			System.out.println("viewFlipper.getDisplayedChild(): "+viewFlipper.getDisplayedChild());
			
			break;
		}

		return super.onTouchEvent(event);
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
