package com.jimmylee.memorytraining;

import java.util.List;

import android.content.Context;
import android.widget.BaseAdapter;
/**
 * 自定义的抽象适配器
 * @param <T> 列表的实体类
 */
public abstract class CustomBaseAdapter<T> extends BaseAdapter {
	protected Context context;
	protected List<T> list;

	public CustomBaseAdapter(Context context, List<T> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
