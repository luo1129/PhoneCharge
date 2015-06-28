package Util;

import com.phone.bill.LoginActivity;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareData {

	static SharedPreferences mSP;

	static ShareData mInstance;

	// public static ShareData getIntance(){
	//
	// if(mInstance == null){
	//
	// mInstance = new ShareData();
	// }
	//
	// return this;
	// }

	public static void init(Context context) {
		if (mSP == null) {
			mSP = context.getSharedPreferences(ShareData.class.getName(), 0);
		}
	}

	public static void putInt(String key, int value) {
		SharedPreferences.Editor prefsWriter = mSP.edit();
		prefsWriter.putInt(key, value);
		prefsWriter.commit();
	}

	public static int getInt(String key) {
		if (mSP.contains(key)) {
			return mSP.getInt(key, 0);
		}
		return 0;
	}

	public static void putBoolean(String key, boolean value) {
		SharedPreferences.Editor prefsWriter = mSP.edit();
		prefsWriter.putBoolean(key, value);
		prefsWriter.commit();
	}

	public static boolean getBoolean(String key) {
		if (mSP.contains(key)) {
			return mSP.getBoolean(key, false);
		}
		return false;
	}

	public static void putString(String key, String value) {
		SharedPreferences.Editor prefsWriter = mSP.edit();
		prefsWriter.putString(key, value);
		prefsWriter.commit();
	}

	public static String getString(String key, String def) {
		if (mSP.contains(key)) {
			return mSP.getString(key, def);
		}
		return def;
	}

}
