package cn.com.farsight.mytextviewdemo;

import android.os.Bundle;
import android.app.Activity;
import android.text.util.Linkify;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView print = (TextView) findViewById(R.id.tv);
		EditText passwd = (EditText) findViewById(R.id.edit);
		
		
		
		
		//textView.setAutoLinkMask(Linkify.WEB_URLS);
		//textView.setText("www.126.com");
		
	}



}
