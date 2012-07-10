package com.android.ustadzkita;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpConnection;
import org.apache.http.util.ByteArrayBuffer;

public class HttpUtils {

	HttpConnection _c;
	OutputStream _s;
	InputStream _is;
	
	public HttpUtils() {
		_c = null;
		_s = null;
		_is = null;
	}
	
	public String fectUrl(String address) throws IOException {
		
		String raw = null;
		
        try{
            URL url = new URL(address);
           
            URLConnection urlConn = url.openConnection();
           
            InputStream is = urlConn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
           
            ByteArrayBuffer baf = new ByteArrayBuffer(50);
            int current = 0;
            while((current = bis.read()) != -1){
            	baf.append((byte)current);
            }
           
            raw = new String(baf.toByteArray());

        }catch(Exception e){
            System.out.println(e.getMessage());
           
        }
        
        return raw;
		
	}
	
}
