package com.android.ustadzkita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
//import android.widget.ImageView;
import android.widget.TextView;

public class UstadzTeraktifAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] Values;
	
	public UstadzTeraktifAdapter(Context context, String[] objects){
		super(context, R.layout.ustadzteraktif_singlelist, objects);
		
		this.context = context;
		this.Values = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.ustadzteraktif_singlelist, parent, false);
		//convertView = inflater.inflate(R.layout.ustadzteraktif_singlelist, null);
		
		//ImageView mmm_photo_name = (ImageView) convertView.findViewById(R.id.mmm_photo_name);
		//ImageView mmm_photo_name = (ImageView) rowView.findViewById(R.id.mmm_photo_name);
		//TextView tkj_date = (TextView) rowView.findViewById(R.id.tkj_date);
		TextView mmm_name = (TextView) rowView.findViewById(R.id.mmm_name);
		TextView totaltausiyah = (TextView) rowView.findViewById(R.id.totaltausiyah);
		TextView totalkajian = (TextView) rowView.findViewById(R.id.totalkajian);
		TextView total = (TextView) rowView.findViewById(R.id.total);
		
		mmm_name.setText(Values[position]);
		//return convertView;
		return rowView;
	}
}
