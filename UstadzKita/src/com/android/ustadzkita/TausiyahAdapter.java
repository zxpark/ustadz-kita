package com.android.ustadzkita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TausiyahAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] Values;

	public TausiyahAdapter(Context context, String[] objects) {
		super(context, R.layout.tausiyah_singlelist, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.Values = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.tausiyah_singlelist, parent,
				false);

		TextView ttt_title = (TextView) rowView.findViewById(R.id.ttt_title);
		// TextView ttt_date = (TextView) rowView.findViewById(R.id.ttt_date);
		// TextView mmm_name = (TextView) rowView.findViewById(R.id.mmm_name);
		// TextView ttt_desc = (TextView) rowView.findViewById(R.id.ttt_desc);
		// TextView ttt_id = (TextView) rowView.findViewById(R.id.ttt_id);

		ttt_title.setText(Values[position]);
		return rowView;
	}
}
