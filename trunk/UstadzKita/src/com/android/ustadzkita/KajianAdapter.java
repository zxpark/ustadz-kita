package com.android.ustadzkita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class KajianAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] Values;

	public KajianAdapter(Context context, String[] objects) {
		super(context, R.layout.kajian_singlelist, objects);

		this.context = context;
		this.Values = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.kajian_singlelist, parent,
				false);

		TextView tkj_title = (TextView) rowView.findViewById(R.id.tkj_title);
		// TextView tkj_date = (TextView) rowView.findViewById(R.id.tkj_date);
		// TextView mmm_name = (TextView) rowView.findViewById(R.id.mmm_name);
		// TextView tkj_desc = (TextView) rowView.findViewById(R.id.tkj_desc);
		// TextView ttt_id = (TextView) rowView.findViewById(R.id.ttt_id);

		tkj_title.setText(Values[position]);
		return rowView;
	}

}
