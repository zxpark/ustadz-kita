package com.android.ustadzkita;

public class FormData {

	//private variables
    int _id;
    String _title;
    String _date;
    String _name;
    String _desc;
 
    // Empty constructor
    public FormData(){
 
    }
    // constructor
    public FormData(int ttt_id, String ttt_title, String ttt_date, String mmm_name, String ttt_desc){
        this._id = ttt_id;
        this._title = ttt_title;
        this._date = ttt_date;
        this._name = mmm_name;
        this._desc = ttt_desc;
    }
 
    // constructor
    public FormData(String ttt_title, String ttt_date, String mmm_name, String ttt_desc){
    	this._title = ttt_title;
        this._date = ttt_date;
        this._name = mmm_name;
        this._desc = ttt_desc;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
 
    // setting id
    public void setID(int ttt_id){
        this._id = ttt_id;
    }
 
    // getting name
    public String getName(){
        return this._name;
    }
 
    // setting name
    public void setName(String mmm_name){
        this._name = mmm_name;
    }
 
    // getting phone number
    public String getDate(){
        return this._date;
    }
 
    // setting phone number
    public void setDate(String ttt_date){
        this._date = ttt_date;
    }
    
    public String getTitle(){
    	return this._title;
    }
    
    public void setTitle(String ttt_title){
    	this._title= ttt_title;
    }
    
    public String getDesc(){
    	return this._desc;
    }
    
    public void setDesc(String ttt_desc){
    	this._desc = ttt_desc;
    }
}
