package com.android.ustadzkita;

import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
//import android.widget.Toast;

public class DetailTausiyah extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tausiyah_singlelist);
        
        Bundle bundle = this.getIntent().getExtras().getBundle("data");
        String title = bundle.getString("title");
        String date = bundle.getString("date");
        String name = bundle.getString("name");
        String desc = bundle.getString("desc");
        //Toast.makeText(getApplicationContext(), title, 3000).show();
        //Toast.makeText(getApplicationContext(), date, 3000).show();
        //Toast.makeText(getApplicationContext(), name, 3000).show();
        //Toast.makeText(getApplicationContext(), desc, 3000).show();
        
        TextView dtTitle = (TextView) findViewById(R.id.ttt_title);
        dtTitle.setText(title);
        
        TextView dtDate = (TextView) findViewById(R.id.ttt_date);
        dtDate.setText(date);
        
        TextView dtName = (TextView) findViewById(R.id.mmm_name);
        dtName.setText(name);
        
        TextView dtDesc = (TextView) findViewById(R.id.ttt_desc);
        dtDesc.setText(desc);
        
	}
}
