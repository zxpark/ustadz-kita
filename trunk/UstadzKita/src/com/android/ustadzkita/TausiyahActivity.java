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

import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
//import android.widget.ArrayAdapter;
//import android.widget.Toast;
//import android.R.integer;
import android.app.ListActivity;
//import android.content.Context;
//import android.content.Intent;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
import android.content.Intent;

public class TausiyahActivity extends ListActivity {

	private static String link_url = "http://119.82.224.38/services/trs_tausiah.php?imei=1";

	private static final String AR_ID = "ttt_id";
	private static final String AR_DATE = "ttt_date";
	// private static final String AR_IMEI = "imei";
	// private static final String AR_PIN = "pinnumber";
	// private static final String AR_MMMID = "mmm_id";
	private static final String AR_NAME = "mmm_name";
	private static final String AR_TITLE = "ttt_title";
	private static final String AR_DESC = "ttt_desc";

	List<HashMap<String, String>> myMaps;
	Vector<String> mapData = new Vector<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.tausiyah);

		// ListView lv = (ListView) this.findViewById(R.id.listView);

		DBHandler dbHandler = new DBHandler(getBaseContext(), "ustadzkita2.db",
				"tausiyah");

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
			int[] to = new int[] { R.id.ttt_title, R.id.ttt_desc, R.id.ttt_date };
			myMaps = new ArrayList<HashMap<String, String>>();

			JSONObject jo = new JSONObject(catchData);
			JSONArray jr = jo.getJSONArray("items");

			if (jr != null) {

				for (int i = 0; i < jr.length(); i++) {

					HashMap<String, String> map = new HashMap<String, String>();
					JSONObject ar = jr.getJSONObject(i);

					String ttt_id = ar.getString(AR_ID);
					String ttt_date = ar.getString(AR_DATE);
					// String imei = ar.getString(AR_IMEI);
					// String pinnumber = ar.getString(AR_PIN);
					// String mmm_id = ar.getString(AR_MMMID);
					String mmm_name = ar.getString(AR_NAME);
					String ttt_title = ar.getString(AR_TITLE);
					String ttt_desc = ar.getString(AR_DESC);
					// String tth_id = ar.getString(AR_TTHID);

					map.put("id", ttt_id);
					map.put("title", ttt_title);
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
					// map.put("desc", ttt_desc);
					map.put("date", "Tanggal: " + ttt_date + " " + " Ustadz: "
							+ mmm_name);

					myMaps.add(map);
					mapData.add(ttt_id);
					dbHandler.addData(new FormData(Integer.parseInt(ttt_id),
							ttt_title, ttt_date, mmm_name, ttt_desc));
				}
			}

			SimpleAdapter adapter = new SimpleAdapter(this, myMaps,
					R.layout.tausiyah_singlelist, from, to);
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

		DBHandler dbHandler = new DBHandler(getApplicationContext(),
				"ustadzkita2.db", "tausiyah");
		FormData data = dbHandler.getData((String) mapData.elementAt(position));

		// Toast.makeText(getApplicationContext(), data.getTitle(),
		// 3000).show();
		Bundle param = new Bundle();
		param.putString("title", data.getTitle().toString());
		param.putString("date", data.getDate().toString());
		param.putString("name", data.getName().toString());
		param.putString("desc", data.getDesc().toString());
		Intent intent = new Intent(v.getContext(), DetailTausiyah.class);
		intent.putExtra("data",param);
		startActivity(intent);
	}
}