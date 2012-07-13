package com.android.ustadzkita;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailKajian extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_kajian_singlelist);
        
        Bundle bundle = this.getIntent().getExtras().getBundle("data");
        String title = bundle.getString("title");
        String date = bundle.getString("date");
        String name = bundle.getString("name");
        String desc = bundle.getString("desc");
        //Toast.makeText(getApplicationContext(), title, 3000).show();
        //Toast.makeText(getApplicationContext(), date, 3000).show();
        //Toast.makeText(getApplicationContext(), name, 3000).show();
        //Toast.makeText(getApplicationContext(), desc, 3000).show();
        
        TextView dtTitle = (TextView) findViewById(R.id.tkj_title);
        dtTitle.setText(title);
        
        TextView dtDate = (TextView) findViewById(R.id.tkj_date);
        dtDate.setText(date);
        
        TextView dtName = (TextView) findViewById(R.id.mmm_name);
        dtName.setText(name);
        
        TextView dtDesc = (TextView) findViewById(R.id.tkj_desc);
        dtDesc.setText(desc);   
	}
}
