package com.android.ustadzkita;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;

import android.widget.ListView;
import android.widget.SimpleAdapter;
//import android.widget.Toast;
import android.app.Activity;

public class UstadzTeraktifActivity extends Activity {
	
	private static String link_url = "http://119.82.224.38/services/getmemberstop.php";
	
	//private static final String UT_MMMID = "mmm_id";
	private static final String UT_MMM_NAME = "mmm_name";
	//private static final String UT_PHOTO = "mmm_photo_name";
	private static final String UT_TK = "totalkajian";
	private static final String UT_TT = "totaltausiyah";
	private static final String UT_TOTAL = "total";

	@Override
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ustadzteraktif);		
		
		ListView lv = (ListView) this.findViewById(R.id.listView);
		
		HttpUtils getData = new HttpUtils();
		String catchData = null;
		try {
			catchData = getData.fectUrl(link_url);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
				
		//Toast.makeText(getApplicationContext(), catchData, 3000).show();

		try {
		
			String[] from = new String[] {"nama","total tausiyah", "total kajian", "total"};
			int[] to = new int[] {R.id.mmm_name, R.id.totaltausiyah, R.id.totalkajian, R.id.total};
			List<HashMap<String, String>> myMaps = new ArrayList<HashMap<String, String>>();
			
			JSONObject jo = new JSONObject(catchData);
			JSONArray jr = jo.getJSONArray("items");
			
			//String[] arrTitle = null;
			
			if(jr != null) {
			
				for(int i = 0; i < jr.length(); i++){
					
					HashMap<String, String> map = new HashMap<String, String>();
					JSONObject ar = jr.getJSONObject(i);
					
					//String ttt_id = ar.getString(AR_ID);
					//String mmm_photo_name = ar.getString(UT_PHOTO);
					//String imei = ar.getString(AR_IMEI);
					//String pinnumber = ar.getString(AR_PIN);
					//String mmm_id = ar.getString(AR_MMMID);
					String mmm_name = ar.getString(UT_MMM_NAME);
					String totalkajian = ar.getString(UT_TK);
					String totaltausiyah = ar.getString(UT_TT);
					String total = ar.getString(UT_TOTAL);
					//String tth_id = ar.getString(AR_TTHID);

					//map.put("photo", mmm_photo_name);
					map.put("nama", mmm_name);
					map.put("total tausiyah", "Total Tausiyah: " + totaltausiyah);
					map.put("total kajian", "Total Kajian: " + totalkajian);
					map.put("total", "Total: " + total);
					myMaps.add(map);
				}
			}

			SimpleAdapter adapter = new SimpleAdapter(this, myMaps, R.layout.ustadzteraktif_singlelist, from, to);
			lv.setAdapter(adapter);			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
