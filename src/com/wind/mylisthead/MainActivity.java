package com.wind.mylisthead;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private MyListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			list.add("测试" + i);
		}
		
		listView = (MyListView) this.findViewById(R.id.main_listView);
		LinearLayout listHead = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.list_head, null);
		listView.addHeaderView(listHead);
		listView.setAdapter(new MyAdapter(this, list));
	}
	
	private class MyAdapter extends BaseAdapter {
		
		private ArrayList<String> list;
		private Context context;
		
		public MyAdapter(Context context, ArrayList<String> list) {

			this.context = context;
			this.list = list;
			
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
				holder.textView = (TextView) convertView.findViewById(R.id.item_textView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.textView.setText(list.get(position));
			return convertView;
		}
		
		private class ViewHolder {
			
			public TextView textView;
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
