package com.android.ustadzkita;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.widget.Toast;

public class DBHandlerTausiyah extends SQLiteOpenHelper {
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Contacts Table Columns names
	private static final String AR_ID = "ttt_id";
	private static final String AR_TITLE = "ttt_title";
	private static final String AR_DATE = "ttt_date";
	private static final String AR_NAME = "mmm_name";
	private static final String AR_DESC = "ttt_desc";
	private String tableName = "";

	public DBHandlerTausiyah(Context context, String dbName, String tableName) {
		super(context, dbName, null, DATABASE_VERSION);
		this.tableName = tableName;
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_tableName = "CREATE TABLE IF NOT EXISTS " + tableName
				+ "(" + AR_ID + " INTEGER PRIMARY KEY," + AR_TITLE + " TEXT,"
				+ AR_DATE + " DATE," + AR_NAME + " TEXT," + AR_DESC + " TEXT"
				+ ")";
		db.execSQL(CREATE_tableName);
		db.close();
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + tableName);

		// Create tables again
		onCreate(db);
		db.close();
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	void addData(FormData formData) {
		SQLiteDatabase db = this.getWritableDatabase();

		//ContentValues values = new ContentValues();
		ContentValues values = new ContentValues();
		values.put(AR_ID,formData.getID());
		values.put(AR_TITLE, formData.getTitle());
		values.put(AR_DATE, formData.getDate());
		values.put(AR_NAME, formData.getName());
		values.put(AR_DESC,formData.getDesc());
		
		while(values == null){
			db.insert(tableName, null, values);
		}
		
		db.close(); // Closing database connection
	}

	// Getting single contact
	FormData getTitle(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(tableName, new String[] { AR_ID, AR_TITLE,
				AR_DATE, AR_NAME, AR_DESC }, AR_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		FormData formData = new FormData(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4));
		// return contact
		db.close();
		return formData;
	}

	public FormData getData(String IdNews) {
		//FormData DataList = new FormData();
		String selectQuery = "SELECT * FROM " + tableName
				+ " WHERE ttt_id = " + IdNews;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		cursor.moveToFirst();
		FormData formData = new FormData();
		//if (cursor.getCount() > 0) {
			formData.setID(Integer.parseInt(cursor.getString(0)));
			formData.setTitle(cursor.getString(1));
			formData.setDate(cursor.getString(2));
			formData.setName(cursor.getString(3));
			formData.setDesc(cursor.getString(4));
		//}
		db.close();
		return formData;
	}

	// Getting All Contacts
	public List<FormData> getAllData() {
		List<FormData> DataList = new ArrayList<FormData>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + tableName;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				FormData formData = new FormData();
				formData.setID(Integer.parseInt(cursor.getString(0)));
				formData.setTitle(cursor.getString(1));
				formData.setDate(cursor.getString(2));
				formData.setName(cursor.getString(3));
				formData.setDesc(cursor.getString(4));
				// Adding contact to list
				DataList.add(formData);
			} while (cursor.moveToNext());
		}

		// return contact list
		db.close();
		return DataList;
	}

	// Updating single contact
	public int updateData(FormData formData) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(AR_ID, formData.getID());
		values.put(AR_TITLE, formData.getTitle());
		values.put(AR_DATE, formData.getDate());
		values.put(AR_NAME, formData.getName());
		values.put(AR_DESC, formData.getDesc());

		// updating row
		return db.update(tableName, values, AR_ID + " = ?",
				new String[] { String.valueOf(formData.getID()) });
	}

	// Deleting single contact
	public void deleteData(FormData formData) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(tableName, AR_ID + " = ?",
				new String[] { String.valueOf(formData.getID()) });
		db.close();
	}

	// Getting contacts Count
	public int getDataCount() {
		String countQuery = "SELECT  * FROM " + tableName;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		db.close();
		return cursor.getCount();
	}
}