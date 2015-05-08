package com.jimmylee.memorytraining;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

public abstract class BaseActivity extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

	}

	/**
	 * 是否显示返回键
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	protected void setBackButton(boolean isShow) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) { //Android3.0及其以上
			android.app.ActionBar actionBar = getActionBar();
			actionBar.setDisplayHomeAsUpEnabled(isShow);
		} else {
			ActionBar actionBar = getSupportActionBar();
			actionBar.setDisplayHomeAsUpEnabled(isShow);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
