package cn.com.farsight.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main extends Activity { 
    TextView tv; 
    final String TAG="AsyncTaskTest"; 
  
    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.main); 
        tv = (TextView) findViewById(R.id.textView1); 
        new MyTask().execute(6, 12, 7); 
  
    } 
  
    class MyTask extends AsyncTask<Integer, Integer, Integer> { 
  
        @Override 
        protected void onPreExecute() { 
            super.onPreExecute(); 
            Log.d(TAG, "onPreExecute()"); 
        } 
  
        @Override 
        protected Integer doInBackground(Integer... params) { 
            Log.d(TAG, "doInBackground()"); 
            int p = 0; 
            for (int index = 0; index < params.length; index++) { 
                int num = params[index]; 
                for (int j = 0; j < num; j++) { 
                    if (num - j <= 0) { 
                        break; 
                    } 
                    p++; 
                    publishProgress(p); 
                    try { 
                    	//ºÄÊ±²Ù×÷
                        Thread.sleep(500); 
                    } catch (InterruptedException e) { 
                        e.printStackTrace(); 
                    } 
                } 
            } 
            return p; 
        } 
  
        @Override 
        protected void onProgressUpdate(Integer... progress) { 
            Log.d(TAG, "onProgressUpdate()"); 
            tv.append("\nProgress: " + progress[0]); 
        } 
  
        @Override 
        protected void onPostExecute(Integer result) { 
            Log.d(TAG, "onPostExecute()"); 
            tv.append("\nFinished. Result: " + result); 
        } 
  
       
    } 
} 
