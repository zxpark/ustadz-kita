package com.android.ustadzkita;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;

//import android.widget.ListView;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
//import android.widget.Toast;
//import android.app.Activity;
import android.app.ListActivity;
//import android.content.Intent;
import android.content.Intent;

public class KajianActivity extends ListActivity {

	private static String link_url = "http://119.82.224.38/services/trs_kajian.php?imei=1";

	private static final String AR_ID = "tkj_id";
	// private static final String KA_MMMID = "mmm_id";
	private static final String AR_NAME = "mmm_name";
	private static final String AR_TITLE = "tkj_title";
	private static final String AR_DESC = "tkj_desc";
	private static final String AR_DATE = "tkj_date";

	List<HashMap<String, String>> myMaps;
	Vector<String> mapData = new Vector<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.kajian);

		// ListView lv = (ListView) this.findViewById(R.id.listView);

		DBHandlerKajian dbHandler = new DBHandlerKajian(getBaseContext(),
				"ustadzkita2.db", "kajian");

		HttpUtils getData = new HttpUtils();
		String catchData = null;
		try {
			catchData = getData.fectUrl(link_url);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Toast.makeText(getApplicationContext(), catchData, 3000).show();

		try {

			String[] from = new String[] { "title", "desc", "date" };
			int[] to = new int[] { R.id.tkj_title, R.id.tkj_desc, R.id.tkj_date };
			myMaps = new ArrayList<HashMap<String, String>>();

			JSONObject jo = new JSONObject(catchData);
			JSONArray jr = jo.getJSONArray("items");

			// String[] arrTitle = null;

			if (jr != null) {

				for (int i = 0; i < jr.length(); i++) {

					HashMap<String, String> map = new HashMap<String, String>();
					JSONObject ar = jr.getJSONObject(i);

					String tkj_id = ar.getString(AR_ID);
					String tkj_date = ar.getString(AR_DATE);
					// String imei = ar.getString(AR_IMEI);
					// String pinnumber = ar.getString(AR_PIN);
					// String mmm_id = ar.getString(AR_MMMID);
					String mmm_name = ar.getString(AR_NAME);
					String tkj_title = ar.getString(AR_TITLE);
					String tkj_desc = ar.getString(AR_DESC);
					// String tth_id = ar.getString(AR_TTHID);

					map.put("id", tkj_id);
					map.put("title", tkj_title);
					map.put("desc",
							new String(
									ar.getString(AR_DESC)
											.substring(
													0,
													(ar.getString(AR_DESC)
															.length() > 100) ? 100
															: ar.getString(
																	AR_DESC)
																	.length())
											+ "..."));
					map.put("date", "Tanggal: " + tkj_date + " " + " Ustadz: "
							+ mmm_name);

					myMaps.add(map);
					mapData.add(tkj_id);
					dbHandler.addDataKajian(new FormDataKajian(Integer
							.parseInt(tkj_id), tkj_title, tkj_date, mmm_name,
							tkj_desc));
				}
			}

			SimpleAdapter adapter = new SimpleAdapter(this, myMaps,
					R.layout.kajian_singlelist, from, to);
			// lv.setAdapter(adapter);
			setListAdapter(adapter);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onListItemClick(ListView parent, View v, int position,
			long id) {
		super.onListItemClick(parent, v, position, id);

		DBHandlerKajian dbHandler = new DBHandlerKajian(
				getApplicationContext(), "ustadzkita2.db", "kajian");
		FormDataKajian data = dbHandler.getData((String) mapData
				.elementAt(position));

		// Toast.makeText(getApplicationContext(), data.getTitle(),
		// 3000).show();
		// Bundle param = new Bundle();
		// param.putString("title", data.getTitle().toString());

		// Intent intent = new Intent(this, DetailTausiyah.class);
		// startActivity(intent.putExtra("title", param));

		Bundle param = new Bundle();
		param.putString("title", data.getTitle().toString());
		param.putString("date", data.getDate().toString());
		param.putString("name", data.getName().toString());
		param.putString("desc", data.getDesc().toString());
		Intent intent = new Intent(v.getContext(), DetailKajian.class);
		intent.putExtra("data", param);
		startActivity(intent);
	}
}
