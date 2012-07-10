//test lalalalala
package com.android.ustadzkita;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UstadzKitaActivity extends Activity {
    /** Called when the activity is first created. */
	Button button1, button2, button3, button4, button5;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				tausiyah();
			}
		});
		
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				kajian();
			}
		});
		
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				teraktif();
			}
		});
		
		button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				pengaturan();
			}
		});
    }
    
    public void tausiyah() {
		Intent bukatausiyah = new Intent(this, TausiyahActivity.class);
		startActivity(bukatausiyah);
	}
    
    public void kajian() {
		Intent bukakajian = new Intent(this, KajianActivity.class);
		startActivity(bukakajian);
	}
    
    public void teraktif() {
		Intent bukateraktif = new Intent(this, UstadzTeraktifActivity.class);
		startActivity(bukateraktif);
	}
    
    public void pengaturan() {
		Intent bukapengaturan = new Intent(this, Pengaturan.class);
		startActivity(bukapengaturan);
	}
}