package com.example.workingtime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;

import br.com.uacari.android.UacariReader;

public class Converter {
	public static String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {		
		byte[] buffer = UacariReader.getBytes(entity.getContent());
		String x = new String(buffer); 
		return x;
	}
	public static String notNull(String text){
    	if(text==null){
    		return "";
    	}else{
    		return text;    		
    	}
    }
	public static Integer notNull(Integer text){
    	if(text==null){
    		return 0;
    	}else{
    		return text;    		
    	}
    }
	public static Date toDate(String strDate,String pattern){
		SimpleDateFormat spdf = new SimpleDateFormat(pattern);
		try{
			if(strDate!=null){
				return spdf.parse(strDate);
			}
		}catch(Exception e){
			return new Date();
		}
		return null;
	}
	public static Date toDate(String strDate){
		return toDate(strDate,"yyyy-MM-dd HH:mm:ss.SS");
	}
	
	public static String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	    Reader reader = null;
	    reader = new InputStreamReader(stream, "UTF-8");        
	    char[] buffer = new char[len];
	    reader.read(buffer);
	    return new String(buffer);
	}
}
