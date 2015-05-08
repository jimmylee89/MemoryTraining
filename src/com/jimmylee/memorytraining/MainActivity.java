package com.jimmylee.memorytraining;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 主页
 * @author jimmylee
 * @version 创建时间：2015年4月30日  上午11:36:29
 */
public class MainActivity extends BaseActivity {
	private Button btnRecord;
	private Button btnStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnRecord = (Button) findViewById(R.id.btn_record);
		btnStart = (Button) findViewById(R.id.btn_start);

		btnRecord.setOnClickListener(new OnClistenerImpl());
		btnStart.setOnClickListener(new OnClistenerImpl());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class OnClistenerImpl implements OnClickListener {
		Intent intent = null;

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_record:
				intent = new Intent(getApplicationContext(), RecordActivity.class);
				break;

			case R.id.btn_start:
				intent = new Intent(getApplicationContext(), TrainingStartActivity.class);

				break;

			default:
				break;
			}
			startActivity(intent);
		}

	}

}
