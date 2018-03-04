package cn.com.farsight.network.status;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv1 = (TextView)findViewById(R.id.textView1);
        TextView tv2 = (TextView)findViewById(R.id.textView2);
        
        ConnectivityManager connec =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);   
        NetworkInfo info = connec.getActiveNetworkInfo(); 
       
        if(info==null){
        	tv1.setText(tv1.getText()+"û������");
        }else{
        	tv1.setText(tv1.getText()+"�Ѿ�����");
        	tv2.setText(tv2.getText()+info.getTypeName());
        }
    }
}
