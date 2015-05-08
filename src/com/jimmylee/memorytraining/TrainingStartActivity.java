package com.jimmylee.memorytraining;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 主页
 */
public class TrainingStartActivity extends BaseActivity {
	/**
	 * 填写数字数量的提示
	 */
	private TextView tvDesc;
	/**
	 * 数量框
	 */
	private EditText etNumber;
	/**
	 * 开始按钮
	 */
	private Button btnStart;
	/**
	 * 随机数显示
	 */
	private GridView gridView;
	/**
	 * gridView显示的数据
	 */
	private ArrayList<Integer> numbers = new ArrayList<Integer>();
	/**
	 * gridView的adpater
	 */
	private NumberAdapter numberAdapter;
	/**
	 * 总数
	 */
	private int count = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_training_start);

		setBackButton(true);
		init();
		setItem();
	}

	/**
	 * 设置view的个数，先不显示数字
	 */
	private void setItem() {
		numbers.clear();
		setCount();

		for (int i = 0; i < count; i++) {
			numbers.add(-1);
		}
		numberAdapter.notifyDataSetChanged();
	}

	private void init() {
		gridView = (GridView) findViewById(R.id.gv_number);
		tvDesc = (TextView) findViewById(R.id.tv_number_desc);
		etNumber = (EditText) findViewById(R.id.et_number);
		btnStart = (Button) findViewById(R.id.btn_start);

		numberAdapter = new NumberAdapter(getApplicationContext(), numbers);
		gridView.setAdapter(numberAdapter);

		btnStart.setOnClickListener(new OnClickeListenerImpl());
		etNumber.addTextChangedListener(new TextWatcherImpl());
	}

	private class TextWatcherImpl implements TextWatcher {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void afterTextChanged(Editable s) {
			setCount();
			if (count > 200) {
				tvDesc.setVisibility(View.VISIBLE);
				tvDesc.setText("暂时不支持200以上的数字");
				return;
			} else if (count < 1) {
				tvDesc.setVisibility(View.VISIBLE);
				tvDesc.setText(R.string.training_tv_number_desc);
			} else {
				tvDesc.setVisibility(View.GONE);
				setItem();
			}
		}
	}

	private class OnClickeListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (count < 1) {
				Toast.makeText(getApplicationContext(), R.string.training_tv_number_desc, Toast.LENGTH_LONG).show();
				return;
			}
			
			numbers.clear();
			Random random = new Random();
			for(int i = 0; i < count; i++){
				numbers.add(random.nextInt(100));
			}
			numberAdapter.notifyDataSetChanged();

		}

	}

	/**
	 * 为总数量赋值
	 */
	private void setCount() {
		String strNumber = etNumber.getText().toString();
		if (!TextUtils.isEmpty(strNumber)) {
			count = Integer.parseInt(strNumber);
		}
	}

	/**
	 * gridView 的adapter
	 * @author jimmylee
	 * @version 创建时间：2015年5月4日  下午4:37:15
	 */
	public class NumberAdapter extends CustomBaseAdapter<Integer> {

		public NumberAdapter(Context context, List<Integer> list) {
			super(context, list);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			if (convertView == null) {
				holder = new Holder();
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_grid_training_start, null);
				holder.tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
				holder.tvOffiset = (TextView) convertView.findViewById(R.id.tv_offiset);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}

			if(list.get(position) < 0){
				convertView.setBackgroundColor(getResources().getColor(R.color.backgroud_gray));
				holder.tvNumber.setText("");
			}else{
				convertView.setBackgroundColor(getResources().getColor(R.color.white));
				holder.tvNumber.setText(list.get(position) + "");
			}
			holder.tvOffiset.setText((position + 1)+"");

			return convertView;
		}
	}

	class Holder {
		TextView tvNumber;
		TextView tvOffiset;
	}

}
