package com.brainnet.smartremote.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;


public class AsyncHttpDownloader extends AsyncTask<String,String, String> {
	

	public interface OnAsyncHttpDownloaderLisenter {
		public void onEnd(String text);
	}
	
	OnAsyncHttpDownloaderLisenter mLisenter;
	private String url = "";
	
    // 根据url下载文件，前提是这个文件是文本文件，函数的返回值是文件中的内容  

	public AsyncHttpDownloader(OnAsyncHttpDownloaderLisenter lisenter){

		mLisenter = lisenter;
		
	}

	
	public void download( String id,String type){
		
		this.execute(url);	
	}
	


	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		mLisenter.onEnd(result);

	}


	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}


	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
	}


	private String download(String urlStr) {  

		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		int BUFFER_SIZE = 1024;
		String ret = null;
		byte[] buf = new byte[BUFFER_SIZE];
		HttpURLConnection urlConn = null;
		InputStream is = null;
       
		try {  
  
           // 使用IO流读取数据       
	       // 创建url  
	       URL url = new URL(urlStr);  
	       // 创建http    
	       urlConn = (HttpURLConnection) url.openConnection();  
	       // 读取数据    
	       is = urlConn.getInputStream();  
	       int nRead = 0;					
			while(true){				
				nRead = is.read(buf,0,BUFFER_SIZE);
				if( nRead == -1){					
					break;
				}
				if(nRead > 0) {
					out.write(buf,0,nRead);
				}
			}
			ret =  new String(out.toByteArray());
			
		} catch (IOException e) {  
			e.printStackTrace();    
		}  
		
		if(urlConn != null){
			urlConn.disconnect();
			urlConn = null;
		}
	
       return "";  
    }  
  
  

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String url = (String) params[0];
		return download(url);
	}  
}
