package com.android.ustadzkita;

public class FormDataKajian {
	//private variables
    int _id;
    String _title;
    String _date;
    String _name;
    String _desc;
 
    // Empty constructor
    public FormDataKajian(){
 
    }
    // constructor
    public FormDataKajian(int tkj_id, String tkj_title, String tkj_date, String mmm_name, String tkj_desc){
        this._id = tkj_id;
        this._title = tkj_title;
        this._date = tkj_date;
        this._name = mmm_name;
        this._desc = tkj_desc;
    }
 
    // constructor
    public FormDataKajian(String tkj_title, String tkj_date, String mmm_name, String tkj_desc){
    	this._title = tkj_title;
        this._date = tkj_date;
        this._name = mmm_name;
        this._desc = tkj_desc;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
 
    // setting id
    public void setID(int tkj_id){
        this._id = tkj_id;
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
    public void setDate(String tkj_date){
        this._date = tkj_date;
    }
    
    public String getTitle(){
    	return this._title;
    }
    
    public void setTitle(String tkj_title){
    	this._title= tkj_title;
    }
    
    public String getDesc(){
    	return this._desc;
    }
    
    public void setDesc(String tkj_desc){
    	this._desc = tkj_desc;
    }
}
