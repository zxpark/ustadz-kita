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
import android.widget.Toast;
import android.app.Activity;

public class KajianActivity extends Activity{
	
	private static String link_url = "http://119.82.224.38/services/trs_kajian.php?imei=1";
		
		private static final String KA_ID = "tkj_id";
		private static final String KA_MMMID = "mmm_id";
		private static final String KA_MMM_NAME = "mmm_name";
		private static final String KA_TITLE = "tkj_title";
		private static final String KA_DESC = "tkj_desc";
		private static final String KA_DATE = "tkj_date";

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.kajian);		
			
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
			
				String[] from = new String[] {"title","desc","date"};
				int[] to = new int[] {R.id.tkj_title, R.id.tkj_desc, R.id.tkj_date};
				List<HashMap<String, String>> myMaps = new ArrayList<HashMap<String, String>>();
				
				JSONObject jo = new JSONObject(catchData);
				JSONArray jr = jo.getJSONArray("items");
				
				String[] arrTitle = null;
				
				if(jr != null) {
				
					for(int i = 0; i < jr.length(); i++){
						
						HashMap<String, String> map = new HashMap<String, String>();
						JSONObject ar = jr.getJSONObject(i);
						
						//String ttt_id = ar.getString(AR_ID);
						String tkj_date = ar.getString(KA_DATE);
						//String imei = ar.getString(AR_IMEI);
						//String pinnumber = ar.getString(AR_PIN);
						//String mmm_id = ar.getString(AR_MMMID);
						String mmm_name = ar.getString(KA_MMM_NAME);
						String tkj_title = ar.getString(KA_TITLE);
						String tkj_desc = new String (ar.getString(KA_DESC).substring(0, (ar.getString(KA_DESC).length()>100)? 100 : ar.getString(KA_DESC).length())+"...");
						//String tth_id = ar.getString(AR_TTHID);

						map.put("title", tkj_title);
						map.put("desc", tkj_desc);
						map.put("date", "Tanggal: " + tkj_date+ " " + " Ustadz: "+mmm_name);
						
						myMaps.add(map);
					}
				}

				SimpleAdapter adapter = new SimpleAdapter(this, myMaps, R.layout.kajian_singlelist, from, to);
				lv.setAdapter(adapter);			
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
}
